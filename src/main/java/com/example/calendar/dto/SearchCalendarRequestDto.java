package com.example.calendar.dto;

import java.time.LocalDate;
import lombok.Getter;

@Getter
public class SearchCalendarRequestDto {

    private LocalDate modDate;
    private String writerName;
}
