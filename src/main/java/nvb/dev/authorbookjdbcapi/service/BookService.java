package nvb.dev.authorbookjdbcapi.service;

import nvb.dev.authorbookjdbcapi.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    void saveBook(Book book);

    Optional<Book> findBookById(long id);

    List<Book> findAllBooks();

    void updateBook(long id, Book book);

    void deleteBookById(long id);

}
