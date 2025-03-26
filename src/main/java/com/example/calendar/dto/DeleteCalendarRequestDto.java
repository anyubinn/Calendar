package com.example.calendar.dto;

import lombok.Getter;

/**
 * 일정 삭제 요청에 사용되는 DTO
 */
@Getter
public class DeleteCalendarRequestDto {

    private String email;
    private String password;
}
