package com.example.calendar.service;

import com.example.calendar.dto.CalendarRequestDto;
import com.example.calendar.dto.CalendarResponseDto;
import com.example.calendar.dto.DeleteCalendarRequestDto;
import com.example.calendar.dto.SearchCalendarRequestDto;
import java.util.List;

/**
 * 일정 관련 비즈니스 로직을 처리하는 서비스 인터페이스
 */
public interface CalendarService {

    /**
     * 새로운 일정을 추가하는 메서드이다.
     *
     * @param dto 생성할 일정 정보
     * @return 생성된 일정에 대한 응답 DTO
     */
    CalendarResponseDto createSchedule(CalendarRequestDto dto);

    /**
     * 모든 일정을 조회하는 메서드이다.
     *
     * @param dto 검색 조건
     * @return 검색 조건에 맞는 일정 목록
     */
    List<CalendarResponseDto> findAllSchedules(SearchCalendarRequestDto dto);

    /**
     * 특정 일정을 조회하는 메서드이다.
     *
     * @param id 조회할 일정의 id
     * @return 조회된 일정에 대한 응답 DTO
     */
    CalendarResponseDto findScheduleById(Long id);

    /**
     * 특정 일정을 수정하는 메서드이다.
     *
     * @param id 수정할 일정의 id
     * @param dto 수정할 일정 정보
     * @return 수정된 일정에 대한 응답 DTO
     */
    CalendarResponseDto updateSchedule(Long id, CalendarRequestDto dto);

    /**
     * 특정 일정을 삭제하는 메서드이다.
     *
     * @param id 삭제할 일정의 id
     * @param dto 작성자의 정보
     */
    void deleteSchedule(Long id, DeleteCalendarRequestDto dto);
}
