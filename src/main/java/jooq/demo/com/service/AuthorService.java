package jooq.demo.com.service;

import jooq.demo.com.dto.BookAuthorDto;
import jooq.demo.com.entites.Author;
import jooq.demo.com.repository.AuthorRepositiory;
import jooq.demo.com.request.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    private final AuthorRepositiory authorRepositiory;

    public AuthorService(AuthorRepositiory authorRepositiory) {
        this.authorRepositiory = authorRepositiory;
    }

    public Author findById(int id) {
        return this.authorRepositiory.findById(id);
    }

    @SuppressWarnings("rawtypes")
    public Page pagination(Pagination<BookAuthorDto> pagination) {
        return this.authorRepositiory.pagination(pagination);
    }
}
