package jooq.demo.com.repository;

import jooq.demo.com.Tables;
import jooq.demo.com.dto.BookAuthorDto;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {

    private final DSLContext dslContext;

    public BookRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public List<BookAuthorDto> getBookWithAuthor() {

        List<BookAuthorDto> bookAuthors = dslContext.select(Tables.BOOK.ID, Tables.BOOK.TITLE, Tables.AUTHOR.FIRST_NAME, Tables.AUTHOR.LAST_NAME)
                .from(Tables.AUTHOR)
                .join(Tables.BOOK)
                .on(Tables.BOOK.ID.eq(Tables.AUTHOR.ID))
                .fetch().into(BookAuthorDto.class);
        return bookAuthors;
    }
}
