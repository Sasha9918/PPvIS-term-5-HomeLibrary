package com.iit.ppvis.api;

import com.iit.ppvis.entity.Profile;
import com.iit.ppvis.model.CreateProfileRequest;
import com.iit.ppvis.model.AllBookInfo;
import com.iit.ppvis.model.WorkWithBookRequest;
import com.iit.ppvis.model.WorkWithReadBookRequest;
import com.iit.ppvis.model.enums.VisitorRole;
import com.iit.ppvis.repository.ProfilesRepository;
import com.iit.ppvis.service.BookService;
import com.iit.ppvis.service.ProfilesService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.iit.ppvis.model.enums.VisitorRole.fromAuthority;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
public class VisitorService extends VerticalLayout {

    //в методах необходимы параметры

    private final BookService bookService;
    private final ProfilesService profilesService;

    private final ProfilesRepository profilesRepository;

    public void createProfile() {
        removeAll();
        var layout = new HorizontalLayout();

        var firstName = new TextField("Имя");
        var lastName = new TextField("Фамилия");
        var role = new TextField("Роль");//TODO: make enum

        var take = new Button("Добавить", e -> {
            takeBook();
        });
        layout.add(firstName, lastName, role, take);

        var visitor = new Profile();
        visitor.setFirstName(firstName.getValue());
        visitor.setLastName(lastName.getValue());
        visitor.setRole(fromAuthority(role.getValue()));
        profilesRepository.save(visitor);
    }

    public void takeBook() {
        removeAll();
        var layout = new HorizontalLayout();

        var bookName = new TextField("Название книги");
        var visitorLastName = new TextField("Фамилия посетителя");

        layout.add(bookName, visitorLastName);
        findVisitor(bookName.getValue(), visitorLastName.getValue());
    }

    private void findVisitor(String bookName, String lastName) {
        if (profilesRepository.existsByLastName(lastName)) {
            //findBook(bookName);
        } else {
            createProfile();
        }
    }

//    @GetMapping("/book/take")
//    public ResponseEntity<AllBookInfo> takeBook(@RequestParam String bookName, @RequestParam String visitorLastName) {
//        var response = bookService.take(bookName, visitorLastName);
//        return new ResponseEntity<>(response, OK);
//    }

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
