package com.iit.ppvis.entity;

import com.iit.ppvis.model.Book;
import com.iit.ppvis.model.enums.BookStatus;
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
@Table(name = "book_catalog")
public class BookCatalogRecord {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    protected UUID id;

    @Column(name = "book", nullable = false)
    private Book book;

    @Column(name = "rate", nullable = false)
    private Double rate;

    @Column(name = "status", nullable = false)
    private BookStatus status;

}
