package jooq.demo.com.service;

import jooq.demo.com.dto.BookAuthorDto;
import jooq.demo.com.repository.BookRepository;
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
