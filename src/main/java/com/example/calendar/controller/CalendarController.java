package com.example.calendar.controller;

import com.example.calendar.dto.CalendarRequestDto;
import com.example.calendar.dto.CalendarResponseDto;
import com.example.calendar.service.CalendarService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/calendars")
@RestController
public class CalendarController {

    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @PostMapping
    public ResponseEntity<CalendarResponseDto> createSchedule(@RequestBody CalendarRequestDto dto) {

        return new ResponseEntity<>(calendarService.createSchedule(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<CalendarResponseDto> findAllSchedules() {

        return calendarService.findAllSchedules();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalendarResponseDto> findScheduleById(@PathVariable Long id) {

        return new ResponseEntity<>(calendarService.findScheduleById(id), HttpStatus.OK);
    }
}
