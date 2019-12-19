package com.iit.ppvis.service.impl;

import com.iit.ppvis.entity.VisitorCounting;
import com.iit.ppvis.repository.VisitorCountingRepository;
import com.iit.ppvis.service.VisitorCountingService;
import com.vaadin.flow.component.notification.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.stream.Collectors;

import static com.iit.ppvis.common.utils.ExceptionUtils.entityNotFoundException;
import static com.iit.ppvis.common.utils.ExceptionUtils.forbiddenAccessException;

@Service
@RequiredArgsConstructor
public class VisitorCountingServiceImpl implements VisitorCountingService {

    private final VisitorCountingRepository visitorCountingRepository;

    @Override
    @Transactional
    public void create(String bookName, String visitorLastName) {
        checkIfBorrowed(bookName);
        var record = new VisitorCounting();
        record.setBookName(bookName);
        record.setVisitorLastName(visitorLastName);
        record.setTakenDate(Instant.now());
        visitorCountingRepository.save(record);
        Notification.show("Book is successfully taken");
    }

    @Override
    @Transactional
    public void update(String bookName, String visitorLastName) {
        var record = visitorCountingRepository.findByBookNameAndVisitorLastName(bookName, visitorLastName)
                .orElseThrow(() -> {
                            Notification.show(String.format("Book with name %s wasn't taken", bookName));
                            throw entityNotFoundException(String.format("Book with name %s wasn't taken", bookName));
                        }
                );
        record.setReturnedDate(Instant.now());
        visitorCountingRepository.save(record);
        Notification.show("Book is successfully returned");
    }

    @Override
    @Transactional(readOnly = true)
    public void checkIfBorrowed(String bookName) {
        var records = visitorCountingRepository.findAll().stream()
                .filter(record -> record.getBookName().equals(bookName) && record.getReturnedDate() == null)
                .collect(Collectors.toList());
        if (!records.isEmpty()) {
            throw forbiddenAccessException(String.format("Book %s has been already borrowed", bookName));
        }
    }

}
