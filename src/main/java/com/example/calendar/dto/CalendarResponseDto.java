package com.example.calendar.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CalendarResponseDto {

    private Long id;
    private String todo;
    private String writerName;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
