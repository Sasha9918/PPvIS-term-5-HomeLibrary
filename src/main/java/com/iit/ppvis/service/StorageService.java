package com.iit.ppvis.service;

import com.iit.ppvis.entity.StorageRecord;
import com.iit.ppvis.entity.enums.Genre;
import com.iit.ppvis.entity.enums.Subject;
import com.iit.ppvis.repository.StorageRepository;
import com.vaadin.flow.component.notification.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final StorageRepository storageRepository;

    @Transactional(readOnly = true)
    public void find(String bookName) {
        storageRepository.findById(bookName).orElseThrow(() -> {
            Notification.show(String.format("В хранилище нет книги %s", bookName));
            throw new NullPointerException();
        });
    }

    @Transactional
    public void create(String bookName, Subject subject, Genre genre) {
        var bookStorageRecord = new StorageRecord();
        bookStorageRecord.setBookName(bookName);
        bookStorageRecord.setSubject(subject);
        bookStorageRecord.setGenre(genre);
        storageRepository.save(bookStorageRecord);
        Notification.show("Книга успешно добавлена в хранилище");
    }

    @Transactional
    public void delete(String bookName) {
        storageRepository.deleteById(bookName);
    }

}
