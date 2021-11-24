package jooq.demo.com.controller;

import java.util.List;
import jooq.demo.com.dto.BookAuthorDto;
import jooq.demo.com.entites.Book;
import jooq.demo.com.request.Pagination;
import jooq.demo.com.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/pagination")
    public ResponseEntity<Page> pagination(@RequestBody Pagination<Book> pagination) {
        return ResponseEntity.ok().body(this.bookService.pagination(pagination));
    }

    @PostMapping("/batch")
    public ResponseEntity<Boolean> insert(@RequestBody List<Book> books) {
        return ResponseEntity.ok().body(this.bookService.insert(books));
    }

    @PostMapping
    public ResponseEntity<Boolean> insert(@RequestBody Book book) {
        return ResponseEntity.ok().body(this.bookService.insert(book));
    }

    @PutMapping
    public ResponseEntity<Boolean> update(@RequestBody Book book) {
        return ResponseEntity.ok().body(this.bookService.update(book));
    }
}
