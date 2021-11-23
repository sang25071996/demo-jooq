package jooq.demo.com.execute;

import java.util.Map;
import org.jooq.SelectConditionStep;
import org.jooq.Table;
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

  @SuppressWarnings("rawtypes")
  PaginationExecutor execute(Map<Table, Table> map);

  int size();

  long offset();

  Sort sort();
}
