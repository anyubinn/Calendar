package com.example.calendar.repository;

import com.example.calendar.dto.WriterResponseDto;
import com.example.calendar.entity.Writer;
import java.time.LocalDateTime;

public interface WriterRepository {

    /**
     * 새로운 작성자를 추가하는 메서드이다.
     *
     * @param writer 저장할 작성자 정보
     * @return 저장된 작성자에 대한 응답 DTO
     */
    WriterResponseDto registerWriter(Writer writer);

    /**
     * 작성자 id로 작성자 정보를 조회하는 메서드이다.
     * @param writerId 조회할 작성자의 id
     * @return 조회된 작성자에 대한 응답 DTO
     */
    WriterResponseDto findWriterById(Long writerId);

    /**
     * 작성자의 정보를 수정하는 메서드이다.
     *
     * @param writerId 수정할 작성자의 id
     * @param writerName 수정할 작성자명
     * @param password 수정할 비밀번호
     * @param email 수정할 이메일
     * @param modDate 수정일
     * @return 수정된 행 수
     */
    int updateWriter(Long writerId, String writerName, String password, String email, LocalDateTime modDate);

    /**
     * 작성자를 삭제하는 메서드이다.
     *
     * @param writerId 삭제할 작성자의 id
     * @param email 작성자의 이메일
     * @param password 작성자의 비밀번호
     * @return 삭제된 행 수
     */
    int deleteWriter(Long writerId, String email, String password);
}
