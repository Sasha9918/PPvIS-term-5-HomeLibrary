package com.iit.ppvis.service;

import com.iit.ppvis.model.WorkWithBookRequest;

public interface VisitorCountingService {

    void create(WorkWithBookRequest request);

    void updateRecord(WorkWithBookRequest request);

    void checkIfBorrowed(String bookName);

}
