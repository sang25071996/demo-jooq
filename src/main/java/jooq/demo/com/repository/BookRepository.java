package jooq.demo.com.repository;

import static jooq.demo.com.tables.Book.BOOK;
import static org.jooq.impl.DSL.trueCondition;

import java.util.ArrayList;
import java.util.List;
import jooq.demo.com.Tables;
import jooq.demo.com.dto.BookAuthorDto;
import jooq.demo.com.entites.Author;
import jooq.demo.com.entites.Book;
import jooq.demo.com.execute.PaginationExecutor;
import jooq.demo.com.execute.PaginationExecutorCommand;
import jooq.demo.com.execute.query.AbstractQueryExecutor;
import jooq.demo.com.filter.BookFilter;
import jooq.demo.com.filter.BookFilterBuilder;
import jooq.demo.com.filter.FilterStrategyBuilder;
import jooq.demo.com.request.Pagination;
import jooq.demo.com.tables.records.AuthorBookRecord;
import jooq.demo.com.tables.records.BookRecord;
import org.apache.commons.lang3.StringUtils;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.SelectConditionStep;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BookRepository extends AbstractQueryExecutor {

  private PaginationExecutor paginationExecutor;
  private FilterStrategyBuilder filterStrategyBuilder = new FilterStrategyBuilder();

  public BookRepository(DSLContext dslContext) {
    super(dslContext);
    this.paginationExecutor = new PaginationExecutorCommand(dslContext);
  }

  public List<BookAuthorDto> getBookWithAuthor() {

    List<BookAuthorDto> bookAuthors = dslContext.select(Tables.BOOK.ID, Tables.BOOK.TITLE,
            Tables.AUTHOR.FIRST_NAME, Tables.AUTHOR.LAST_NAME)
        .from(Tables.AUTHOR)
        .join(Tables.BOOK)
        .on(Tables.BOOK.ID.eq(Tables.AUTHOR.ID))
        .fetch().into(BookAuthorDto.class);
    return bookAuthors;
  }

  public List<Book> getBooks() {
    List<Book> books = dslContext.selectFrom(Tables.BOOK).fetchInto(Book.class);
    List<Book> newBooks = filterBooks(books);
    return books;
  }

  private List<Book> filterBooks(List<Book> books) {
    BookFilter bookFilter = new BookFilterBuilder(books).id(2);
    this.filterStrategyBuilder.setFilter(bookFilter);
    return this.filterStrategyBuilder.filter();
  }

  public Page pagination(Pagination<Book> pagination) throws Exception {
    PaginationExecutor paginateExecutor = this.paginationExecutor.size(pagination.getSize())
        .page(pagination.getPage()).execute();
    SelectConditionStep<?> query = dslContext.select(BOOK.ID, BOOK.TITLE).from(Tables.BOOK)
        .where(condition(pagination.getModel()));
    return paginateExecutor.pagination(query, Book.class);
  }

  public Condition condition(Book book) {
    Condition condition = trueCondition();
    if (StringUtils.isNotEmpty(book.getTitle())) {
      condition = condition.and(
          Tables.BOOK.TITLE.likeIgnoreCase("%" + book.getTitle() + "%"));
    }
    return condition;
  }

  @Transactional
  public Boolean insert(List<Book> books) {
    List<BookRecord> bookRecords = new ArrayList<>();
    for (Book book : books) {
      BookRecord bookRecord = new BookRecord();
      bookRecord.setTitle(book.getTitle());
      bookRecords.add(bookRecord);
    }
    batchInsert(bookRecords);
    return true;
  }

  @Transactional
  public int update(List<Book> books) {
    List<BookRecord> bookRecords = new ArrayList<>();
    for (Book book : books) {
      BookRecord bookRecord = new BookRecord();
      bookRecord.setTitle(book.getTitle());
      bookRecords.add(bookRecord);
    }
    return batchUpdate(bookRecords);
  }

  @Transactional
  public Boolean insert(Book book) {
    BookRecord bookRecord = new BookRecord();
    bookRecord.setTitle(book.getTitle());
    BookRecord newBookRecord = insert(Tables.BOOK, bookRecord);
    List<AuthorBookRecord> authorBookRecords = new ArrayList<>();
    for (Author author : book.getAuthors()) {
      AuthorBookRecord authorBookRecord = new AuthorBookRecord();
      authorBookRecord.setAuthorId(author.getId());
      authorBookRecord.setBookId(newBookRecord.getId());
      authorBookRecords.add(authorBookRecord);
    }
    insert(Tables.AUTHOR_BOOK, authorBookRecords);
    return true;
  }

  @Transactional
  public Boolean update(Book book) {
    BookRecord bookRecord = new BookRecord();
    bookRecord.setId(book.getId());
    bookRecord.setTitle(book.getTitle());
    update(bookRecord);
    return true;
  }
}
