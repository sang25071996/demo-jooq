package jooq.demo.com.demojooq.repository;

import static jooq.demo.com.demojooq.Tables.AUTHOR;
import static jooq.demo.com.demojooq.Tables.AUTHOR_BOOK;
import static jooq.demo.com.demojooq.Tables.BOOK;
import static org.jooq.impl.DSL.trueCondition;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import jooq.demo.com.demojooq.dto.BookAuthorDto;
import jooq.demo.com.demojooq.entites.Author;
import jooq.demo.com.demojooq.request.Pagination;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.SortField;
import org.jooq.Table;
import org.jooq.TableField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class AuthorRepositiory {

    private final DSLContext dslContext;

    public AuthorRepositiory(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public Author findById(int id) {
        return dslContext.selectFrom(AUTHOR).where(AUTHOR.ID.equal(id)).fetchOneInto(Author.class);
    }

    @SuppressWarnings("rawtypes")
    public Page pagination(Pagination<BookAuthorDto> pagination) {
        int size = pagination.getSize();
        int page = pagination.getPage();
        Sort sort = null;
        Pageable pageable;
        if (ObjectUtils.isEmpty(pagination.getSortField())) {
            sort = null;
            pageable = PageRequest.of(page, size);
        } else {
            sort = Sort.by(pagination.getDirection(), pagination.getSortField().toUpperCase());
            pageable = PageRequest.of(page, size, sort);
        }

        long offset = pageable.getOffset();
        Collection<SortField<?>> sortFields = sortField(sort);
        List<BookAuthorDto> authors = dslContext.select(AUTHOR.ID, AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME, BOOK.TITLE).from(AUTHOR)
                .innerJoin(AUTHOR_BOOK).on(AUTHOR.ID.eq(AUTHOR_BOOK.AUTHOR_ID))
                .innerJoin(BOOK).on(AUTHOR_BOOK.BOOK_ID.eq(BOOK.ID))
                .where(condition(pagination.getModel()))
                .orderBy(sortFields)
                .limit(size).offset(offset).fetchInto(BookAuthorDto.class);
        long total = dslContext.fetchCount(dslContext.select(AUTHOR.ID, AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME, BOOK.TITLE).from(AUTHOR)
                .innerJoin(AUTHOR_BOOK).on(AUTHOR.ID.eq(AUTHOR_BOOK.AUTHOR_ID))
                .innerJoin(BOOK).on(AUTHOR_BOOK.BOOK_ID.eq(BOOK.ID))
                .where(condition(pagination.getModel())));
        return new PageImpl(authors, pageable, total);
    }

    protected Condition condition(BookAuthorDto author) {
        Condition condition = trueCondition();
        if (StringUtils.isNotEmpty(author.getFirstName())) {
            condition = condition.and(AUTHOR.FIRST_NAME.likeIgnoreCase("%" + author.getFirstName() + "%"));
        }
        if (StringUtils.isNotEmpty(author.getLastName())) {
            condition = condition.and(AUTHOR.LAST_NAME.likeIgnoreCase("%" + author.getLastName() + "%"));
        }
        if (StringUtils.isNotEmpty(author.getTitle())) {
            condition = condition.and(BOOK.TITLE.likeIgnoreCase("%" + author.getTitle() + "%"));
        }
        return condition;
    }

    protected Collection<SortField<?>> sortField(Sort sort) {
        Collection<SortField<?>> sortFields = new ArrayList<>();
        if (sort == null) {
            return sortFields;
        }
        Iterator<Sort.Order> iterator = sort.iterator();
        try {
            while(iterator.hasNext()) {
                Sort.Order order = iterator.next();
                String fieldName = order.getProperty();
                Sort.Direction direction = order.getDirection();

                Map<Table, Table> map = new HashMap<>();
                map.put(AUTHOR, AUTHOR);
                map.put(BOOK, BOOK);
                for (Map.Entry<Table, Table> entry : map.entrySet()) {
                    Table table = entry.getKey();
                    SortField<?> sortField = getSortField(fieldName, table, direction);
                    if (ObjectUtils.isNotEmpty(sortField)) {
                        sortFields.add(sortField);
                    }
                }
            }
        } catch(Exception ex) {
            log.error("Error get sort field: {}", ex.getMessage());
        }

        return sortFields;
    }

    private SortField<?> getSortField(String fieldName, Table table, Sort.Direction direction) {
        TableField tableField;
        SortField<?> sortField = null;
        try {
            Field field = table.getClass().getField(fieldName);
            tableField = (TableField) field.get(table);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            tableField = null;
        }
        if (ObjectUtils.isNotEmpty(tableField)) {
            if (direction == Sort.Direction.ASC) {
                sortField = tableField.asc();
            } else {
                sortField = tableField.desc();
            }
        }
        return sortField;
    }

}
