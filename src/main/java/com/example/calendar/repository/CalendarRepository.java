package com.example.calendar.repository;

import com.example.calendar.dto.CalendarResponseDto;
import com.example.calendar.entity.Calendar;
import com.example.calendar.entity.Writer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CalendarRepository {

    CalendarResponseDto saveSchedule(Calendar calendar, Writer writer);

    List<CalendarResponseDto> findAllSchedules();

    List<CalendarResponseDto> findAllSchedules(LocalDate modDate, String writerName, Long writerId, String email);

    CalendarResponseDto findScheduleById(Long id);

    int updateSchedule(Long id, String todo, String writerName, String password, LocalDateTime modDate);

    int deleteSchedule(Long id, String password);
}
