package com.iit.ppvis.service;

import com.iit.ppvis.model.AllBookInfo;
import com.iit.ppvis.model.WorkWithBookRequest;
import com.iit.ppvis.model.WorkWithReadBookRequest;

public interface BookService {

    AllBookInfo take(String bookName, String visitorLastName);

    void addToLibrary(AllBookInfo info);

    void delete(WorkWithBookRequest request);

    void returnBook(WorkWithBookRequest request);

    void addToReadList(WorkWithReadBookRequest request);

    void addToFavouriteList(WorkWithReadBookRequest request);

}
