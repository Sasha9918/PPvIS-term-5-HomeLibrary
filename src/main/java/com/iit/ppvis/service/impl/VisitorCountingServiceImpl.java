package com.iit.ppvis.service.impl;

import com.iit.ppvis.entity.VisitorCounting;
import com.iit.ppvis.model.WorkWithBookRequest;
import com.iit.ppvis.repository.VisitorCountingRepository;
import com.iit.ppvis.service.VisitorCountingService;
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
    public void create(WorkWithBookRequest request) {
        var record = new VisitorCounting();
        record.setBookName(request.getBookName());
        record.setVisitorLastName(request.getVisitorLastName());
        record.setTakenDate(Instant.now());
        visitorCountingRepository.save(record);
    }

    @Override
    @Transactional
    public void updateRecord(WorkWithBookRequest request) {
        var record = visitorCountingRepository.findByBookNameAndVisitorLastName(request.getBookName(),
                request.getVisitorLastName()).orElseThrow(() ->
                entityNotFoundException(String.format("Book with name %s wasn't taken", request.getBookName())));
        record.setReturnedDate(Instant.now());
        visitorCountingRepository.save(record);
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
