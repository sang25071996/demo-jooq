package jooq.demo.com.controller;

import java.util.List;

import jooq.demo.com.dto.BookAuthorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jooq.demo.com.service.BookService;

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
