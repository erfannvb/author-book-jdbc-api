package nvb.dev.authorbookjdbcapi.service.impl;

import lombok.AllArgsConstructor;
import nvb.dev.authorbookjdbcapi.dao.AuthorDao;
import nvb.dev.authorbookjdbcapi.domain.Author;
import nvb.dev.authorbookjdbcapi.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    public void saveAuthor(Author author) {
        authorDao.create(author);
    }

    @Override
    public Optional<Author> findAuthorById(long id) {
        return Optional.ofNullable(authorDao.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public List<Author> findAllAuthors() {
        List<Author> authorList = authorDao.findAll();
        if (!authorList.isEmpty()) {
            return authorList.stream().toList();
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void updateAuthor(long id, Author author) {
        Optional<Author> optionalAuthor = authorDao.findById(id);
        if (optionalAuthor.isPresent()) {

            Author currentAuthor = optionalAuthor.get();

            currentAuthor.setId(author.getId());
            currentAuthor.setName(author.getName());
            currentAuthor.setAge(author.getAge());

            authorDao.create(currentAuthor);

        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void deleteAuthorById(long id) {
        Optional<Author> optionalAuthor = authorDao.findById(id);
        if (optionalAuthor.isPresent()) {
            authorDao.deleteById(id);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
