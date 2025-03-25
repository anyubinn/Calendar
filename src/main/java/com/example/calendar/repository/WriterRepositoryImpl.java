package com.example.calendar.repository;

import com.example.calendar.dto.WriterResponseDto;
import com.example.calendar.entity.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class WriterRepositoryImpl implements WriterRepository {

    private final JdbcTemplate jdbcTemplate;

    public WriterRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public WriterResponseDto registerWriter(Writer writer) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("writer").usingGeneratedKeyColumns("writer_id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("writer_name", writer.getWriterName());
        parameters.put("password", writer.getPassword());
        parameters.put("email", writer.getEmail());
        parameters.put("reg_date", writer.getRegDate());
        parameters.put("mod_date", writer.getModDate());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        return new WriterResponseDto(key.longValue(), writer.getWriterName(), writer.getEmail(), writer.getRegDate(),
                writer.getModDate());
    }

    @Override
    public WriterResponseDto findWriterById(Long writerId) {

        return jdbcTemplate.queryForObject("select * from writer where writer_id = ?", writerRowMapper(), writerId);
    }

    @Override
    public int updateWriter(Long writerId, String writerName, String password, String email, LocalDateTime modDate) {

        return jdbcTemplate.update("update writer set writer_name = ?, password = ?, email = ?, mod_date = ? where writer_id = ?",
                writerName, password, email, modDate, writerId);
    }

    @Override
    public int deleteWriter(Long writerId, String email, String password) {

        return jdbcTemplate.update("delete from writer where writer_id = ? and email = ? and password = ?", writerId, email,
                password);
    }

    private RowMapper<WriterResponseDto> writerRowMapper() {

        return new RowMapper<WriterResponseDto>() {
            @Override
            public WriterResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new WriterResponseDto(
                        rs.getLong("writer_id"),
                        rs.getString("writer_name"),
                        rs.getString("email"),
                        rs.getTimestamp("reg_date").toLocalDateTime(),
                        rs.getTimestamp("mod_date").toLocalDateTime()
                );
            }
        };
    }
}
