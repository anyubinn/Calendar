package com.example.calendar.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class Writer {

    private Long writerId;
    private String writerName;
    private String password;
    private String email;

    public Writer(String writerName, String password, String email) {
        this.writerName = writerName;
        this.password = password;
        this.email = email;
    }

    public Writer(String writerName, String password) {
        this.writerName = writerName;
        this.password = password;
    }
}
