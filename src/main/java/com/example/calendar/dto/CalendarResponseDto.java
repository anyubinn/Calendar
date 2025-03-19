package com.example.calendar.dto;

import java.util.Date;
import lombok.Getter;

@Getter
public class CalendarResponseDto {

    private Long id;
    private String todo;
    private String writerName;
    private Date regDate;
    private Date modDate;
}
