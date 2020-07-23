package com.example.bookspace.controller;

import com.example.bookspace.exception.ResourceNotFoundException;
import com.example.bookspace.model.interested.Interested;
import com.example.bookspace.service.interested.InterestedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class InterestedController {
    private final InterestedService interestedService;

    public InterestedController(InterestedService interestedService) {
        this.interestedService = interestedService;
    }

    @PostMapping(value = "interest/new", consumes = {"application/json"})
    public ResponseEntity<Long> insert(@RequestBody Interested interested) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(interestedService.insert(interested));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value = "/interest/{userid}")
    public ResponseEntity<List<Interested>> retrieve(@PathVariable(value = "userid") long userId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(interestedService.retrieve(userId));
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping(value = "/interest/{id}")
    public ResponseEntity<Integer> delete(@PathVariable(value = "id") long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(interestedService.delete(id));
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
