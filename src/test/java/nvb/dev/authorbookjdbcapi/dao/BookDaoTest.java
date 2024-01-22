package nvb.dev.authorbookjdbcapi.dao;

import nvb.dev.authorbookjdbcapi.dao.impl.BookDaoImpl;
import nvb.dev.authorbookjdbcapi.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.ResultSet;
import java.sql.SQLException;

import static nvb.dev.authorbookjdbcapi.MotherObject.createTestBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BookDaoTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @Mock
    ResultSet resultSet;

    @InjectMocks
    BookDaoImpl bookDao;

    @Test
    void testThatSaveBookGeneratesCorrectSql() {
        bookDao.saveBook(createTestBook());

        verify(jdbcTemplate).update(
                eq("INSERT INTO tbl_book(isbn, title, author_id) VALUES (?, ?, ?)"),
                eq("123-456"),
                eq("You"),
                eq(1L)
        );
    }

    @Test
    void testThatFindBookByIdGeneratesCorrectSql() {
        bookDao.findBookById(1L);

        verify(jdbcTemplate).query(
                eq("SELECT * FROM tbl_book WHERE id = ?"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),
                eq(1L)
        );
    }

    @Test
    void testThatFindAllBooksGeneratesCorrectSql() {
        bookDao.findAllBooks();

        verify(jdbcTemplate).query(
                eq("SELECT * FROM tbl_book"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any()
        );
    }

    @Test
    void testThatUpdateBookGeneratesCorrectSql() {
        bookDao.updateBook(1L, createTestBook());

        verify(jdbcTemplate).update(
                eq("UPDATE tbl_book SET id=?, isbn=?, title=?, author_id=? WHERE id=?"),
                eq(1L),
                eq("123-456"),
                eq("You"),
                eq(1L),
                eq(1L)
        );
    }

    @Test
    void testThatDeleteBookByIdGeneratesCorrectSql() {
        bookDao.deleteBookById(1L);

        verify(jdbcTemplate).update(
                eq("DELETE FROM tbl_book WHERE id = ?"),
                eq(1L)
        );
    }

    @Test
    void testRowMapper() throws SQLException {
        when(resultSet.getLong("id")).thenReturn(1L);
        when(resultSet.getString("isbn")).thenReturn("123-456");
        when(resultSet.getString("title")).thenReturn("You");
        when(resultSet.getLong("author_id")).thenReturn(1L);

        BookDaoImpl.BookRowMapper bookRowMapper = new BookDaoImpl.BookRowMapper();

        Book book = bookRowMapper.mapRow(resultSet, 1);

        assertEquals(1L, book.getId());
        assertEquals("123-456", book.getIsbn());
        assertEquals("You", book.getTitle());
        assertEquals(1L, book.getAuthorId());
    }

}