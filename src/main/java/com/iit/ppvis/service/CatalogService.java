package com.iit.ppvis.service;

import com.iit.ppvis.entity.CatalogRecord;
import com.iit.ppvis.model.AllBookInfo;
import com.iit.ppvis.model.enums.BookStatus;

import java.util.List;

public interface CatalogService {

    CatalogRecord find(String bookName, List<BookStatus> statuses);

    void create(AllBookInfo info);

    void delete(String bookName);

}
