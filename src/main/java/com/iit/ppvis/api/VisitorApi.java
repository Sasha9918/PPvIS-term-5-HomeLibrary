package com.iit.ppvis.api;

import com.iit.ppvis.model.CreateProfileRequest;
import com.iit.ppvis.model.AllBookInfo;
import com.iit.ppvis.model.WorkWithBookRequest;
import com.iit.ppvis.model.WorkWithReadBookRequest;
import com.iit.ppvis.service.BookService;
import com.iit.ppvis.service.ProfilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/visitor")
public class VisitorApi {

    private final BookService bookService;
    private final ProfilesService profilesService;

    @PostMapping("/profile/create")
    public ResponseEntity<Void> createProfile(CreateProfileRequest request) {
        profilesService.create(request);
        return new ResponseEntity<>(CREATED);
    }

    @GetMapping("/book/take")
    public ResponseEntity<AllBookInfo> takeBook(@RequestParam String bookName, @RequestParam String visitorLastName) {
        var response = bookService.take(bookName, visitorLastName);
        return new ResponseEntity<>(response, OK);
    }

    @PutMapping("/book/return")
    public ResponseEntity<Void> returnBook(@RequestBody WorkWithBookRequest request) {
        bookService.returnBook(request);
        return new ResponseEntity<>(OK);
    }

    @PutMapping("/book/add-to-read")
    public ResponseEntity<Void> addBookToRead(@RequestBody WorkWithReadBookRequest request) {
        bookService.addToReadList(request);
        return new ResponseEntity<>(OK);
    }

    @PutMapping("/book/add-to-favourite")
    public ResponseEntity<Void> addBookToFavourite(@RequestBody WorkWithReadBookRequest request) {
        bookService.addToFavouriteList(request);
        return new ResponseEntity<>(OK);
    }

    @PutMapping("/book/add-to-planned-to-read")
    public ResponseEntity<Void> addBookToPlannedToRead(@RequestBody WorkWithBookRequest request) {
        profilesService.addToPlannedToReadList(request);
        return new ResponseEntity<>(OK);
    }

}
