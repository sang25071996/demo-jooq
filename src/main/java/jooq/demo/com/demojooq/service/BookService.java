package jooq.demo.com.demojooq.service;

import jooq.demo.com.demojooq.dto.BookAuthorDto;
import jooq.demo.com.demojooq.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookAuthorDto> getBookWithAuthor() {
        return this.bookRepository.getBookWithAuthor();
    }
}
