package com.example.calendar.dto;

import lombok.Getter;

/**
 * 작성자 추가, 작성자 수정 요청에 사용되는 DTO
 */
@Getter
public class WriterRequestDto {

    private String writerName;
    private String password;
    private String email;
}
