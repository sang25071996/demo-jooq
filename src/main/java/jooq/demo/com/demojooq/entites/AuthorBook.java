//package jooq.demo.com.demojooq.entites;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name = "author_book", schema = "masters")
//@Setter
//@Getter
//public class AuthorBook {
//
//    @Id
//    @JoinColumn(name = "author_id")
//    @ManyToOne()
//    private List<AuthorBook> authorBooks;
//
//    @Id
//    @JoinColumn(name = "book_id")
//    @ManyToOne()
//    private List<Book> books;
//}
