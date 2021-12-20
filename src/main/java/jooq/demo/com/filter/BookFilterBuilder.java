package jooq.demo.com.filter;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import jooq.demo.com.entites.Book;

public class BookFilterBuilder implements BookFilter {

  private List<Book> books;
  private Predicate<Book> predicate;

  public BookFilterBuilder(List<Book> books) {
    this.books = books;
  }

  @Override
  public BookFilter id(int id) {
    Predicate<Book> predicateId = s -> s.getId() == id;
    predicate = predicate != null ? predicate.and(predicateId) : predicateId;
    return this;
  }

  @Override
  public BookFilter title(String title) {
    Predicate<Book> predicateId = s -> s.getTitle() == title;
    predicate = predicate != null ? predicate.and(predicateId) : predicateId;
    return this;
  }


  @Override
  public List<Book> filter() {
    return this.books.stream().filter(predicate).collect(Collectors.toList());
  }
}
