package jooq.demo.com.entites;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book", schema = "masters")
@Setter
@Getter
public class Book {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

//    @OneToMany(mappedBy = "authorBooks", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<AuthorBook> authorBooks = new ArrayList<>();
}
