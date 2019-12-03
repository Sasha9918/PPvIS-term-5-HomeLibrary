package com.iit.ppvis.service.impl;

import com.iit.ppvis.entity.CatalogRecord;
import com.iit.ppvis.model.AllBookInfo;
import com.iit.ppvis.model.enums.BookStatus;
import com.iit.ppvis.repository.CatalogRepository;
import com.iit.ppvis.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.iit.ppvis.common.utils.ExceptionUtils.entityNotFoundException;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository;

    @Override
    public CatalogRecord find(String bookName, List<BookStatus> statuses) {
        return catalogRepository.findByBookNameAndStatus(bookName, statuses).orElseThrow(() -> {
            throw entityNotFoundException(String.format("There is no record for book with name %s", bookName));
        });
    }

    @Override
    public void create(AllBookInfo info) {
        var bookCatalogRecord = new CatalogRecord();
        bookCatalogRecord.setBookName(info.getBookName());
        bookCatalogRecord.setStatus(info.getStatus());
        catalogRepository.save(bookCatalogRecord);
    }

    @Override
    public void delete(String bookName) {
        catalogRepository.deleteByBookName(bookName);
    }

}
