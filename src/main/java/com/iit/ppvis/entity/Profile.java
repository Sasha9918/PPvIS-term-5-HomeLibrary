package com.iit.ppvis.entity;

import com.iit.ppvis.entity.enums.VisitorRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.EnumType.STRING;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "visitor_profile")
public class Profile {

    @Id
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Enumerated(STRING)
    @Column(name = "role", nullable = false)
    private VisitorRole role;

    @ElementCollection
    @Column(name = "favourite")
    private List<String> favouriteBooks;

    @ElementCollection
    @Column(name = "planned_to_read")
    private List<String> plannedToReadBooks;

    @ElementCollection
    @Column(name = "read")
    private List<String> readBooks;

}
