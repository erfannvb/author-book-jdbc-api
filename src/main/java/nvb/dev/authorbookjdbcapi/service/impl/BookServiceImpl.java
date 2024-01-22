package nvb.dev.authorbookjdbcapi.service.impl;

import lombok.AllArgsConstructor;
import nvb.dev.authorbookjdbcapi.dao.AuthorDao;
import nvb.dev.authorbookjdbcapi.dao.BookDao;
import nvb.dev.authorbookjdbcapi.domain.Author;
import nvb.dev.authorbookjdbcapi.domain.Book;
import nvb.dev.authorbookjdbcapi.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final AuthorDao authorDao;
    private final BookDao bookDao;

    @Override
    public void saveBook(Book book) {
        Optional<Author> optionalAuthor = authorDao.findById(book.getAuthorId());
        if (optionalAuthor.isPresent()) {
            bookDao.create(book);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Optional<Book> findBookById(long id) {
        return Optional.ofNullable(bookDao.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public List<Book> findAllBooks() {
        List<Book> bookList = bookDao.findAll();
        if (!bookList.isEmpty()) {
            return bookList.stream().toList();
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void updateBook(long id, Book book) {
        Optional<Book> optionalBook = bookDao.findById(id);
        if (optionalBook.isPresent()) {

            Book currentBook = optionalBook.get();

            currentBook.setId(book.getId());
            currentBook.setIsbn(book.getIsbn());
            currentBook.setTitle(book.getTitle());
            currentBook.setAuthorId(book.getAuthorId());

            bookDao.update(id, currentBook);

        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void deleteBookById(long id) {
        Optional<Book> optionalBook = bookDao.findById(id);
        if (optionalBook.isPresent()) {
            bookDao.deleteById(id);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
