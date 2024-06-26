package nvb.dev.authorbookjdbcapi.dao.impl;

import lombok.AllArgsConstructor;
import nvb.dev.authorbookjdbcapi.dao.AuthorDao;
import nvb.dev.authorbookjdbcapi.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class AuthorDaoImpl implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void create(Author author) {
        jdbcTemplate.update("INSERT INTO tbl_author(name, age) VALUES (?, ?)",
                author.getName(),
                author.getAge()
        );
    }

    @Override
    public Optional<Author> findById(long id) {
        List<Author> query = jdbcTemplate.query("SELECT * FROM tbl_author WHERE id = ?",
                new AuthorRowMapper(), id);
        return query.stream().findFirst();
    }

    @Override
    public List<Author> findAll() {
        return jdbcTemplate.query("SELECT * FROM tbl_author", new AuthorRowMapper());
    }

    @Override
    public void update(long id, Author author) {
        jdbcTemplate.update("UPDATE tbl_author SET name = ?, age = ? WHERE id = ?",
                author.getName(),
                author.getAge(),
                id
        );
    }

    @Override
    public void deleteById(long id) {
        jdbcTemplate.update("DELETE FROM tbl_author WHERE id = ?", id);
    }

    public static class AuthorRowMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Author.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .age(rs.getInt("age"))
                    .build();
        }
    }
}
