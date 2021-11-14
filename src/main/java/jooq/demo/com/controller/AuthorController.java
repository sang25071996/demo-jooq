package jooq.demo.com.controller;

import jooq.demo.com.dto.BookAuthorDto;
import jooq.demo.com.entites.Author;
import jooq.demo.com.request.Pagination;
import jooq.demo.com.service.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable int id) {
        return ResponseEntity.ok().body(this.authorService.findById(id));
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/pagination")
    public ResponseEntity<Page> pagination(@RequestBody Pagination<BookAuthorDto> pagination) {
        return ResponseEntity.ok().body(this.authorService.pagination(pagination));
    }
}
