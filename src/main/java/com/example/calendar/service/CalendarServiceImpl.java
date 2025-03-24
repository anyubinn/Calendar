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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CalendarServiceImpl implements CalendarService {

    private final CalendarRepository calendarRepository;

    public CalendarServiceImpl(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    @Override
    public CalendarResponseDto createSchedule(CalendarRequestDto dto) {

        Writer writer = new Writer(dto.getWriterName(), dto.getPassword());

        Calendar calendar = new Calendar(dto.getTodo(), Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime(),
                Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime());

        return calendarRepository.saveSchedule(calendar, writer);
    }

    @Override
    public List<CalendarResponseDto> findAllSchedules(SearchCalendarRequestDto dto) {

        if (dto == null) {
            return calendarRepository.findAllSchedules();
        }

        return calendarRepository.findAllSchedules(dto.getModDate(), dto.getWriterName(), dto.getWriterId(), dto.getEmail());
    }

    @Override
    public CalendarResponseDto findScheduleById(Long id) {

        return calendarRepository.findScheduleById(id);
    }

    @Transactional
    @Override
    public CalendarResponseDto updateSchedule(Long id, CalendarRequestDto dto) {

        calendarRepository.updateSchedule(id, dto.getTodo(), dto.getWriterName(), dto.getPassword(),
                Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime());

        return calendarRepository.findScheduleById(id);
    }

    @Override
    public void deleteSchedule(Long id, DeleteCalendarRequestDto dto) {

        calendarRepository.deleteSchedule(id, dto.getPassword());
    }
}
