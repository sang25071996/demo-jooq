package jooq.demo.com.repository;

import jooq.demo.com.dto.BookAuthorDto;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jooq.demo.com.Tables.AUTHOR;
import static jooq.demo.com.Tables.BOOK;

@Repository
public class BookRepository {

    private final DSLContext dslContext;

    public BookRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public List<BookAuthorDto> getBookWithAuthor() {

        List<BookAuthorDto> bookAuthors = dslContext.select(BOOK.ID,BOOK.TITLE, AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME)
                .from(AUTHOR)
                .join(BOOK)
                .on(BOOK.ID.eq(AUTHOR.ID))
                .fetch().into(BookAuthorDto.class);
        return bookAuthors;
    }
}
