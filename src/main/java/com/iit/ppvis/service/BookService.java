package com.iit.ppvis.service;

import com.iit.ppvis.model.AllBookInfo;
import com.iit.ppvis.model.WorkWithBookRequest;

public interface BookService {

    AllBookInfo take(String bookName, String visitorLastName);

    void addToLibrary(String author, String bookName, String publisher, Integer publishingYear);

    void delete(WorkWithBookRequest request);

}
