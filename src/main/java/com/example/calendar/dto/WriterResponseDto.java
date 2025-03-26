package com.example.calendar.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 작성자 응답에 사용되는 DTO
 */
@Getter
@AllArgsConstructor
public class WriterResponseDto {

    private Long writerId;
    private String writerName;
    private String email;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
