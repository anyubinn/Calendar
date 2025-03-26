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

/**
 * 작성자 관련 비즈니스 로직을 처리하는 서비스 구현 클래스
 */
@Service
public class WriterServiceImpl implements WriterService {

    private final WriterRepository writerRepository;

    public WriterServiceImpl(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    /**
     * 새로운 작성자를 추가하는 메서드이다.
     *
     * @param dto 생성할 작성자 정보
     * @return 생성된 작성자에 대한 응답 DTO
     */
    @Override
    public WriterResponseDto registerWriter(WriterRequestDto dto) {

        Writer writer = new Writer(dto.getWriterName(), dto.getPassword(), dto.getEmail(), Timestamp.valueOf(
                LocalDateTime.now()).toLocalDateTime(), Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime());

        return writerRepository.registerWriter(writer);
    }

    /**
     * 특정 작성자를 수정하는 메서드이다.
     *
     * @param writerId 수정할 작성자의 id
     * @param dto 수정할 작성자 정보
     * @return 수정된 작성자에 대한 응답 DTO
     */
    @Transactional
    @Override
    public WriterResponseDto updateWriter(Long writerId, WriterRequestDto dto) {

        int updatedRow = writerRepository.updateWriter(writerId, dto.getWriterName(), dto.getPassword(), dto.getEmail(),
                Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime());

        // 수정된 작성자가 없을 경우
        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 작성자를 찾을 수 없습니다.");
        }

        return writerRepository.findWriterById(writerId);
    }

    /**
     * 특정 작성자를 삭제하는 메서드이다.
     *
     * @param writerId 삭제할 작성자의 id
     * @param dto 작성자의 정보
     */
    @Override
    public void deleteWriter(Long writerId, DeleteWriterRequestDto dto) {

        int updatedRow = writerRepository.deleteWriter(writerId, dto.getEmail(), dto.getPassword());

        // 삭제된 작성자가 없을 경우
        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 작성자를 찾을 수 없습니다.");
        }
    }
}
