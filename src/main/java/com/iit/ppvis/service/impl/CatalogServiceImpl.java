package com.iit.ppvis.service.impl;

import com.iit.ppvis.entity.CatalogRecord;
import com.iit.ppvis.model.enums.BookStatus;
import com.iit.ppvis.repository.CatalogRepository;
import com.iit.ppvis.service.CatalogService;
import com.iit.ppvis.service.ProfilesService;
import com.vaadin.flow.component.notification.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.iit.ppvis.common.utils.ExceptionUtils.entityNotFoundException;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private static final Double INITIAL_RATE = 0.0;

    private final CatalogRepository catalogRepository;

    @Override
    @Transactional(readOnly = true)
    public CatalogRecord find(String bookName, List<BookStatus> statuses) {
        return catalogRepository.findByBookNameAndStatus(bookName, statuses).orElseThrow(() -> {
            Notification.show(String.format("There is no record for book with name %s", bookName));
            throw entityNotFoundException(String.format("There is no record for book with name %s", bookName));
        });
    }

    @Override
    @Transactional
    public void create(String bookName, BookStatus status) {
        var record = new CatalogRecord();
        record.setBookName(bookName);
        record.setStatus(status);
        record.setRate(INITIAL_RATE);
        catalogRepository.save(record);
        Notification.show("Successfully added to catalog");
    }

    @Override
    @Transactional
    public void delete(String bookName) {
        catalogRepository.deleteByBookName(bookName);
    }

    @Override
    @Transactional
    public void updateRate(String bookName, String visitorLastName, Integer rate) {
        var catalogRecord = catalogRepository.findByBookName(bookName).orElseThrow(() -> {
            Notification.show(String.format("There is no record for book with name %s", bookName));
            throw entityNotFoundException(String.format("There is no record for book with name %s", bookName));
        });

        var oldRate = catalogRecord.getRate();
        var denominator = oldRate.equals(0.0) ? 1 : 2;
        var newRate = (oldRate + rate) / denominator;
        catalogRecord.setRate(newRate);
        catalogRepository.save(catalogRecord);
        Notification.show(String.format("Successfully update rate for book with name %s", bookName));
    }

}
