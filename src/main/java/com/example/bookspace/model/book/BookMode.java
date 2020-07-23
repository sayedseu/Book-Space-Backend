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
public class BookMode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long modeId;
    private String bookMode;
}
