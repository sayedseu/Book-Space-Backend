package com.example.bookspace.repository.book;

import com.example.bookspace.model.book.BookDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDetailsRepository extends JpaRepository<BookDetails, Long> {
}
