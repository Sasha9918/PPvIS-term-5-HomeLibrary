package com.iit.ppvis.service;

import com.iit.ppvis.model.AllBookInfo;
import com.iit.ppvis.model.WorkWithBookRequest;

public interface BookService {

    AllBookInfo take(WorkWithBookRequest request);

    void addToLibrary(AllBookInfo info);

    void delete(WorkWithBookRequest request);

    void returnBook(WorkWithBookRequest request);

}
