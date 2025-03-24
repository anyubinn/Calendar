package com.example.calendar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterWriterResponseDto {

    private Long writerId;
    private String writerName;
    private String email;
}
