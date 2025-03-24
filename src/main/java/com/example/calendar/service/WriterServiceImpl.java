package com.example.calendar.service;

import com.example.calendar.dto.RegisterWriterRequestDto;
import com.example.calendar.dto.RegisterWriterResponseDto;
import com.example.calendar.entity.Writer;
import com.example.calendar.repository.WriterRepository;
import org.springframework.stereotype.Service;

@Service
public class WriterServiceImpl implements WriterService {

    private final WriterRepository writerRepository;

    public WriterServiceImpl(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    @Override
    public RegisterWriterResponseDto registerWriter(RegisterWriterRequestDto dto) {

        Writer writer = new Writer(dto.getWriterName(), dto.getPassword(), dto.getEmail());
        return writerRepository.registerWriter(writer);
    }
}
