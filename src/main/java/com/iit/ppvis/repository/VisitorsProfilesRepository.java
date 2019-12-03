package com.iit.ppvis.repository;

import com.iit.ppvis.entity.VisitorProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VisitorsProfilesRepository extends JpaRepository<VisitorProfile, UUID> {
}
