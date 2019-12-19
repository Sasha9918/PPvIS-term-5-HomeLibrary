package com.iit.ppvis.api;

import com.iit.ppvis.model.enums.BookStatus;
import com.iit.ppvis.model.enums.Subject;
import com.iit.ppvis.service.CatalogService;
import com.iit.ppvis.service.StorageService;
import com.iit.ppvis.service.VisitorCountingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final CatalogService catalogService;
    private final StorageService storageService;
    private final VisitorCountingService visitorCountingService;

    public void addToCatalog(String bookName, BookStatus status){
        catalogService.create(bookName, status);
    }

    public void addToStorage(String bookName, Subject subject, String genre) {
        storageService.create(bookName, subject, genre);
    }

    void addVisitorCountingRecord(String bookName, String lastName) {
        visitorCountingService.create(bookName, lastName);
    }

    void updateVisitorCountingRecord(String bookName, String lastName) {
        visitorCountingService.update(bookName, lastName);
    }

}
