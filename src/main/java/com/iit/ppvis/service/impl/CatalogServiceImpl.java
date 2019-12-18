package com.iit.ppvis.service.impl;

import com.iit.ppvis.entity.CatalogRecord;
import com.iit.ppvis.model.AllBookInfo;
import com.iit.ppvis.model.WorkWithReadBookRequest;
import com.iit.ppvis.model.enums.BookStatus;
import com.iit.ppvis.model.enums.VisitorRole;
import com.iit.ppvis.repository.CatalogRepository;
import com.iit.ppvis.service.CatalogService;
import com.iit.ppvis.service.ProfilesService;
import com.vaadin.flow.component.notification.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.iit.ppvis.common.utils.ExceptionUtils.entityNotFoundException;
import static com.iit.ppvis.common.utils.ExceptionUtils.forbiddenAccessException;
import static com.iit.ppvis.model.enums.BookStatus.PRIVATE;
import static com.iit.ppvis.model.enums.VisitorRole.ROLE_VISITOR;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private static final Double INITIAL_RATE = 0.0;

    private final CatalogRepository catalogRepository;

    private final ProfilesService profilesService;

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
    public void create(AllBookInfo info) {
        var record = new CatalogRecord();
        record.setBookName(info.getBookName());
        record.setStatus(info.getStatus());
        record.setRate(INITIAL_RATE);
        catalogRepository.save(record);
    }

    @Override
    @Transactional
    public void delete(String bookName) {
        catalogRepository.deleteByBookName(bookName);
    }

    @Override
    @Transactional
    public void updateRate(WorkWithReadBookRequest readBookRequest) {
        var bookName = readBookRequest.getBookName();
        var catalogRecord = catalogRepository.findByBookName(bookName).orElseThrow(() -> {
            throw entityNotFoundException(String.format("There is no record for book with name %s", bookName));
        });
        var profile = profilesService.find(readBookRequest.getVisitorLastName());

        checkAccessLevel(catalogRecord.getStatus(), profile.getRole());

        var oldRate = catalogRecord.getRate();
        var denominator = oldRate.equals(0.0) ? 1 : 2;
        var newRate = (oldRate + readBookRequest.getRate()) / denominator;
        catalogRecord.setRate(newRate);
        catalogRepository.save(catalogRecord);
    }

    private void checkAccessLevel(BookStatus status, VisitorRole role) {
        if (status.equals(PRIVATE) && role.equals(ROLE_VISITOR)) {
            throw forbiddenAccessException("Visitor can't have access to private books");
        }
    }

}
