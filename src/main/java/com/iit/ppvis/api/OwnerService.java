package com.iit.ppvis.api;

import com.iit.ppvis.entity.VisitorCounting;
import com.iit.ppvis.repository.VisitorCountingRepository;
import com.vaadin.flow.component.notification.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

import static com.iit.ppvis.common.utils.ExceptionUtils.forbiddenAccessException;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final VisitorCountingRepository visitorCountingRepository;


    void addVisitorCountingRecord(String bookName, String lastName) {
        checkIfBorrowed(bookName);
        var record = new VisitorCounting();
        record.setBookName(bookName);
        record.setVisitorLastName(lastName);
        record.setTakenDate(Instant.now());
        visitorCountingRepository.save(record);
    }

    private void checkIfBorrowed(String bookName) {
        var records = visitorCountingRepository.findAll().stream()
                .filter(record -> record.getBookName().equals(bookName) && record.getReturnedDate() == null)
                .collect(Collectors.toList());
        if (!records.isEmpty()) {
            Notification.show(String.format("Book %s has been already borrowed", bookName));
            throw forbiddenAccessException(String.format("Book %s has been already borrowed", bookName));
        }
    }

}
