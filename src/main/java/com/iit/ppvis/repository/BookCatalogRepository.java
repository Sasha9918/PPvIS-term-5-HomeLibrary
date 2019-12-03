package com.iit.ppvis.repository;

import com.iit.ppvis.entity.BookCatalogRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookCatalogRepository extends JpaRepository<BookCatalogRecord, UUID> {
}
