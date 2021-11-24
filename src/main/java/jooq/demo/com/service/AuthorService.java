package jooq.demo.com.service;

import java.util.List;
import jooq.demo.com.dto.BookAuthorDto;
import jooq.demo.com.entites.Author;
import jooq.demo.com.repository.AuthorRepository;
import jooq.demo.com.request.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorService {

  private final AuthorRepository authorRepositiory;

  public AuthorService(AuthorRepository authorRepositiory) {
    this.authorRepositiory = authorRepositiory;
  }

  public Author findById(int id) {
    return this.authorRepositiory.findById(id);
  }

  @SuppressWarnings("rawtypes")
  public Page pagination(Pagination<BookAuthorDto> pagination) {
    return this.authorRepositiory.pagination(pagination);
  }

  @Transactional
  public int insert(List<Author> authors) {
    return this.authorRepositiory.insert(authors);
  }

  @Transactional
  public int update(List<Author> authors) {
    return this.authorRepositiory.update(authors);
  }
}
