package com.example.bookspace.repository.book;

import com.example.bookspace.model.book.ExpectedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpectedBookRepository extends JpaRepository<ExpectedBook, Long> {
}
