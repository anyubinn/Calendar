package com.example.calendar.repository;

import com.example.calendar.dto.CalendarResponseDto;
import com.example.calendar.entity.Calendar;
import com.example.calendar.entity.Writer;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

/**
 * 일정 관련 db 작업을 처리하는 레포지토리 구현 클래스
 */
@Repository
public class CalendarRepositoryImpl implements CalendarRepository {

    private final JdbcTemplate jdbcTemplate;

    public CalendarRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 새로운 일정을 추가하는 메서드이다.
     *
     * @param calendar 저장할 일정 내용
     * @param writer   일정의 작성자 정보
     * @param writerId 작성자 id
     * @return 저장된 일정에 대한 응답 DTO
     */
    @Override
    public CalendarResponseDto saveSchedule(Calendar calendar, Writer writer, Long writerId) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("calendar").usingGeneratedKeyColumns("id");

        // 일정 정보를 삽입할 파라미터 설정
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("todo", calendar.getTodo());
        parameters.put("reg_date", calendar.getRegDate());
        parameters.put("mod_date", calendar.getModDate());
        parameters.put("writer_id", writerId);

        // 새로운 일정의 id 생성
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new CalendarResponseDto(key.longValue(), calendar.getTodo(), writer.getWriterName(),
                calendar.getRegDate(), calendar.getModDate());
    }

    /**
     * 모든 일정을 조회하는 메서드이다.
     *
     * @return 모든 일정에 대한 응답 DTO 리스트
     */
    @Override
    public List<CalendarResponseDto> findAllSchedules() {

        // 일정과 작성자를 조인하여 조회
        return jdbcTemplate.query(
                "select * from calendar c join writer w on c.writer_id = w.writer_id order by c.mod_date desc",
                calendarRowMapper());
    }

    /**
     * 수정일, 작성자명, 작성자 id, 이메일을 기준으로 모든 일정을 조회하는 메서드이다.
     *
     * @param modDate    조회할 수정일
     * @param writerName 조회할 작성자명
     * @param writerId   조회할 작성자 id
     * @param email      조회할 이메일
     * @return 조건에 맞는 모든 일정에 대한 응답 DTO 리스트
     */
    @Override
    public List<CalendarResponseDto> findAllSchedules(LocalDate modDate, String writerName, Long writerId,
                                                      String email) {

        String sql = "select * from calendar c join writer w on c.writer_id = w.writer_id where (date(c.mod_date) = ? or ? is null) and (writer_name = ? or ? is null) and (w.writer_id = ? or ? is null) and (email = ? or ? is null) order by c.mod_date desc";

        return jdbcTemplate.query(sql,
                calendarRowMapper(), modDate != null ? Date.valueOf(modDate) : null, modDate, writerName, writerName,
                writerId, writerId, email, email);
    }

    /**
     * 득정 일정을 조회하는 메서드이다.
     *
     * @param id 조회할 일정의 Id
     * @return 조회된 일정에 대한 응답 DTO
     */
    @Override
    public CalendarResponseDto findScheduleByIdOrElseThrow(Long id) {

        List<CalendarResponseDto> result = jdbcTemplate.query(
                "select * from calendar c join writer w on c.writer_id = w.writer_id where id = ?", calendarRowMapper(),
                id);

        // 일정이 없다면 예외 발생
        return result.stream().findAny()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 작성글이 존재하지 않습니다."));
    }

    /**
     * 일정을 수정하는 메서드이다.
     *
     * @param id 수정할 일정의 id
     * @param todo 수정할 일정 내용
     * @param modDate 수정일
     * @return 수정된 행 수
     */
    @Override
    public int updateSchedule(Long id, String todo, LocalDateTime modDate) {

        return jdbcTemplate.update(
                "update calendar set todo = ?, mod_date = ? where id = ?", todo, modDate, id);
    }

    /**
     * 특정 일정을 삭제하는 메서드이다.
     *
     * @param id 삭제할 일정의 id
     * @param email 작성자의 이메일
     * @param password 작성자의 비밀번호
     * @return 삭제된 행 수
     */
    @Override
    public int deleteSchedule(Long id, String email, String password) {

        return jdbcTemplate.update("delete from calendar where id = ?", id);
    }

    /**
     * 일정 id로 작성자 id를 조회하는 메서드이다.
     *
     * @param id 일정의 id
     * @return 해당 일정의 작성자 id
     */
    @Override
    public Long findWriterIdByScheduleId(Long id) {

        List<Long> writerId = jdbcTemplate.query("select writer_id from calendar where id = ?",
                (rs, rowNum) -> rs.getLong("writer_id"), id);

        return writerId.isEmpty() ? null : writerId.get(0);
    }

    /**
     * 작성자명과 비밀번호로 작성자 id를 조회하는 메서드이다.
     *
     * @param writerName 작성자명
     * @param password 작성자의 비밀번호
     * @return 작성자 id
     */
    @Override
    public Long findWriterIdByNameAndPassword(String writerName, String password) {

        List<Long> writerId = jdbcTemplate.query("select writer_id from writer where writer_name = ? and password = ?",
                (rs, rowNum) -> rs.getLong("writer_id"), writerName, password);

        return writerId.isEmpty() ? null : writerId.get(0);
    }

    /**
     * 이메일과 비밀번호로 작성자 id를 조회하는 메서드이다.
     *
     * @param email 작성자의 이메일
     * @param password 작성자의 비밀번호
     * @return 작성자 id
     */
    @Override
    public Long findWriterIdByEmailAndPassword(String email, String password) {

        List<Long> writerId = jdbcTemplate.query("select writer_id from writer where email = ? and password = ?",
                (rs, rowNum) -> rs.getLong("writer_id"), email, password);

        return writerId.isEmpty() ? null : writerId.get(0);
    }

    private RowMapper<CalendarResponseDto> calendarRowMapper() {

        return new RowMapper<CalendarResponseDto>() {
            @Override
            public CalendarResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new CalendarResponseDto(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getString("writer_name"),
                        rs.getTimestamp("reg_date").toLocalDateTime(),
                        rs.getTimestamp("mod_date").toLocalDateTime()
                );
            }
        };
    }
}
