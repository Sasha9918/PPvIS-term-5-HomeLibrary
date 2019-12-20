package com.iit.ppvis.extended.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "auction")
public class Auction {

    @Id
    @Column(name = "name", nullable = false, updatable = false)
    private String name;

    @ElementCollection
    @Column(name = "books")
    private List<String> books;

    @Column(name = "started_at", nullable = false)
    private Instant startedAt;

    @Column(name = "finished_at", nullable = false)
    private Instant finishedAt;

}
