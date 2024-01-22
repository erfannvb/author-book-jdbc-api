package nvb.dev.authorbookjdbcapi.dao;

import nvb.dev.authorbookjdbcapi.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    void create(Author author);

    Optional<Author> findById(long id);

    List<Author> findAll();

    void update(long id, Author author);

    void deleteById(long id);

}
