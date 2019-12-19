package com.iit.ppvis.repository;

import com.iit.ppvis.entity.StorageRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends JpaRepository<StorageRecord, String> {

}
