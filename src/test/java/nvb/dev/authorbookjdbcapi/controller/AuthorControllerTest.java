package nvb.dev.authorbookjdbcapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nvb.dev.authorbookjdbcapi.domain.Author;
import nvb.dev.authorbookjdbcapi.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static nvb.dev.authorbookjdbcapi.MotherObject.createTestAuthor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthorController.class)
@ExtendWith(SpringExtension.class)
class AuthorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    AuthorService authorService;

    @Test
    void testThatAuthorCanBeSaved() throws Exception {
        Author author = createTestAuthor();

        doNothing().when(authorService).saveAuthor(any(Author.class));

        mockMvc.perform(post("/api/v1/author")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(author))
        ).andExpect(status().isCreated());
    }

    @Test
    void testThatAuthorCanBeFoundById() throws Exception {
        Author author = createTestAuthor();

        when(authorService.findAuthorById(1L)).thenReturn(Optional.of(author));

        mockMvc.perform(get("/api/v1/author/" + 1)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    void testThatAuthorsCanBeFound() throws Exception {
        Author author = createTestAuthor();

        when(authorService.findAllAuthors()).thenReturn(List.of(author));

        mockMvc.perform(get("/api/v1/author/all")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    void testThatAuthorIsUpdated() throws Exception {
        Author author = createTestAuthor();

        doNothing().when(authorService).updateAuthor(1L, author);

        mockMvc.perform(put("/api/v1/author/" + 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(author))
        ).andExpect(status().isOk());
    }

    @Test
    void testThatAuthorCanBeDeleted() throws Exception {
        Author author = createTestAuthor();

        doNothing().when(authorService).deleteAuthorById(author.getId());

        mockMvc.perform(delete("/api/v1/author/" + 1)).andExpect(status().isNoContent());
    }

}