package com.example.calendar.repository;

import com.example.calendar.dto.CalendarResponseDto;
import com.example.calendar.entity.Calendar;

public interface CalendarRepository {

    CalendarResponseDto saveSchedule(Calendar calendar);
}
