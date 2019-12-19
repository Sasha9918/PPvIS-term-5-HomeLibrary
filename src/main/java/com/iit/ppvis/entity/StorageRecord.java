package com.iit.ppvis.entity;

import com.iit.ppvis.entity.enums.Genre;
import com.iit.ppvis.entity.enums.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book_storage")
public class StorageRecord {

    @Id
    @Column(name = "book_name")
    private String bookName;

    @Enumerated(STRING)
    @Column(name = "subject", nullable = false)
    private Subject subject;

    @Enumerated(STRING)
    @Column(name = "genre", nullable = false)
    private Genre genre;

}
