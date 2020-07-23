package com.example.bookspace.service.book;

import com.example.bookspace.exception.ResourceNotFoundException;
import com.example.bookspace.model.auth.User;
import com.example.bookspace.model.book.BookDetails;
import com.example.bookspace.model.book.BookMode;
import com.example.bookspace.repository.auth.UserRepository;
import com.example.bookspace.repository.book.BookDetailsRepository;
import com.example.bookspace.repository.book.BookModeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    private BookDetailsRepository bookDetailsRepository;
    private BookModeRepository bookModeRepository;
    private UserRepository userRepository;

    public BookService(BookDetailsRepository bookDetailsRepository, BookModeRepository bookModeRepository, UserRepository userRepository) {
        this.bookDetailsRepository = bookDetailsRepository;
        this.bookModeRepository = bookModeRepository;
        this.userRepository = userRepository;
    }

    public Long insert(BookDetails bookDetails) throws ResourceNotFoundException {
        Optional<User> optionalUser = userRepository.findById(bookDetails.getOwner().getUserId());
        if (optionalUser.isPresent()) {
            bookDetails.setOwner(optionalUser.get());
            Optional<BookMode> optionalBookMode = bookModeRepository.findAll()
                    .stream()
                    .filter(bookMode -> bookMode.getBookMode().equals(bookDetails.getBookMode().getBookMode()))
                    .findFirst();
            if (optionalBookMode.isPresent()) bookDetails.setBookMode(optionalBookMode.get());
            else bookDetails.setBookMode(bookModeRepository.save(bookDetails.getBookMode()));
            return bookDetailsRepository.save(bookDetails).getId();
        } else throw new ResourceNotFoundException("");
    }

    public List<BookDetails> retrieve() throws ResourceNotFoundException {
        List<BookDetails> list = bookDetailsRepository.findAll();
        if (list.isEmpty()) throw new ResourceNotFoundException("");
        else return list;
    }

    public List<BookDetails> retrieveByMode(String mode) throws ResourceNotFoundException {
        List<BookDetails> list = bookDetailsRepository.findAll()
                .stream()
                .filter(bookDetails -> bookDetails.getBookMode().getBookMode().equals(mode))
                .collect(Collectors.toList());
        if (list.isEmpty()) throw new ResourceNotFoundException("");
        else return list;
    }

    public List<BookDetails> retrieveByCategory(String category) throws ResourceNotFoundException {
        List<BookDetails> list = bookDetailsRepository.findAll()
                .stream()
                .filter(bookDetails -> bookDetails.getBookCategory().getSubCategory().equals(category))
                .collect(Collectors.toList());
        if (list.isEmpty()) throw new ResourceNotFoundException("");
        else return list;
    }

}
