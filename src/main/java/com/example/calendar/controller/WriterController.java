package com.example.calendar.controller;

import com.example.calendar.dto.WriterRequestDto;
import com.example.calendar.dto.WriterResponseDto;
import com.example.calendar.service.WriterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<WriterResponseDto> registerWriter(@RequestBody WriterRequestDto dto) {

        return new ResponseEntity<>(writerService.registerWriter(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{writerId}")
    public ResponseEntity<WriterResponseDto> updateWriter(@PathVariable Long writerId,
                                                          @RequestBody WriterRequestDto dto) {

        return new ResponseEntity<>(writerService.updateWriter(writerId, dto), HttpStatus.OK);
    }
}
