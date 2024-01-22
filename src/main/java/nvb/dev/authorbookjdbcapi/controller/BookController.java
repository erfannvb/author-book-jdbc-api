package nvb.dev.authorbookjdbcapi.controller;

import lombok.AllArgsConstructor;
import nvb.dev.authorbookjdbcapi.domain.Book;
import nvb.dev.authorbookjdbcapi.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/book")
    public ResponseEntity<HttpStatus> saveBook(@RequestBody Book book) {
        bookService.saveBook(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<Optional<Book>> findBookById(@PathVariable long bookId) {
        return new ResponseEntity<>(bookService.findBookById(bookId), HttpStatus.OK);
    }

    @GetMapping("/book/all")
    public ResponseEntity<List<Book>> findAllBooks() {
        return new ResponseEntity<>(bookService.findAllBooks(), HttpStatus.OK);
    }

    @PutMapping("/book/{bookId}")
    public ResponseEntity<HttpStatus> updateBook(@PathVariable long bookId, @RequestBody Book book) {
        bookService.updateBook(bookId, book);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/book/{bookId}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable long bookId) {
        bookService.deleteBookById(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
