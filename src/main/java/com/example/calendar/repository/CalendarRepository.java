package com.example.calendar.repository;

import com.example.calendar.dto.CalendarResponseDto;
import com.example.calendar.entity.Calendar;
import java.util.List;

public interface CalendarRepository {

    CalendarResponseDto saveSchedule(Calendar calendar);

    List<CalendarResponseDto> findAllSchedules();
}
