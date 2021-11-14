package jooq.demo.com.demojooq.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Setter
@Getter
public class Pagination<T> {

    private T model;
    private int size;
    private int page;
    private String sortField;
    private Sort.Direction direction;
}
