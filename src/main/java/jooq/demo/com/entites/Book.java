package jooq.demo.com.entites;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Book {

    private int id;

    private String title;

//    @OneToMany(mappedBy = "authorBooks", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Author> authors = new ArrayList<>();
}
