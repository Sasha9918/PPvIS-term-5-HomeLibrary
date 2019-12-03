package com.iit.ppvis.service.impl;

import com.iit.ppvis.entity.Book;
import com.iit.ppvis.model.AllBookInfo;
import com.iit.ppvis.model.WorkWithBookRequest;
import com.iit.ppvis.repository.BookRepository;
import com.iit.ppvis.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.iit.ppvis.common.utils.AccessUtils.checkAccessLevel;
import static com.iit.ppvis.common.utils.BookUtils.createAllBookInfo;
import static com.iit.ppvis.common.utils.BookUtils.prepareBookStatus;
import static com.iit.ppvis.common.utils.ExceptionUtils.entityAlreadyExistsException;
import static com.iit.ppvis.common.utils.ExceptionUtils.entityNotFoundException;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final CatalogService catalogService;
    private final ProfilesService profilesService;
    private final StorageService storageService;
    private final VisitorCountingService visitorCountingService;

    @Override
    public AllBookInfo take(WorkWithBookRequest request) {
        var visitor = profilesService.find(request.getVisitorLastName());
        var statuses = prepareBookStatus(visitor.getRole());
        var bookName = request.getBookName();

        var book = bookRepository.findByBookName(bookName).orElseThrow(() -> {
            throw entityNotFoundException(String.format("There is no book with name %s", bookName));
        });

        var catalogRecord = catalogService.find(bookName, statuses);
        var storageRecord = storageService.find(bookName);

        visitorCountingService.checkIfBorrowed(bookName);
        visitorCountingService.create(request);

        return createAllBookInfo(book, catalogRecord, storageRecord);
    }

    @Override
    public void addToLibrary(AllBookInfo info) {
        checkIfExists(info.getBookName());

        var book = new Book();
        book.setAuthor(info.getAuthor());
        book.setBookName(info.getBookName());
        book.setPublisher(info.getPublisher());
        book.setPublishingYear(info.getPublishingYear());

        catalogService.create(info);
        storageService.create(info);
    }

    @Override
    public void delete(WorkWithBookRequest request) {
        var visitor = profilesService.find(request.getVisitorLastName());
        checkAccessLevel(visitor.getRole());
        var bookName = request.getBookName();

        catalogService.delete(bookName);
        profilesService.deleteBookFromLists(bookName);
        storageService.delete(bookName);
    }

    @Override
    public void returnBook(WorkWithBookRequest request) {
        visitorCountingService.updateRecord(request);
    }

    private void checkIfExists(String bookName) {
        if (bookRepository.existsByBookName(bookName)) {
            throw entityAlreadyExistsException(String.format("Book %s already exists", bookName));
        }
    }

}
