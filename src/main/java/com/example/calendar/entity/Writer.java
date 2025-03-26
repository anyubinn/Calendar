package com.example.calendar.entity;

import java.time.LocalDateTime;
import lombok.Getter;

/**
 * 작성자에 대한 정보를 담고 있는 클래스
 */
@Getter
public class Writer {

    private Long writerId;
    private String writerName;
    private String password;
    private String email;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public Writer(String writerName, String password, String email, LocalDateTime regDate, LocalDateTime modDate) {
        this.writerName = writerName;
        this.password = password;
        this.email = email;
        this.regDate = regDate;
        this.modDate = modDate;
    }

    public Writer(String writerName, String password) {
        this.writerName = writerName;
        this.password = password;
    }
}
