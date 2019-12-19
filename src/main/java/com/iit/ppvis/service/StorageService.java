package com.iit.ppvis.service;

import com.iit.ppvis.entity.StorageRecord;
import com.iit.ppvis.model.enums.Subject;

public interface StorageService {

    StorageRecord find(String bookName);

    void create(String bookName, Subject subject, String genre);

    void delete(String bookName);

}
