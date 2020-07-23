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
public class ExpectedBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long expectedBookId;
    private String boonName;
    private String bookAuthorName;
    private String bookPrice;
    private String imageLocation1;
    private String imageLocation2;
    private String imageLocation3;
}
