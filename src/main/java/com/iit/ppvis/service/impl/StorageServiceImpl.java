package com.iit.ppvis.service.impl;

import com.iit.ppvis.entity.StorageRecord;
import com.iit.ppvis.model.enums.Subject;
import com.iit.ppvis.repository.StorageRepository;
import com.iit.ppvis.service.StorageService;
import com.vaadin.flow.component.notification.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.iit.ppvis.common.utils.ExceptionUtils.entityNotFoundException;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    private final StorageRepository storageRepository;

    @Override
    @Transactional(readOnly = true)
    public StorageRecord find(String bookName) {
        return storageRepository.findByBookName(bookName).orElseThrow(() -> {
            Notification.show(String.format("There is no book with name %s in storage", bookName));
            throw entityNotFoundException(String.format("There is no book with name %s in storage", bookName));
        });
    }

    @Override
    @Transactional
    public void create(String bookName, Subject subject, String genre) {
        var bookStorageRecord = new StorageRecord();
        bookStorageRecord.setBookName(bookName);
        bookStorageRecord.setSubject(subject);
        bookStorageRecord.setGenre(genre);
        storageRepository.save(bookStorageRecord);
        Notification.show("Successfully added to storage");
    }

    @Override
    @Transactional
    public void delete(String bookName) {
        storageRepository.deleteByBookName(bookName);
    }

}
