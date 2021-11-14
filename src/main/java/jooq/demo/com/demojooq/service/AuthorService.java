package jooq.demo.com.demojooq.service;

import jooq.demo.com.demojooq.dto.BookAuthorDto;
import jooq.demo.com.demojooq.entites.Author;
import jooq.demo.com.demojooq.repository.AuthorRepositiory;
import jooq.demo.com.demojooq.request.Pagination;
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
