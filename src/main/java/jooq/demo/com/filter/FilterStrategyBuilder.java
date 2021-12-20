package jooq.demo.com.filter;

import java.util.List;

public class FilterStrategyBuilder<T> implements FilterBuilder<T> {

  private FilterBuilder<T> filterBuilder;

  public void setFilter(FilterBuilder filterBuilder) {
    this.filterBuilder = filterBuilder;
  }

  @Override
  public List<T> filter() {
    return this.filterBuilder.filter();
  }
}
