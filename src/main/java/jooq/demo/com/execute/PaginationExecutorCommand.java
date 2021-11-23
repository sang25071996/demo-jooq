package jooq.demo.com.execute;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.jooq.DSLContext;
import org.jooq.SelectConditionStep;
import org.jooq.SortField;
import org.jooq.Table;
import org.jooq.TableField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

@Slf4j
public class PaginationExecutorCommand implements PaginationExecutor {

  private int size;
  private int page;
  private String sortField;
  private Sort.Direction direction;
  private PageRequest pageRequest;
  private Map<Table, Table> map = new HashMap<>();
  private final DSLContext dslContext;

  public PaginationExecutorCommand(DSLContext dslContext) {
    this.dslContext = dslContext;
  }

  public PaginationExecutor size(int size) {
    this.size = size;
    return this;
  }

  public PaginationExecutor page(int page) {
    this.page = page;
    return this;
  }

  public PaginationExecutor sort(String sortField) {
    if (ObjectUtils.isNotEmpty(sortField)) {
      this.sortField = sortField;
    }
    return this;
  }

  public PaginationExecutor direction(Sort.Direction direction) {
    this.direction = direction;
    return this;
  }

  /**
   * @param selectConditionStep
   * @param clazz
   * @return Page
   */
  @Override
  public Page pagination(SelectConditionStep<?> selectConditionStep,
      Class<?> clazz) {
    Collection<SortField<?>> sortFields = sortField(sort(), this.map);
    List<?> list = selectConditionStep.orderBy(sortFields).limit(size()).offset(offset())
        .fetchInto(clazz);
    long count = dslContext.fetchCount(selectConditionStep);
    return new PageImpl(list, this.pageRequest, count);
  }

  @Override
  public PaginationExecutor execute(Map<Table, Table> map) {
    this.pageRequest = ObjectUtils.isEmpty(sortField) ? PageRequest.of(page, size)
        : PageRequest.of(page, size, Sort.by(direction, sortField.toUpperCase()));
    this.map = map;
    return this;
  }

  @Override
  public int size() {
    return this.pageRequest.getPageSize();
  }

  @Override
  public long offset() {
    return this.pageRequest.getOffset();
  }

  @Override
  public Sort sort() {
    return this.pageRequest.getSort();
  }

  protected Collection<SortField<?>> sortField(Sort sort, Map<Table, Table> map) {
    Collection<SortField<?>> sortFields = new ArrayList<>();
    if (sort == null) {
      return sortFields;
    }
    Iterator<Order> iterator = sort.iterator();
    try {
      while (iterator.hasNext()) {
        Sort.Order order = iterator.next();
        String fieldName = order.getProperty();
        Sort.Direction direction = order.getDirection();

        for (Map.Entry<Table, Table> entry : map.entrySet()) {
          Table table = entry.getKey();
          SortField<?> sortField = getSortField(fieldName, table, direction);
          if (ObjectUtils.isNotEmpty(sortField)) {
            sortFields.add(sortField);
          }
        }
      }
    } catch (Exception ex) {
      log.error("Error get sort field: {}", ex.getMessage());
    }

    return sortFields;
  }

  protected SortField<?> getSortField(String fieldName, Table table, Sort.Direction direction) {
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
