package com.example.calendar.service;

import com.example.calendar.dto.DeleteWriterRequestDto;
import com.example.calendar.dto.WriterRequestDto;
import com.example.calendar.dto.WriterResponseDto;

public interface WriterService {

    WriterResponseDto registerWriter(WriterRequestDto dto);

    WriterResponseDto updateWriter(Long writerId, WriterRequestDto dto);

    void deleteWriter(Long writerId, DeleteWriterRequestDto dto);
}
