package com.iit.ppvis.entity;

import com.iit.ppvis.model.enums.VisitorRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

import static javax.persistence.GenerationType.AUTO;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "visitor_profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    protected UUID id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", unique = true, nullable = false)
    private String lastName;

    @Column(name = "role", nullable = false)
    private VisitorRole role;

    @Column(name = "favourite")
    private List<String> favouriteBooks;

    @Column(name = "planned_to_read")
    private List<String> plannedToReadBooks;

    @Column(name = "read")
    private List<String> readBooks;

}