package com.iit.ppvis.repository;

import com.iit.ppvis.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfilesRepository extends JpaRepository<Profile, UUID> {

    Optional<Profile> findByLastName(String lastName);

    boolean existsByLastName(String lastName);

}
