package com.iit.ppvis.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/visitor")
public class VisitorApi {

    @PostMapping("/create")
    public ResponseEntity<Void> createProfile() {
        return new ResponseEntity<>(CREATED);
    }

    @GetMapping("/book/search")
    public ResponseEntity<Void> searchBook() {
        return new ResponseEntity<>(OK);
    }

    @GetMapping("/book/take")
    public ResponseEntity<Void> takeBook() {
        return new ResponseEntity<>(OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Void> searchVisitor() {
        return new ResponseEntity<>(OK);
    }

    @PutMapping("/book/rate")
    public ResponseEntity<Void> rateBook() {
        return new ResponseEntity<>(OK);
    }

    @PutMapping("/book/report-taking")
    public ResponseEntity<Void> reportTakingBook() {
        return new ResponseEntity<>(OK);
    }

    @PutMapping("/book/report-returning")
    public ResponseEntity<Void> reportReturningBook() {
        return new ResponseEntity<>(OK);
    }

    @PutMapping("/book/return")
    public ResponseEntity<Void> returnBook() {
        return new ResponseEntity<>(OK);
    }

    @PutMapping("/book/add-to-read")
    public ResponseEntity<Void> addBookToRead() {
        return new ResponseEntity<>(OK);
    }

    @PutMapping("/book/add-to-favourite")
    public ResponseEntity<Void> addBookToFavourite() {
        return new ResponseEntity<>(OK);
    }

    @PutMapping("/book/add-to-planned-to-read")
    public ResponseEntity<Void> addBookToPlannedToRead() {
        return new ResponseEntity<>(OK);
    }

}
//TODO: redirect to necessary pages
