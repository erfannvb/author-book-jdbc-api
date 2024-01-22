package nvb.dev.authorbookjdbcapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Author {

    private Long id;
    private String name;
    private Integer age;

}
