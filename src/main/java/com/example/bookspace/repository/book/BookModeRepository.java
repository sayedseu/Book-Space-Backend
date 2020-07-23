package com.example.bookspace.repository.book;

import com.example.bookspace.model.book.BookMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookModeRepository extends JpaRepository<BookMode, Long> {
}
