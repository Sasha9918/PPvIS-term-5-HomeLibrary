package com.iit.ppvis.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/owner")
public class OwnerApi {

    @PostMapping("/visitor-counting/create")
    public ResponseEntity<Void> createVisitorCountingRecord() {
        return new ResponseEntity<>(CREATED);
    }

    @PostMapping("/book-catalog/add")
    public ResponseEntity<Void> addBookToCatalog() {
        return new ResponseEntity<>(CREATED);
    }

    @PostMapping("/book-storage/add")
    public ResponseEntity<Void> addBookToStorage() {
        return new ResponseEntity<>(CREATED);
    }

    @PutMapping("/visitor-counting/update")
    public ResponseEntity<Void> updateVisitorCountingRecord() {
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/book-catalog/delete")
    public ResponseEntity<Void> deleteBookFromCatalog() {
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/book-storage/delete")
    public ResponseEntity<Void> deleteBookFromStorage() {
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/visitor-profile/book/delete")
    public ResponseEntity<Void> deleteBookFromVisitorProfiles() {
        return new ResponseEntity<>(OK);
    }

}
//TODO: redirect to necessary pages
