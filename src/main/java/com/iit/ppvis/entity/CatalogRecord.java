package com.iit.ppvis.entity;

import com.iit.ppvis.entity.enums.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book_catalog")
public class CatalogRecord {

    @Id
    @Column(name = "book_name")
    private String bookName;

    @Column(name = "rate")
    private Double rate;

    @Enumerated(STRING)
    @Column(name = "status", nullable = false)
    private BookStatus status;

}
