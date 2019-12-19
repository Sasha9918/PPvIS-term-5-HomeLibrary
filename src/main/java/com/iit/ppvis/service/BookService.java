package com.iit.ppvis.service;

import com.iit.ppvis.entity.Book;
import com.iit.ppvis.repository.BookRepository;
import com.vaadin.flow.component.notification.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public void addToLibrary(String author, String bookName, String publisher, Integer publishingYear) {
        checkIfExists(bookName);

        var book = new Book();
        book.setAuthor(author);
        book.setBookName(bookName);
        book.setPublisher(publisher);
        book.setPublishingYear(publishingYear);
        bookRepository.save(book);
        Notification.show("Книга успешно добавлена в библиотеку");
    }

    private void checkIfExists(String bookName) {
        if (bookRepository.existsById(bookName)) {
            Notification.show(String.format("Книга %s уже существует", bookName));
            throw new IllegalArgumentException();
        }
    }

}
