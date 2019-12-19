package com.iit.ppvis.service;

import com.iit.ppvis.entity.VisitorCounting;
import com.iit.ppvis.repository.VisitorCountingRepository;
import com.vaadin.flow.component.notification.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitorCountingService {

    private final VisitorCountingRepository visitorCountingRepository;

    @Transactional
    public void create(String bookName, String visitorLastName) {
        checkIfBorrowed(bookName);
        var record = new VisitorCounting();
        record.setBookName(bookName);
        record.setVisitorLastName(visitorLastName);
        record.setTakenDate(Instant.now());
        visitorCountingRepository.save(record);
        Notification.show("Книга успешно взята");
    }

    @Transactional
    public void update(String bookName, String visitorLastName) {
        var record = visitorCountingRepository.findByBookNameAndVisitorLastName(bookName, visitorLastName)
                .orElseThrow(() -> {
                            Notification.show(String.format("Книга %s не была взята", bookName));
                            throw new NullPointerException();
                        }
                );
        record.setReturnedDate(Instant.now());
        visitorCountingRepository.save(record);
        Notification.show("Книга успешно возвращена");
    }

    @Transactional(readOnly = true)
    public void checkIfBorrowed(String bookName) {
        var records = visitorCountingRepository.findAll().stream()
                .filter(record -> record.getBookName().equals(bookName) && record.getReturnedDate() == null)
                .collect(Collectors.toList());
        if (!records.isEmpty()) {
            Notification.show(String.format("Книга %s взята другим посетителем", bookName));
            throw new IllegalArgumentException();
        }
    }

}
