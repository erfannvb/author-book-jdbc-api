package nvb.dev.authorbookjdbcapi;

import nvb.dev.authorbookjdbcapi.domain.Author;
import nvb.dev.authorbookjdbcapi.domain.Book;

public class MotherObject {
    public static Author createTestAuthor() {
        return Author.builder()
                .id(1L)
                .name("John Doe")
                .age(35)
                .build();
    }

    public static Book createTestBook() {
        return Book.builder()
                .id(1L)
                .isbn("123-456")
                .title("You")
                .authorId(1L)
                .build();
    }
}
