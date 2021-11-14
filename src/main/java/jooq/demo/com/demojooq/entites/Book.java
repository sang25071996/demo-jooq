package jooq.demo.com.demojooq.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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
