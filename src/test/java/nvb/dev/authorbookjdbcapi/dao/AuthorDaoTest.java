package nvb.dev.authorbookjdbcapi.dao;

import nvb.dev.authorbookjdbcapi.dao.impl.AuthorDaoImpl;
import nvb.dev.authorbookjdbcapi.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.ResultSet;
import java.sql.SQLException;

import static nvb.dev.authorbookjdbcapi.MotherObject.createTestAuthor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AuthorDaoTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @Mock
    ResultSet resultSet;

    @InjectMocks
    AuthorDaoImpl authorDao;

    @Test
    void testThatSaveAuthorGeneratesCorrectSql() {
        Author author = createTestAuthor();

        authorDao.create(author);

        verify(jdbcTemplate).update(
                eq("INSERT INTO tbl_author(name, age) VALUES (?, ?)"),
                eq("John Doe"),
                eq(35)
        );
    }

    @Test
    void testThatFindAuthorByIdGeneratesCorrectSql() {
        Author author = createTestAuthor();

        authorDao.findById(author.getId());

        verify(jdbcTemplate).query(
                eq("SELECT * FROM tbl_author WHERE id = ?"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
                eq(1L)
        );
    }

    @Test
    void testThatFindAllAuthorsGeneratesCorrectSql() {
        authorDao.findAll();

        verify(jdbcTemplate).query(
                eq("SELECT * FROM tbl_author"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any()
        );
    }

    @Test
    void testThatUpdateAuthorGeneratesCorrectSql() {
        Author author = createTestAuthor();

        authorDao.update(author.getId(), author);

        verify(jdbcTemplate).update(
                eq("UPDATE tbl_author SET name = ?, age = ? WHERE id = ?"),
                eq("John Doe"),
                eq(35),
                eq(1L)
        );
    }

    @Test
    void testThatDeleteAuthorByIdGeneratesCorrectSql() {
        authorDao.deleteById(1L);

        verify(jdbcTemplate).update(
                eq("DELETE FROM tbl_author WHERE id = ?"),
                eq(1L)
        );
    }

    @Test
    void testRowMapper() throws SQLException {
        when(resultSet.getLong("id")).thenReturn(1L);
        when(resultSet.getString("name")).thenReturn("John Doe");
        when(resultSet.getInt("age")).thenReturn(35);

        AuthorDaoImpl.AuthorRowMapper authorRowMapper = new AuthorDaoImpl.AuthorRowMapper();

        Author author = authorRowMapper.mapRow(resultSet, 1);

        assertEquals(1L, author.getId());
        assertEquals("John Doe", author.getName());
        assertEquals(35, author.getAge());
    }

}