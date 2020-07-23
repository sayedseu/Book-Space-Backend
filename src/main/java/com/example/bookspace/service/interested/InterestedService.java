package com.example.bookspace.service.interested;

import com.example.bookspace.exception.ResourceNotFoundException;
import com.example.bookspace.model.auth.User;
import com.example.bookspace.model.book.BookDetails;
import com.example.bookspace.model.interested.Interested;
import com.example.bookspace.repository.auth.UserRepository;
import com.example.bookspace.repository.book.BookDetailsRepository;
import com.example.bookspace.repository.interested.InterestedRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InterestedService {
    private final InterestedRepository interestedRepository;
    private final UserRepository userRepository;
    private final BookDetailsRepository bookDetailsRepository;

    public InterestedService(InterestedRepository interestedRepository, UserRepository userRepository, BookDetailsRepository bookDetailsRepository) {
        this.interestedRepository = interestedRepository;
        this.userRepository = userRepository;
        this.bookDetailsRepository = bookDetailsRepository;
    }

    public long insert(Interested interested) throws ResourceNotFoundException {
        Optional<User> optionalUser = userRepository.findById(interested.getOwner().getUserId());
        Optional<BookDetails> optionalBookDetails = bookDetailsRepository.findById(interested.getBookDetails().getId());

        Optional<Interested> optionalInterested = interestedRepository.findAll()
                .stream()
                .filter(interested1 -> interested1.getBookDetails().getId() == interested.getBookDetails().getId()
                        && interested1.getOwner().getUserId() == interested.getOwner().getUserId())
                .findFirst();


        if (optionalInterested.isPresent()) return -1;
        if (optionalUser.isPresent() && optionalBookDetails.isPresent()) {
            interested.setOwner(optionalUser.get());
            interested.setBookDetails(optionalBookDetails.get());
            return interestedRepository.save(interested).getId();
        } else throw new ResourceNotFoundException("");
    }

    public List<Interested> retrieve(long userId) throws ResourceNotFoundException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            List<Interested> interestedList = interestedRepository.findAll()
                    .stream()
                    .filter(interested -> interested.getOwner().getUserId() == userId)
                    .collect(Collectors.toList());
            if (interestedList.isEmpty()) throw new ResourceNotFoundException("");
            else return interestedList;
        } else throw new ResourceNotFoundException("");
    }

    public int delete(long interestedId) throws ResourceNotFoundException {
        Optional<Interested> optionalInterested = interestedRepository.findById(interestedId);
        if (optionalInterested.isPresent()) {
            interestedRepository.deleteById(interestedId);
            return 1;
        } else throw new ResourceNotFoundException("");
    }
}
