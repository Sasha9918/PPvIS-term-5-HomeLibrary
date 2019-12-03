package com.iit.ppvis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.GenerationType.AUTO;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    protected UUID id;

    @Column(name = "author", nullable = false, updatable = false)
    private String author;

    @Column(name = "name", unique = true, nullable = false, updatable = false)
    private String bookName;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "publisher_year")
    private Integer publishingYear;

}
