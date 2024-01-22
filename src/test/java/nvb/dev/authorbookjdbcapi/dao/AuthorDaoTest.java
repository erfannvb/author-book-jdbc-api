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

import static nvb.dev.authorbookjdbcapi.MotherObject.createTestAuthor;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class AuthorDaoTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    AuthorDaoImpl authorDao;

    @Test
    void testThatSaveAuthorGeneratesCorrectSql() {
        Author author = createTestAuthor();

        authorDao.saveAuthor(author);

        verify(jdbcTemplate).update(
                eq("INSERT INTO tbl_author(id, name, age) VALUES (?, ?, ?)"),
                eq(1L),
                eq("John Doe"),
                eq(35)
        );
    }

    @Test
    void testThatFindAuthorByIdGeneratesCorrectSql() {
        Author author = createTestAuthor();

        authorDao.findAuthorById(author.getId());

        verify(jdbcTemplate).query(
                eq("SELECT * FROM tbl_author WHERE id = ?"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
                eq(1L)
        );
    }

    @Test
    void testThatFindAllAuthorsGeneratesCorrectSql() {
        authorDao.findAllAuthors();

        verify(jdbcTemplate).query(
                eq("SELECT * FROM tbl_author"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any()
        );
    }

    @Test
    void testThatUpdateAuthorGeneratesCorrectSql() {
        Author author = createTestAuthor();

        authorDao.updateAuthor(author.getId(), author);

        verify(jdbcTemplate).update(
                eq("UPDATE tbl_author SET id = ?, name = ?, age = ? WHERE id = ?"),
                eq(1L),
                eq("John Doe"),
                eq(35)
        );
    }

    @Test
    void testThatDeleteAuthorByIdGeneratesCorrectSql() {
        authorDao.deleteAuthorById(1L);

        verify(jdbcTemplate).update(
                eq("DELETE FROM tbl_author WHERE id = ?"),
                eq(1L)
        );
    }

}