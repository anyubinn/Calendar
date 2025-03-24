package com.example.calendar.repository;

import com.example.calendar.dto.RegisterWriterResponseDto;
import com.example.calendar.entity.Writer;

public interface WriterRepository {

    RegisterWriterResponseDto registerWriter(Writer writer);
}
