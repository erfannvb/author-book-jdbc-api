package nvb.dev.authorbookjdbcapi.dao.impl;

import lombok.AllArgsConstructor;
import nvb.dev.authorbookjdbcapi.dao.BookDao;
import nvb.dev.authorbookjdbcapi.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class BookDaoImpl implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void create(Book book) {
        jdbcTemplate.update("INSERT INTO tbl_book(isbn, title, author_id) VALUES (?, ?, ?)",
                book.getIsbn(),
                book.getTitle(),
                book.getAuthorId()
        );
    }

    @Override
    public Optional<Book> findById(long id) {
        List<Book> query = jdbcTemplate.query("SELECT * FROM tbl_book WHERE id = ?",
                new BookRowMapper(), id);
        return query.stream().findFirst();
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM tbl_book", new BookRowMapper());
    }

    @Override
    public void update(long id, Book book) {
        jdbcTemplate.update("UPDATE tbl_book SET id=?, isbn=?, title=?, author_id=? WHERE id=?",
                book.getId(),
                book.getIsbn(),
                book.getTitle(),
                book.getAuthorId(),
                id);
    }

    @Override
    public void deleteById(long id) {
        jdbcTemplate.update("DELETE FROM tbl_book WHERE id = ?", id);
    }

    public static class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Book.builder()
                    .id(rs.getLong("id"))
                    .isbn(rs.getString("isbn"))
                    .title(rs.getString("title"))
                    .authorId(rs.getLong("author_id"))
                    .build();
        }
    }
}
