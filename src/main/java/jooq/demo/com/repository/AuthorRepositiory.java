package jooq.demo.com.repository;

import static org.jooq.impl.DSL.trueCondition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jooq.demo.com.Tables;
import jooq.demo.com.dto.BookAuthorDto;
import jooq.demo.com.entites.Author;
import jooq.demo.com.execute.PaginationExecutor;
import jooq.demo.com.execute.PaginationExecutorCommand;
import jooq.demo.com.execute.query.AbstractQueryExecutor;
import jooq.demo.com.request.Pagination;
import jooq.demo.com.tables.Book;
import jooq.demo.com.tables.records.AuthorRecord;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.SelectConditionStep;
import org.jooq.Table;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Slf4j
public class AuthorRepositiory extends AbstractQueryExecutor {

  private PaginationExecutor paginationExecutor;
  public AuthorRepositiory(DSLContext dslContext) {
    super(dslContext);
    paginationExecutor = new PaginationExecutorCommand(dslContext);
  }

  public Author findById(int id) {
    Author author = dslContext.selectFrom(Tables.AUTHOR).where(Tables.AUTHOR.ID.equal(id))
        .fetchOneInto(Author.class);
    return author;
  }

  @SuppressWarnings("rawtypes")
  public Page pagination(Pagination<BookAuthorDto> pagination) {
    Map<Table, Table> tableMap = new HashMap<>();
    tableMap.put(Tables.AUTHOR, Tables.AUTHOR);
    tableMap.put(Book.BOOK, Book.BOOK);
    this.paginationExecutor = this.paginationExecutor
        .size(pagination.getSize()).page(pagination.getPage()).sort(pagination.getSortField())
        .direction(pagination.getDirection()).execute(tableMap);
    SelectConditionStep<?> query = dslContext.select(Tables.AUTHOR.ID, Tables.AUTHOR.FIRST_NAME,
            Tables.AUTHOR.LAST_NAME, Book.BOOK.TITLE).from(
            Tables.AUTHOR)
        .innerJoin(Tables.AUTHOR_BOOK).on(Tables.AUTHOR.ID.eq(Tables.AUTHOR_BOOK.AUTHOR_ID))
        .innerJoin(Book.BOOK).on(Tables.AUTHOR_BOOK.BOOK_ID.eq(Book.BOOK.ID))
        .where(condition(pagination.getModel()));
    return this.paginationExecutor.pagination(query, BookAuthorDto.class);
  }

  protected Condition condition(BookAuthorDto author) {
    Condition condition = trueCondition();
    if (StringUtils.isNotEmpty(author.getFirstName())) {
      condition = condition.and(
          Tables.AUTHOR.FIRST_NAME.likeIgnoreCase("%" + author.getFirstName() + "%"));
    }
    if (StringUtils.isNotEmpty(author.getLastName())) {
      condition = condition.and(
          Tables.AUTHOR.LAST_NAME.likeIgnoreCase("%" + author.getLastName() + "%"));
    }
    if (StringUtils.isNotEmpty(author.getTitle())) {
      condition = condition.and(Book.BOOK.TITLE.likeIgnoreCase("%" + author.getTitle() + "%"));
    }
    return condition;
  }

  @Transactional
  public int insert(List<Author> authors) {
    List<AuthorRecord> tableRecords = new ArrayList<>();

    for (Author author : authors) {
      AuthorRecord authorRecord = new AuthorRecord();
      authorRecord.setFirstName(author.getFirstName());
      authorRecord.setLastName(author.getLastName());
      tableRecords.add(authorRecord);
    }
    return insertBatch(tableRecords);
  }
}
