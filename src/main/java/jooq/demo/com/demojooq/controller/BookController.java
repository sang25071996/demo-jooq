package jooq.demo.com.demojooq.controller;

import jooq.demo.com.demojooq.dto.BookAuthorDto;
import jooq.demo.com.demojooq.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookAuthorDto>> getBookWithAuthor() {
        return ResponseEntity.ok().body(this.bookService.getBookWithAuthor());
    }
}
