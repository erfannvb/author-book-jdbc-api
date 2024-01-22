package nvb.dev.authorbookjdbcapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Book {

    private Long id;
    private String isbn;
    private String title;
    private Long authorId;

}
