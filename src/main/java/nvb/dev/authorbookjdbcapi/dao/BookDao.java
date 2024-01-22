package nvb.dev.authorbookjdbcapi.dao;

import nvb.dev.authorbookjdbcapi.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    void create(Book book);

    Optional<Book> findById(long id);

    List<Book> findAll();

    void update(long id, Book book);

    void deleteById(long id);

}
