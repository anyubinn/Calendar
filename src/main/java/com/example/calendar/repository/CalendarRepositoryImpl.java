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

@Repository
public class CalendarRepositoryImpl implements CalendarRepository {

    private final JdbcTemplate jdbcTemplate;

    public CalendarRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public CalendarResponseDto saveSchedule(Calendar calendar, Writer writer, Long writerId) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("calendar").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("todo", calendar.getTodo());
        parameters.put("reg_date", calendar.getRegDate());
        parameters.put("mod_date", calendar.getModDate());
        parameters.put("writer_id", writerId);

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new CalendarResponseDto(key.longValue(), calendar.getTodo(), writer.getWriterName(),
                calendar.getRegDate(), calendar.getModDate());
    }

    @Override
    public List<CalendarResponseDto> findAllSchedules() {

        return jdbcTemplate.query(
                "select * from calendar c join writer w on c.writer_id = w.writer_id order by c.mod_date desc",
                calendarRowMapper());
    }

    @Override
    public List<CalendarResponseDto> findAllSchedules(LocalDate modDate, String writerName, Long writerId,
                                                      String email) {

        String sql = "select * from calendar c join writer w on c.writer_id = w.writer_id where (date(c.mod_date) = ? or ? is null) and (writer_name = ? or ? is null) and (w.writer_id = ? or ? is null) and (email = ? or ? is null) order by c.mod_date desc";

        return jdbcTemplate.query(sql,
                calendarRowMapper(), modDate != null ? Date.valueOf(modDate) : null, modDate, writerName, writerName,
                writerId, writerId, email, email);
    }

    @Override
    public CalendarResponseDto findScheduleByIdOrElseThrow(Long id) {

        List<CalendarResponseDto> result = jdbcTemplate.query(
                "select * from calendar c join writer w on c.writer_id = w.writer_id where id = ?", calendarRowMapper(),
                id);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 작성글이 존재하지 않습니다."));
    }

    @Override
    public int updateSchedule(Long id, String todo, LocalDateTime modDate) {

            return jdbcTemplate.update(
                    "update calendar set todo = ?, mod_date = ? where id = ?", todo, modDate, id);
    }

    @Override
    public int deleteSchedule(Long id, String email, String password) {

        return jdbcTemplate.update("delete from calendar where id = ?", id);
    }

    @Override
    public Long findWriterIdByScheduleId(Long id) {

        List<Long> writerId = jdbcTemplate.query("select writer_id from calendar where id = ?",
                (rs, rowNum) -> rs.getLong("writer_id"), id);

        return writerId.isEmpty() ? null : writerId.get(0);
    }

    @Override
    public Long findWriterIdByNameAndPassword(String writerName, String password) {

        List<Long> writerId = jdbcTemplate.query("select writer_id from writer where writer_name = ? and password = ?",
                (rs, rowNum) -> rs.getLong("writer_id"), writerName, password);

        return writerId.isEmpty() ? null : writerId.get(0);
    }

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
