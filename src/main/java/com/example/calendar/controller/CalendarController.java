package com.example.calendar.controller;

import com.example.calendar.dto.CalendarRequestDto;
import com.example.calendar.dto.CalendarResponseDto;
import com.example.calendar.dto.DeleteCalendarRequestDto;
import com.example.calendar.dto.SearchCalendarRequestDto;
import com.example.calendar.service.CalendarService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 일정의 CRUD를 담당하는 클래스
 */
@RequestMapping("/calendars")
@RestController
public class CalendarController {

    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    /**
     * 새로운 일정을 추가한다.
     *
     * @param dto 등록할 일정 정보를 담고 있는 DTO
     * @return 생성된 일정 정보와 HTTP 상태(CREATED)
     */
    @PostMapping
    public ResponseEntity<CalendarResponseDto> createSchedule(@RequestBody CalendarRequestDto dto) {

        return new ResponseEntity<>(calendarService.createSchedule(dto), HttpStatus.CREATED);
    }

    /**
     * 모든 일정을 조회한다.
     *
     * @param dto 검색 조건을 담고 있는 DTO, 검색 조건은 필수가 아님
     * @return 일정 정보 목록을 포함한 리스트
     */
    @GetMapping
    public List<CalendarResponseDto> findAllSchedules(@RequestBody(required = false) SearchCalendarRequestDto dto) {

        return calendarService.findAllSchedules(dto);
    }

    /**
     * 특정 일정을 조회한다.
     *
     * @param id 조회할 일정의 id
     * @return 조회된 일정 정보와 HTTP 상태(OK)
     */
    @GetMapping("/{id}")
    public ResponseEntity<CalendarResponseDto> findScheduleById(@PathVariable Long id) {

        return new ResponseEntity<>(calendarService.findScheduleById(id), HttpStatus.OK);
    }

    /**
     * 특정 일정을 수정한다.
     *
     * @param id 수정할 일정의 id
     * @param dto 수정할 일정 정보를 담고 있는 DTO
     * @return 수정된 일정 정보와 HTTP 상태(OK)
     */
    @PutMapping("/{id}")
    public ResponseEntity<CalendarResponseDto> updateSchedule(@PathVariable Long id,
                                                              @RequestBody CalendarRequestDto dto) {

        return new ResponseEntity<>(calendarService.updateSchedule(id, dto), HttpStatus.OK);
    }

    /**
     * 특정 일정을 삭제한다.
     *
     * @param id 삭제할 일정의 id
     * @param dto 삭제를 위해 필요한 작성자 정보를 담고 있는 DTO
     * @return 삭제 성공 시 HTTP 상태(OK)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, @RequestBody DeleteCalendarRequestDto dto) {

        calendarService.deleteSchedule(id, dto);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
