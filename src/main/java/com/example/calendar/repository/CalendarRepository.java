package com.example.calendar.repository;

import com.example.calendar.dto.CalendarResponseDto;
import com.example.calendar.entity.Calendar;
import com.example.calendar.entity.Writer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CalendarRepository {

    CalendarResponseDto saveSchedule(Calendar calendar, Writer writer, Long writerId);

    List<CalendarResponseDto> findAllSchedules();

    List<CalendarResponseDto> findAllSchedules(LocalDate modDate, String writerName, Long writerId, String email);

    CalendarResponseDto findScheduleByIdOrElseThrow(Long id);

    int updateSchedule(Long id, String todo, LocalDateTime modDate);

    int deleteSchedule(Long id, String email, String password);

    Long findWriterIdByScheduleId(Long id);

    Long findWriterIdByNameAndPassword(String writerName, String password);

    Long findWriterIdByEmailAndPassword(String email, String password);
}
