package com.example.calendar.entity;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
