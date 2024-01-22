package nvb.dev.authorbookjdbcapi.service;

import nvb.dev.authorbookjdbcapi.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    void saveAuthor(Author author);

    Optional<Author> findAuthorById(long id);

    List<Author> findAllAuthors();

    void updateAuthor(long id, Author author);

    void deleteAuthorById(long id);

}
