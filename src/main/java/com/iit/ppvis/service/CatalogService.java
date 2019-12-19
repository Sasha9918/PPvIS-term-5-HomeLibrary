package com.iit.ppvis.service;

import com.iit.ppvis.entity.CatalogRecord;
import com.iit.ppvis.entity.enums.BookStatus;
import com.iit.ppvis.repository.CatalogRepository;
import com.vaadin.flow.component.notification.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogService {

    private static final Double INITIAL_RATE = 0.0;

    private final CatalogRepository catalogRepository;

    @Transactional(readOnly = true)
    public void find(String bookName, List<BookStatus> statuses) {
        catalogRepository.findByBookNameAndStatus(bookName, statuses).orElseThrow(() -> {
            Notification.show(String.format("Нет записи о книге %s", bookName));
            throw new NullPointerException();
        });
    }

    @Transactional
    public void create(String bookName, BookStatus status) {
        var record = new CatalogRecord();
        record.setBookName(bookName);
        record.setStatus(status);
        record.setRate(INITIAL_RATE);
        catalogRepository.save(record);
        Notification.show("Книга успешно добавлена в каталог");
    }

    @Transactional
    public void delete(String bookName) {
        catalogRepository.deleteById(bookName);
    }

    @Transactional
    public void updateRate(String bookName, Integer rate) {
        var catalogRecord = catalogRepository.findById(bookName).orElseThrow(() -> {
            Notification.show(String.format("Нет записи о книге %s", bookName));
            throw new NullPointerException();
        });

        var oldRate = catalogRecord.getRate();
        var denominator = oldRate.equals(0.0) ? 1 : 2;
        var newRate = (oldRate + rate) / denominator;
        catalogRecord.setRate(newRate);
        catalogRepository.save(catalogRecord);
        Notification.show(String.format("Оценка книги %s успешно обновлена", bookName));
    }

}
