package com.iit.ppvis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "book_name")
    private String bookName;

    @Column(name = "author", nullable = false, updatable = false)
    private String author;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "publishing_year")
    private Integer publishingYear;

}
