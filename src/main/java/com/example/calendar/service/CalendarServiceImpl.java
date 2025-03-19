package com.example.calendar.service;

import com.example.calendar.dto.CalendarRequestDto;
import com.example.calendar.dto.CalendarResponseDto;
import com.example.calendar.entity.Calendar;
import com.example.calendar.repository.CalendarRepository;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class CalendarServiceImpl implements CalendarService {

    private final CalendarRepository calendarRepository;

    public CalendarServiceImpl(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    @Override
    public CalendarResponseDto createSchedule(CalendarRequestDto dto) {

        Calendar calendar = new Calendar(dto.getTodo(), dto.getWriterName(), dto.getPassword(), Timestamp.valueOf(
                LocalDateTime.now()).toLocalDateTime(), Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime());

        return calendarRepository.saveSchedule(calendar);
    }
}
