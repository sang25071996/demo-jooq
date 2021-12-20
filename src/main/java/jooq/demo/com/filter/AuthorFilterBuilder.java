package jooq.demo.com.filter;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import jooq.demo.com.entites.Author;

public class AuthorFilterBuilder implements AuthorFilter {

  private List<Author> authors;
  private Predicate<Author> predicate;

  public AuthorFilterBuilder(List<Author> authors) {
    this.authors = authors;
  }

  @Override
  public AuthorFilter id(int id) {
    Predicate<Author> predicateId = s -> s.getId() == id;
    predicate = predicate != null ? predicate.and(predicateId) : predicateId;
    return this;
  }

  @Override
  public AuthorFilter firstName(String firstName) {
    Predicate<Author> predicateFirstName = s -> s.getFirstName().equals(firstName);
    predicate = predicate != null ? predicate.and(predicateFirstName) : predicateFirstName;
    return this;
  }

  @Override
  public AuthorFilter lastName(String lastName) {
    Predicate<Author> predicateLastName = s -> s.getLastName().equals(lastName);
    predicate = predicate == null ? predicate.and(predicateLastName) : predicateLastName;
    return this;
  }

  @Override
  public List<Author> filter() {
    return this.authors.stream().filter(predicate).collect(Collectors.toList());
  }
}
