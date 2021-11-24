package jooq.demo.com.entites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    private int id;

    private String firstName;

    private String lastName;

//    @OneToMany(mappedBy = "books", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<AuthorBook> authorBooks = new ArrayList<>();
}
