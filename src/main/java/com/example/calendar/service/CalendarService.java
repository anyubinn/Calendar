package com.example.calendar.service;

import com.example.calendar.dto.CalendarRequestDto;
import com.example.calendar.dto.CalendarResponseDto;
import java.util.List;

public interface CalendarService {

    CalendarResponseDto createSchedule(CalendarRequestDto dto);

    List<CalendarResponseDto> findAllSchedules();

    CalendarResponseDto findScheduleById(Long id);

    CalendarResponseDto updateSchedule(Long id, CalendarRequestDto dto);
}
