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

@Service
public class CalendarServiceImpl implements CalendarService {

    private final CalendarRepository calendarRepository;

    public CalendarServiceImpl(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    @Override
    public CalendarResponseDto createSchedule(CalendarRequestDto dto) {

        Long writerId = calendarRepository.findWriterIdByNameAndPassword(dto.getWriterName(), dto.getPassword());

        if (writerId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "작성자명 혹은 비밀번호가 틀렸습니다.");
        }

        Writer writer = new Writer(dto.getWriterName(), dto.getPassword());
        Calendar calendar = new Calendar(dto.getTodo(), Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime(),
                Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime());

        return calendarRepository.saveSchedule(calendar, writer, writerId);
    }

    @Override
    public List<CalendarResponseDto> findAllSchedules(SearchCalendarRequestDto dto) {

        if (dto == null) {
            return calendarRepository.findAllSchedules();
        }

        return calendarRepository.findAllSchedules(dto.getModDate(), dto.getWriterName(), dto.getWriterId(),
                dto.getEmail());
    }

    @Override
    public CalendarResponseDto findScheduleById(Long id) {

        return calendarRepository.findScheduleByIdOrElseThrow(id);
    }

    @Transactional
    @Override
    public CalendarResponseDto updateSchedule(Long id, CalendarRequestDto dto) {

        Long scheduleWriterId = calendarRepository.findWriterIdByScheduleId(id);
        Long writerId = calendarRepository.findWriterIdByNameAndPassword(dto.getWriterName(), dto.getPassword());

        if (writerId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "작성자명 혹은 비밀번호가 틀렸습니다.");
        }

        if (!writerId.equals(scheduleWriterId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "본인의 일정만 수정할 수 있습니다.");
        }

        int updatedRow = calendarRepository.updateSchedule(id, dto.getTodo(),
                Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime());

        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 일정이 존재하지 않습니다.");
        }

        return calendarRepository.findScheduleByIdOrElseThrow(id);
    }

    @Override
    public void deleteSchedule(Long id, DeleteCalendarRequestDto dto) {

        Long scheduleWriterId = calendarRepository.findWriterIdByScheduleId(id);
        Long writerId = calendarRepository.findWriterIdByEmailAndPassword(dto.getEmail(), dto.getPassword());

        if (writerId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "작성자명 혹은 비밀번호가 틀렸습니다.");
        }

        if (!writerId.equals(scheduleWriterId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "본인의 일정만 수정할 수 있습니다.");
        }

        int updatedRow = calendarRepository.deleteSchedule(id, dto.getEmail(), dto.getPassword());

        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 일정이 존재하지 않습니다.");
        }
    }
}
