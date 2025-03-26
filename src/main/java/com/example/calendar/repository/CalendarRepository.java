package com.example.calendar.repository;

import com.example.calendar.dto.CalendarResponseDto;
import com.example.calendar.entity.Calendar;
import com.example.calendar.entity.Writer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 일정 관련 db 작업을 처리하는 레포지토리 인스턴스
 */
public interface CalendarRepository {

    /**
     * 새로운 일정을 추가하는 메서드이다.
     *
     * @param calendar 저장할 일정 내용
     * @param writer 일정의 작성자 정보
     * @param writerId 작성자 id
     * @return 저장된 일정에 대한 응답 DTO
     */
    CalendarResponseDto saveSchedule(Calendar calendar, Writer writer, Long writerId);

    /**
     * 모든 일정을 조회하는 메서드이다.
     *
     * @return 모든 일정에 대한 응답 DTO 리스트
     */
    List<CalendarResponseDto> findAllSchedules();

    /**
     * 수정일, 작성자명, 작성자 id, 이메일을 기준으로 모든 일정을 조회하는 메서드이다.
     *
     * @param modDate 조회할 수정일
     * @param writerName 조회할 작성자명
     * @param writerId 조회할 작성자 id
     * @param email 조회할 이메일
     * @return 조건에 맞는 모든 일정에 대한 응답 DTO 리스트
     */
    List<CalendarResponseDto> findAllSchedules(LocalDate modDate, String writerName, Long writerId, String email);

    /**
     * 득정 일정을 조회하는 메서드이다.
     *
     * @param id 조회할 일정의 Id
     * @return 조회된 일정에 대한 응답 DTO
     */
    CalendarResponseDto findScheduleByIdOrElseThrow(Long id);

    /**
     * 일정을 수정하는 메서드이다.
     *
     * @param id 수정할 일정의 id
     * @param todo 수정할 일정 내용
     * @param modDate 수정일
     * @return 수정된 행 수
     */
    int updateSchedule(Long id, String todo, LocalDateTime modDate);

    /**
     * 특정 일정을 삭제하는 메서드이다.
     *
     * @param id 삭제할 일정의 id
     * @param email 작성자의 이메일
     * @param password 작성자의 비밀번호
     * @return 삭제된 행 수
     */
    int deleteSchedule(Long id, String email, String password);

    /**
     * 일정 id로 작성자 id를 조회하는 메서드이다.
     *
     * @param id 일정의 id
     * @return 해당 일정의 작성자 id
     */
    Long findWriterIdByScheduleId(Long id);

    /**
     * 작성자명과 비밀번호로 작성자 id를 조회하는 메서드이다.
     *
     * @param writerName 작성자명
     * @param password 작성자의 비밀번호
     * @return 작성자 id
     */
    Long findWriterIdByNameAndPassword(String writerName, String password);

    /**
     * 이메일과 비밀번호로 작성자 id를 조회하는 메서드이다.
     *
     * @param email 작성자의 이메일
     * @param password 작성자의 비밀번호
     * @return 작성자 id
     */
    Long findWriterIdByEmailAndPassword(String email, String password);
}
