package com.iit.ppvis.service;

public interface VisitorCountingService {

    void create(String bookName, String visitorLastName);

    void update(String bookName, String visitorLastName);

    void checkIfBorrowed(String bookName);

}
