package com.example.bookspace.repository.book;

import com.example.bookspace.model.book.BookInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookInfoRepository extends JpaRepository<BookInfo, Long> {
}
