package com.iit.ppvis.repository;

import com.iit.ppvis.entity.BookStorageRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookStorageRepository extends JpaRepository<BookStorageRecord, UUID> {
}
