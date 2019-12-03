package com.iit.ppvis.repository;

import com.iit.ppvis.entity.VisitorCountingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VisitorCountingRepository extends JpaRepository<VisitorCountingRecord, UUID>  {
}
