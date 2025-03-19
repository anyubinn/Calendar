package com.example.calendar.controller;

import com.example.calendar.dto.CalendarRequestDto;
import com.example.calendar.dto.CalendarResponseDto;
import com.example.calendar.service.CalendarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/calendars")
@RestController
public class CalendarController {

    private CalendarService calendarService;

    @PostMapping
    public ResponseEntity<CalendarResponseDto> createSchedule(@RequestBody CalendarRequestDto dto) {

        return new ResponseEntity<>(calendarService.createSchedule(dto), HttpStatus.CREATED);
    }
}
