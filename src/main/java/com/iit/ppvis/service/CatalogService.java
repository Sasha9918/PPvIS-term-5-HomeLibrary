package com.iit.ppvis.service;

import com.iit.ppvis.entity.CatalogRecord;
import com.iit.ppvis.model.enums.BookStatus;

import java.util.List;

public interface CatalogService {

    CatalogRecord find(String bookName, List<BookStatus> statuses);

    void create(String bookName, BookStatus status);

    void delete(String bookName);

    void updateRate(String bookName, String visitorLastName, Integer rate);

}
