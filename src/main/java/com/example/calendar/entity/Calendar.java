package com.example.calendar.entity;


import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class Calendar {

    private Long id;
    private String todo;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private Long writerId;

    public Calendar(String todo, LocalDateTime regDate, LocalDateTime modDate) {
        this.todo = todo;
        this.regDate = regDate;
        this.modDate = modDate;
    }
}
