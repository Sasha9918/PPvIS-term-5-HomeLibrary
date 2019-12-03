package com.iit.ppvis.service;

import com.iit.ppvis.entity.StorageRecord;
import com.iit.ppvis.model.AllBookInfo;

public interface StorageService {

    StorageRecord find(String bookName);

    void create(AllBookInfo info);

    void delete(String bookName);

}
