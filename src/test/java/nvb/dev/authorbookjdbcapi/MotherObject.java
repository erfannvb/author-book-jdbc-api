package nvb.dev.authorbookjdbcapi;

import nvb.dev.authorbookjdbcapi.domain.Author;

public class MotherObject {
    public static Author createTestAuthor() {
        return Author.builder()
                .id(1L)
                .name("John Doe")
                .age(35)
                .build();
    }
}
