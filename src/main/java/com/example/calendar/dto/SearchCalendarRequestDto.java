package com.example.calendar.dto;

import java.time.LocalDate;
import lombok.Getter;

/**
 * 일정 조회 요청에 사용되는 DTO
 */
@Getter
public class SearchCalendarRequestDto {

    private LocalDate modDate;
    private String writerName;
    private Long writerId;
    private String email;
}
