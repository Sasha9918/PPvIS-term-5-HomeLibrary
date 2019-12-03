package com.iit.ppvis.entity;

import com.iit.ppvis.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

import static javax.persistence.GenerationType.AUTO;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "visitor_counting")
public class VisitorCountingRecord {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    protected UUID id;

    @Column(name = "book", nullable = false)
    private Book book;

    @Column(name = "visitor_last_name", nullable = false)
    private String visitorLastName;

    @Column(name = "taken_date", nullable = false)
    private Instant takenDate;

    @Column(name = "returned_date")
    private Instant returnedDate;

}
