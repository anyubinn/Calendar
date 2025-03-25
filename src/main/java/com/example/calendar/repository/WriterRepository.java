package com.example.calendar.repository;

import com.example.calendar.dto.WriterResponseDto;
import com.example.calendar.entity.Writer;
import java.time.LocalDateTime;

public interface WriterRepository {

    WriterResponseDto registerWriter(Writer writer);

    WriterResponseDto findWriterById(Long writerId);

    int updateWriter(Long writerId, String writerName, String password, String email, LocalDateTime modDate);

    int deleteWriter(Long writerId, String email, String password);
}
