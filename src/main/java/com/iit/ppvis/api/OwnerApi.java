package com.iit.ppvis.api;

import com.iit.ppvis.model.AllBookInfo;
import com.iit.ppvis.model.WorkWithBookRequest;
import com.iit.ppvis.service.BookService;
import lombok.RequiredArgsConstructor;
import org.hibernate.jdbc.WorkExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/owner/book")
public class OwnerApi {

    private final BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<Void> add(@RequestBody AllBookInfo info) {
        bookService.addToLibrary(info);
        return new ResponseEntity<>(CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody WorkWithBookRequest request) {
        bookService.delete(request);
        return new ResponseEntity<>(NO_CONTENT);
    }

}
