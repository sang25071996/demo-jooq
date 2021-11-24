package jooq.demo.com.execute;

import org.jooq.SelectConditionStep;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface PaginationExecutor {

  @SuppressWarnings("rawtypes")
  Page pagination(SelectConditionStep<?> resultQuery,
      Class<?> clazz);

  PaginationExecutor size(int size);

  PaginationExecutor page(int page);

  PaginationExecutor sort(String sortField);

  PaginationExecutor direction(Sort.Direction direction);

  PaginationExecutor execute();

  int size();

  long offset();

  Sort sort();
}
