package nvb.dev.authorbookjdbcapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nvb.dev.authorbookjdbcapi.domain.Book;
import nvb.dev.authorbookjdbcapi.service.BookService;
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

import static nvb.dev.authorbookjdbcapi.MotherObject.createTestBook;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookController.class)
@ExtendWith(SpringExtension.class)
class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BookService bookService;

    @Test
    void testThatBookCanBeSaved() throws Exception {
        Book book = createTestBook();

        doNothing().when(bookService).saveBook(book);

        mockMvc.perform(post("/api/v1/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book))
        ).andExpect(status().isCreated());
    }

    @Test
    void testThatBookCanBeFoundById() throws Exception {
        Book book = createTestBook();

        when(bookService.findBookById(1L)).thenReturn(Optional.of(book));

        mockMvc.perform(get("/api/v1/book/" + 1)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    void testThatBooksCanBeFound() throws Exception {
        Book book = createTestBook();

        when(bookService.findAllBooks()).thenReturn(List.of(book));

        mockMvc.perform(get("/api/v1/book/all")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    void testThatBookCanBeUpdated() throws Exception {
        Book book = createTestBook();

        doNothing().when(bookService).updateBook(1L, book);

        mockMvc.perform(put("/api/v1/book/" + 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book))
        ).andExpect(status().isOk());
    }

    @Test
    void testThatBookCanBeDeleted() throws Exception {
        Book book = createTestBook();

        doNothing().when(bookService).deleteBookById(book.getId());

        mockMvc.perform(delete("/api/v1/book/" + 1)).andExpect(status().isNoContent());
    }

}