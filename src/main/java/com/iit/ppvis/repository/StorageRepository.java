package com.iit.ppvis.repository;

import com.iit.ppvis.entity.StorageRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StorageRepository extends JpaRepository<StorageRecord, UUID> {

    Optional<StorageRecord> findByBookName(String bookName);

    void deleteByBookName(String bookName);

}
