package com.example.bookspace.controller;

import com.example.bookspace.exception.ResourceNotFoundException;
import com.example.bookspace.model.book.BookDetails;
import com.example.bookspace.service.book.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "/books/new", consumes = {"application/json"})
    public ResponseEntity<Long> insert(@RequestBody BookDetails bookDetails) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(bookService.insert(bookDetails));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value = "/books")
    public ResponseEntity<List<BookDetails>> retrieve() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(bookService.retrieve());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value = "/books/mode/{mode}")
    public ResponseEntity<List<BookDetails>> retrieveByMode(@PathVariable(value = "mode") String mode) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(bookService.retrieveByMode(mode));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
 @GetMapping(value = "/books/category/{category}")
    public ResponseEntity<List<BookDetails>> retrieveByCategory(@PathVariable(value = "category") String category) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(bookService.retrieveByCategory(category));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
