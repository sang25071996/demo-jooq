package jooq.demo.com.controller;

import java.util.List;
import jooq.demo.com.dto.BookAuthorDto;
import jooq.demo.com.entites.Author;
import jooq.demo.com.request.Pagination;
import jooq.demo.com.service.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

  @PostMapping
  public ResponseEntity<Integer> insert(@RequestBody List<Author> authors) {
    return ResponseEntity.ok().body(this.authorService.insert(authors));
  }
}
