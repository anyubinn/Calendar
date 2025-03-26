package com.example.calendar.service;

import com.example.calendar.dto.DeleteWriterRequestDto;
import com.example.calendar.dto.WriterRequestDto;
import com.example.calendar.dto.WriterResponseDto;

/**
 * 작성자 관련 비즈니스 로직을 처리하는 서비스 인터페이스
 */
public interface WriterService {

    /**
     * 새로운 작성자를 추가하는 메서드이다.
     *
     * @param dto 생성할 작성자 정보
     * @return 생성된 작성자에 대한 응답 DTO
     */
    WriterResponseDto registerWriter(WriterRequestDto dto);

    /**
     * 특정 작성자를 수정하는 메서드이다.
     *
     * @param writerId 수정할 작성자의 id
     * @param dto 수정할 작성자 정보
     * @return 수정된 작성자에 대한 응답 DTO
     */
    WriterResponseDto updateWriter(Long writerId, WriterRequestDto dto);

    /**
     * 특정 작성자를 삭제하는 메서드이다.
     *
     * @param writerId 삭제할 작성자의 id
     * @param dto 작성자의 정보
     */
    void deleteWriter(Long writerId, DeleteWriterRequestDto dto);
}
