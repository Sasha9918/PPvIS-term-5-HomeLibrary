package com.iit.ppvis.entity;

import com.iit.ppvis.model.Book;
import com.iit.ppvis.model.enums.Subject;
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
@Table(name = "book_storage")
public class BookStorageRecord {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    protected UUID id;

    @Column(name = "book", nullable = false)
    private Book book;

    @Column(name = "subject", nullable = false)
    private Subject subject;

    @Column(name = "genre", nullable = false)
    private String genre;

}
//TODO: genre must be nested enum