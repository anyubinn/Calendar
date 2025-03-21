package com.example.calendar.repository;

import com.example.calendar.dto.CalendarResponseDto;
import com.example.calendar.entity.Calendar;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class CalendarRepositoryImpl implements CalendarRepository {

    private final JdbcTemplate jdbcTemplate;

    public CalendarRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public CalendarResponseDto saveSchedule(Calendar calendar) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("calendar").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("todo", calendar.getTodo());
        parameters.put("writer_name", calendar.getWriterName());
        parameters.put("password", calendar.getPassword());
        parameters.put("reg_date", calendar.getRegDate());
        parameters.put("mod_date", calendar.getModDate());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new CalendarResponseDto(key.longValue(), calendar.getTodo(), calendar.getWriterName(),
                calendar.getRegDate(), calendar.getModDate());
    }

    @Override
    public List<CalendarResponseDto> findAllSchedules() {

        return jdbcTemplate.query("select * from calendar order by mod_date desc", calendarRowMapper());
    }

    @Override
    public List<CalendarResponseDto> findAllSchedules(LocalDate modDate, String writerName) {

        String sql = "SELECT * FROM calendar WHERE (DATE(mod_date) = ? OR ? IS NULL) AND (writer_name = ? OR ? IS NULL) ORDER BY mod_date DESC";

        return jdbcTemplate.query(sql,
                calendarRowMapper(), modDate != null ? Date.valueOf(modDate) : null, modDate, writerName, writerName);
    }

    @Override
    public CalendarResponseDto findScheduleById(Long id) {

        return jdbcTemplate.queryForObject("select * from calendar where id = ?", calendarRowMapper(), id);
    }

    @Override
    public int updateSchedule(Long id, String todo, String writerName, String password, LocalDateTime modDate) {

        return jdbcTemplate.update(
                "update calendar set todo = ?, writer_name = ?, mod_date = ? where id = ? and password = ?", todo,
                writerName, modDate, id, password);
    }

    @Override
    public int deleteSchedule(Long id, String password) {

        return jdbcTemplate.update("delete from calendar where id = ? and password = ?", id, password);
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
