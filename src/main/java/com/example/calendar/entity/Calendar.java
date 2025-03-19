package com.example.calendar.entity;


import java.util.Date;
import lombok.Getter;

@Getter
public class Calendar {

    private Long id;
    private String todo;
    private String writerName;
    private Long password;
    private Date regDate;
    private Date modDate;
}
