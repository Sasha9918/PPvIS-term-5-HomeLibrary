package com.iit.ppvis.service.impl;

import com.iit.ppvis.entity.Book;
import com.iit.ppvis.model.AllBookInfo;
import com.iit.ppvis.model.WorkWithBookRequest;
import com.iit.ppvis.model.WorkWithReadBookRequest;
import com.iit.ppvis.repository.BookRepository;
import com.iit.ppvis.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public AllBookInfo take(String bookName, String visitorLastName) {
        var visitor = profilesService.find(visitorLastName);
        var statuses = prepareBookStatus(visitor.getRole());

        var book = bookRepository.findByBookName(bookName).orElseThrow(() -> {
            throw entityNotFoundException(String.format("There is no book with name %s", bookName));
        });

        var catalogRecord = catalogService.find(bookName, statuses);
        var storageRecord = storageService.find(bookName);
        var request = new WorkWithBookRequest();
        request.setBookName(bookName);
        request.setVisitorLastName(visitorLastName);

        visitorCountingService.checkIfBorrowed(bookName);
        visitorCountingService.create(request);

        return createAllBookInfo(book, catalogRecord, storageRecord);
    }

    @Override
    @Transactional
    public void addToLibrary(AllBookInfo info) {
        checkIfExists(info.getBookName());

        var book = new Book();
        book.setAuthor(info.getAuthor());
        book.setBookName(info.getBookName());
        book.setPublisher(info.getPublisher());
        book.setPublishingYear(info.getPublishingYear());

        catalogService.create(info);
        storageService.create(info);
        bookRepository.save(book);
    }

    @Override
    @Transactional
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

    @Override
    public void addToReadList(WorkWithReadBookRequest request) {
        catalogService.updateRate(request);
        profilesService.updateReadList(request);
    }

    @Override
    public void addToFavouriteList(WorkWithReadBookRequest request) {
        catalogService.updateRate(request);
        profilesService.updateFavouriteList(request);
    }

    private void checkIfExists(String bookName) {
        if (bookRepository.existsByBookName(bookName)) {
            throw entityAlreadyExistsException(String.format("Book %s already exists", bookName));
        }
    }

}
