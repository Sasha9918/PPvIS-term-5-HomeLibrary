package com.iit.ppvis.ui;

import com.iit.ppvis.entity.enums.BookStatus;
import com.iit.ppvis.entity.enums.Genre;
import com.iit.ppvis.entity.enums.Subject;
import com.iit.ppvis.repository.BookRepository;
import com.iit.ppvis.service.CatalogService;
import com.iit.ppvis.service.ProfilesService;
import com.iit.ppvis.service.StorageService;
import com.iit.ppvis.service.VisitorCountingService;
import com.vaadin.flow.component.notification.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final CatalogService catalogService;
    private final ProfilesService profilesService;
    private final StorageService storageService;
    private final VisitorCountingService visitorCountingService;

    private final BookRepository bookRepository;

    public void addToCatalog(String bookName, BookStatus status){
        catalogService.create(bookName, status);
    }

    public void addToStorage(String bookName, Subject subject, Genre genre) {
        storageService.create(bookName, subject, genre);
    }

    void addVisitorCountingRecord(String bookName, String lastName) {
        visitorCountingService.create(bookName, lastName);
    }

    void updateVisitorCountingRecord(String bookName, String lastName) {
        visitorCountingService.update(bookName, lastName);
    }

    public void deleteBookFromProfiles(String bookName) {
        profilesService.deleteBookFromLists(bookName);
        Notification.show("Книга успешно удалена из списков пользователей");
    }

    public void deleteBookRecords(String bookName) {
        catalogService.delete(bookName);
        storageService.delete(bookName);
        Notification.show("Книга успешно удалена из хранилища и каталога");
    }

    public void deleteBook(String bookName) {
        bookRepository.deleteById(bookName);
        Notification.show("Книга успешно удалена");
    }

}
