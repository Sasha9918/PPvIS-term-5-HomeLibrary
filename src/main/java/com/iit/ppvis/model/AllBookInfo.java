package com.iit.ppvis.model;

import com.iit.ppvis.model.enums.BookStatus;
import com.iit.ppvis.model.enums.Subject;
import lombok.Data;

@Data
public class AllBookInfo {

    private String author;
    private String bookName;
    private String genre;
    private String publisher;
    private Integer publishingYear;
    private Double rate;
    private BookStatus status;
    private Subject subject;

}
