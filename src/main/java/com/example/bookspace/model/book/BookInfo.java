package com.example.bookspace.model.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BookInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookId;
    private String bookName;
    private String bookAuthorName;
    private String bookPrice;
    private String bookPublicationYear;
    private String bookEdition;
    private String bookLocation;
    private String bookCondition;
    private String isbnNumber;
    private String imageLocation1;
    private String imageLocation2;
    private String imageLocation3;
    private String dateAndTime;
}
