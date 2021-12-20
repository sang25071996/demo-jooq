package jooq.demo.com.filter;

import jooq.demo.com.entites.Author;

public interface AuthorFilter extends FilterBuilder<Author> {

  AuthorFilter id(int id);

  AuthorFilter firstName(String firstName);

  AuthorFilter lastName(String lastName);
}
