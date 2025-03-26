package com.example.calendar.service;

import com.example.calendar.dto.CalendarRequestDto;
import com.example.calendar.dto.CalendarResponseDto;
import com.example.calendar.dto.DeleteCalendarRequestDto;
import com.example.calendar.dto.SearchCalendarRequestDto;
import com.example.calendar.entity.Calendar;
import com.example.calendar.entity.Writer;
import com.example.calendar.repository.CalendarRepository;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

/**
 * 일정 관련 비즈니스 로직을 처리하는 서비스 구현 클래스
 */
@Service
public class CalendarServiceImpl implements CalendarService {

    private final CalendarRepository calendarRepository;

    public CalendarServiceImpl(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    /**
     * 새로운 일정을 추가하는 메서드이다.
     *
     * @param dto 생성할 일정 정보
     * @return 생성된 일정에 대한 응답 DTO
     */
    @Override
    public CalendarResponseDto createSchedule(CalendarRequestDto dto) {

        // 작성자명과 비밀번호를 이용하여 작성자의 id 찾기
        Long writerId = calendarRepository.findWriterIdByNameAndPassword(dto.getWriterName(), dto.getPassword());

        // 작성자명 혹은 비밀번호가 유효하지 않은 경우
        if (writerId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "작성자명 혹은 비밀번호가 틀렸습니다.");
        }

        // 일정 객체 생성
        Writer writer = new Writer(dto.getWriterName(), dto.getPassword());
        Calendar calendar = new Calendar(dto.getTodo(), Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime(),
                Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime());

        return calendarRepository.saveSchedule(calendar, writer, writerId);
    }

    /**
     * 모든 일정을 조회하는 메서드이다.
     *
     * @param dto 검색 조건
     * @return 검색 조건에 맞는 일정 목록
     */
    @Override
    public List<CalendarResponseDto> findAllSchedules(SearchCalendarRequestDto dto) {

        // 검색 조건이 없으면 그냥 모든 일정 조회
        if (dto == null) {
            return calendarRepository.findAllSchedules();
        }

        return calendarRepository.findAllSchedules(dto.getModDate(), dto.getWriterName(), dto.getWriterId(),
                dto.getEmail());
    }

    /**
     * 특정 일정을 조회하는 메서드이다.
     *
     * @param id 조회할 일정의 id
     * @return 조회된 일정에 대한 응답 DTO
     */
    @Override
    public CalendarResponseDto findScheduleById(Long id) {

        return calendarRepository.findScheduleByIdOrElseThrow(id);
    }

    /**
     * 특정 일정을 수정하는 메서드이다.
     *
     * @param id 수정할 일정의 id
     * @param dto 수정할 일정 정보
     * @return 수정된 일정에 대한 응답 DTO
     */
    @Transactional
    @Override
    public CalendarResponseDto updateSchedule(Long id, CalendarRequestDto dto) {

        // 일정에 해당하는 작성자 id 확인
        Long scheduleWriterId = calendarRepository.findWriterIdByScheduleId(id);
        // 작성자명과 비밀번호를 확인하여 작성자 id 확인
        Long writerId = calendarRepository.findWriterIdByNameAndPassword(dto.getWriterName(), dto.getPassword());

        // 작성자명 혹은 비밀번호가 틀린 경우
        if (writerId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "작성자명 혹은 비밀번호가 틀렸습니다.");
        }

        // 일정을 작성한 사람이 아닌 경우
        if (!writerId.equals(scheduleWriterId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "본인의 일정만 수정할 수 있습니다.");
        }

        int updatedRow = calendarRepository.updateSchedule(id, dto.getTodo(),
                Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime());

        // 수정된 일정이 없을 경우
        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 일정이 존재하지 않습니다.");
        }

        return calendarRepository.findScheduleByIdOrElseThrow(id);
    }

    /**
     * 특정 일정을 삭제하는 메서드이다.
     *
     * @param id 삭제할 일정의 id
     * @param dto 작성자의 정보
     */
    @Override
    public void deleteSchedule(Long id, DeleteCalendarRequestDto dto) {

        // 일정에 해당하는 작성자 id 확인
        Long scheduleWriterId = calendarRepository.findWriterIdByScheduleId(id);
        // 이메일과 비밀번호를 확인하여 작성자 id 확인
        Long writerId = calendarRepository.findWriterIdByEmailAndPassword(dto.getEmail(), dto.getPassword());

        // 작성자명 혹은 비밀번호가 틀린 경우
        if (writerId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "작성자명 혹은 비밀번호가 틀렸습니다.");
        }

        // 일정을 작성한 사람이 아닌 경우
        if (!writerId.equals(scheduleWriterId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "본인의 일정만 수정할 수 있습니다.");
        }

        int updatedRow = calendarRepository.deleteSchedule(id, dto.getEmail(), dto.getPassword());

        // 삭제된 일정이 없을 경우
        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 일정이 존재하지 않습니다.");
        }
    }
}
