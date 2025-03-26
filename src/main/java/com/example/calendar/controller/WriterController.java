package com.example.calendar.controller;

import com.example.calendar.dto.DeleteWriterRequestDto;
import com.example.calendar.dto.WriterRequestDto;
import com.example.calendar.dto.WriterResponseDto;
import com.example.calendar.service.WriterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 작성자의 CUD를 담당하는 클래스
 */
@RequestMapping("/writers")
@RestController
public class WriterController {

    private final WriterService writerService;

    public WriterController(WriterService writerService) {
        this.writerService = writerService;
    }

    /**
     * 새로운 작성자를 등록한다.
     *
     * @param dto 등록할 작성자 정보를 담고 있는 DTO
     * @return 생성된 작성자 정보와 HTTP 상태(CREATED)
     */
    @PostMapping
    public ResponseEntity<WriterResponseDto> registerWriter(@RequestBody WriterRequestDto dto) {

        return new ResponseEntity<>(writerService.registerWriter(dto), HttpStatus.CREATED);
    }

    /**
     * 특정 작성자 정보를 수정한다.
     *
     * @param writerId 수정할 작성자의 id
     * @param dto 수정할 작성자의 정보를 담고 있는 DTO
     * @return 수정된 작성자 정보와 HTTP 상태(OK)
     */
    @PutMapping("/{writerId}")
    public ResponseEntity<WriterResponseDto> updateWriter(@PathVariable Long writerId,
                                                          @RequestBody WriterRequestDto dto) {

        return new ResponseEntity<>(writerService.updateWriter(writerId, dto), HttpStatus.OK);
    }

    /**
     * 특정 작성자를 삭제한다.
     *
     * @param writerId 삭제할 작성자의 id
     * @param dto 삭제를 위해 필요한 작성자 정보를 담고 있는 DTO
     * @return 삭제 성공 시 HTTP 상태(OK)
     */
    @DeleteMapping("/{writerId}")
    public ResponseEntity<Void> deleteWriter(@PathVariable Long writerId, @RequestBody DeleteWriterRequestDto dto) {

        writerService.deleteWriter(writerId, dto);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
