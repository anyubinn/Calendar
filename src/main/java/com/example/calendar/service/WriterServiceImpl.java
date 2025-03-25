package com.example.calendar.service;

import com.example.calendar.dto.RegisterWriterRequestDto;
import com.example.calendar.dto.RegisterWriterResponseDto;
import com.example.calendar.entity.Writer;
import com.example.calendar.repository.WriterRepository;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class WriterServiceImpl implements WriterService {

    private final WriterRepository writerRepository;

    public WriterServiceImpl(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    @Override
    public RegisterWriterResponseDto registerWriter(RegisterWriterRequestDto dto) {

        Writer writer = new Writer(dto.getWriterName(), dto.getPassword(), dto.getEmail(), Timestamp.valueOf(
                LocalDateTime.now()).toLocalDateTime(), Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime());

        return writerRepository.registerWriter(writer);
    }
}
