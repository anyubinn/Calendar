package com.example.calendar.dto;

import lombok.Getter;

/**
 * 작성자 삭제 요청에 사용되는 DTO
 */
@Getter
public class DeleteWriterRequestDto {

    private String email;
    private String password;
}
