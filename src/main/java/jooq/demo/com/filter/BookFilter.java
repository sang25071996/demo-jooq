package jooq.demo.com.filter;

import jooq.demo.com.entites.Book;

public interface BookFilter extends FilterBuilder<Book> {

  BookFilter id(int id);

  BookFilter title(String title);
}
