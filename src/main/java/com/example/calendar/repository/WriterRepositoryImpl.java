package com.example.calendar.repository;

import com.example.calendar.dto.RegisterWriterResponseDto;
import com.example.calendar.entity.Writer;
import java.util.HashMap;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
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
    public RegisterWriterResponseDto registerWriter(Writer writer) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("writer").usingGeneratedKeyColumns("writer_id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("writer_name", writer.getWriterName());
        parameters.put("password", writer.getPassword());
        parameters.put("email", writer.getEmail());
        parameters.put("reg_date", writer.getRegDate());
        parameters.put("mod_date", writer.getModDate());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        return new RegisterWriterResponseDto(key.longValue(), writer.getWriterName(), writer.getEmail(), writer.getRegDate(), writer.getModDate());
    }
}
