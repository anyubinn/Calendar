package com.example.calendar.service;

import com.example.calendar.dto.WriterRequestDto;
import com.example.calendar.dto.WriterResponseDto;
import com.example.calendar.entity.Writer;
import com.example.calendar.repository.WriterRepository;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WriterServiceImpl implements WriterService {

    private final WriterRepository writerRepository;

    public WriterServiceImpl(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    @Override
    public WriterResponseDto registerWriter(WriterRequestDto dto) {

        Writer writer = new Writer(dto.getWriterName(), dto.getPassword(), dto.getEmail(), Timestamp.valueOf(
                LocalDateTime.now()).toLocalDateTime(), Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime());

        return writerRepository.registerWriter(writer);
    }

    @Transactional
    @Override
    public WriterResponseDto updateWriter(Long writerId, WriterRequestDto dto) {

        writerRepository.updateWriter(writerId, dto.getWriterName(), dto.getPassword(), dto.getEmail(),
                Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime());

        return writerRepository.findWriterById(writerId);
    }
}
