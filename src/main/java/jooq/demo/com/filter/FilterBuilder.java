package jooq.demo.com.filter;

import java.util.List;

public interface FilterBuilder<T> {

  List<T> filter();
}
