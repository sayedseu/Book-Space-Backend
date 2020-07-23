package com.example.bookspace.model.interested;

import com.example.bookspace.model.auth.User;
import com.example.bookspace.model.book.BookDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Interested {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private User owner;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private BookDetails bookDetails;
}
