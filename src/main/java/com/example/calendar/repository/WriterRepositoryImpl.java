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

/**
 * 작성자 관련 db 작업을 처리하는 레포지토리 구현 클래스
 */
@Repository
public class WriterRepositoryImpl implements WriterRepository {

    private final JdbcTemplate jdbcTemplate;

    public WriterRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 새로운 작성자를 추가하는 메서드이다.
     *
     * @param writer 저장할 작성자 정보
     * @return 저장된 작성자에 대한 응답 DTO
     */
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

    /**
     * 작성자 id로 작성자 정보를 조회하는 메서드이다.
     * @param writerId 조회할 작성자의 id
     * @return 조회된 작성자에 대한 응답 DTO
     */
    @Override
    public WriterResponseDto findWriterById(Long writerId) {

        return jdbcTemplate.queryForObject("select * from writer where writer_id = ?", writerRowMapper(), writerId);
    }

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
    @Override
    public int updateWriter(Long writerId, String writerName, String password, String email, LocalDateTime modDate) {

        return jdbcTemplate.update("update writer set writer_name = ?, password = ?, email = ?, mod_date = ? where writer_id = ?",
                writerName, password, email, modDate, writerId);
    }

    /**
     * 작성자를 삭제하는 메서드이다.
     *
     * @param writerId 삭제할 작성자의 id
     * @param email 작성자의 이메일
     * @param password 작성자의 비밀번호
     * @return 삭제된 행 수
     */
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
