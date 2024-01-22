package nvb.dev.authorbookjdbcapi.controller;

import lombok.AllArgsConstructor;
import nvb.dev.authorbookjdbcapi.domain.Author;
import nvb.dev.authorbookjdbcapi.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping("/author")
    public ResponseEntity<HttpStatus> saveAuthor(@RequestBody Author author) {
        authorService.saveAuthor(author);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<Optional<Author>> findAuthorById(@PathVariable long authorId) {
        return new ResponseEntity<>(authorService.findAuthorById(authorId), HttpStatus.OK);
    }

    @GetMapping("/author/all")
    public ResponseEntity<List<Author>> findAllAuthors() {
        return new ResponseEntity<>(authorService.findAllAuthors(), HttpStatus.OK);
    }

    @PutMapping("/author/{authorId}")
    public ResponseEntity<HttpStatus> updateAuthor(@PathVariable long authorId, @RequestBody Author author) {
        authorService.updateAuthor(authorId, author);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/author/{authorId}")
    public ResponseEntity<HttpStatus> deleteAuthorById(@PathVariable long authorId) {
        authorService.deleteAuthorById(authorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
