package com.example.calendar.service;

import com.example.calendar.dto.WriterRequestDto;
import com.example.calendar.dto.WriterResponseDto;

public interface WriterService {

    WriterResponseDto registerWriter(WriterRequestDto dto);

    WriterResponseDto updateWriter(Long writerId, WriterRequestDto dto);
}
