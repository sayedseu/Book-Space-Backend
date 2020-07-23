package com.example.bookspace.model.book;

import com.example.bookspace.model.auth.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BookDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private User owner;

    @OneToOne(cascade = CascadeType.ALL)
    private BookInfo bookInfo;

    @OneToOne(cascade = CascadeType.ALL)
    private BookCategory bookCategory;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private BookMode bookMode;

    @OneToOne(cascade = CascadeType.ALL)
    private ExpectedBook expectedBook;

}
