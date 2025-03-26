package com.example.calendar.dto;

import lombok.Getter;

/**
 * 일정 추가, 일정 수정 요청에 사용되는 DTO
 */
@Getter
public class CalendarRequestDto {

    private String todo;
    private String writerName;
    private String password;
}
