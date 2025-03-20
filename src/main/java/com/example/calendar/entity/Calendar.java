package com.example.calendar.entity;


import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class Calendar {

    private Long id;
    private String todo;
    private String writerName;
    private String password;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public Calendar(String todo, String writerName, String password, LocalDateTime regDate, LocalDateTime modDate) {
        this.todo = todo;
        this.writerName = writerName;
        this.password = password;
        this.regDate = regDate;
        this.modDate = modDate;
    }
}
