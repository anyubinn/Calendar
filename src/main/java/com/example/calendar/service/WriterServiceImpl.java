package com.example.calendar.service;

import com.example.calendar.dto.DeleteWriterRequestDto;
import com.example.calendar.dto.WriterRequestDto;
import com.example.calendar.dto.WriterResponseDto;
import com.example.calendar.entity.Writer;
import com.example.calendar.repository.WriterRepository;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

        int updatedRow = writerRepository.updateWriter(writerId, dto.getWriterName(), dto.getPassword(), dto.getEmail(),
                Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime());

        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 작성자를 찾을 수 없습니다.");
        }

        return writerRepository.findWriterById(writerId);
    }

    @Override
    public void deleteWriter(Long writerId, DeleteWriterRequestDto dto) {

        int updatedRow = writerRepository.deleteWriter(writerId, dto.getEmail(), dto.getPassword());

        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 작성자를 찾을 수 없습니다.");
        }
    }
}
