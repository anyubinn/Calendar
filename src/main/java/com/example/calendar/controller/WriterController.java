package com.example.calendar.controller;

import com.example.calendar.dto.RegisterWriterRequestDto;
import com.example.calendar.dto.RegisterWriterResponseDto;
import com.example.calendar.service.WriterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/writers")
@RestController
public class WriterController {

    private final WriterService writerService;

    public WriterController(WriterService writerService) {
        this.writerService = writerService;
    }

    @PostMapping
    public ResponseEntity<RegisterWriterResponseDto> registerWriter(@RequestBody RegisterWriterRequestDto dto) {

        return new ResponseEntity<>(writerService.registerWriter(dto), HttpStatus.CREATED);
    }
}
