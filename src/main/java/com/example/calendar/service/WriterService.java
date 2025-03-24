package com.example.calendar.service;

import com.example.calendar.dto.RegisterWriterRequestDto;
import com.example.calendar.dto.RegisterWriterResponseDto;

public interface WriterService {

    RegisterWriterResponseDto registerWriter(RegisterWriterRequestDto dto);
}
