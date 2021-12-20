package jooq.demo.com.service;

import java.util.List;
import jooq.demo.com.dto.BookAuthorDto;
import jooq.demo.com.entites.Book;
import jooq.demo.com.repository.BookRepository;
import jooq.demo.com.request.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public List<BookAuthorDto> getBookWithAuthor() {
    return this.bookRepository.getBookWithAuthor();
  }

  public List<Book> getBooks() {
    return this.bookRepository.getBooks();
  }

  public Page pagination(Pagination<Book> pagination) throws Exception {
    return this.bookRepository.pagination(pagination);
  }

  public Boolean insert(List<Book> books) {
    return this.bookRepository.insert(books);
  }

  public Boolean insert(Book book) {
    return this.bookRepository.insert(book);
  }

  public Boolean update(Book book) {
    return this.bookRepository.update(book);
  }
}
