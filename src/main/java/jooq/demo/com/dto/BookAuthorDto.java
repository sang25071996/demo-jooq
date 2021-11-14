package jooq.demo.com.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookAuthorDto {

    private int id;

    private String title;

    private String firstName;

    private String lastName;
}
