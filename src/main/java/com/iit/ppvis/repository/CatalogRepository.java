package com.iit.ppvis.repository;

import com.iit.ppvis.entity.CatalogRecord;
import com.iit.ppvis.entity.enums.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatalogRepository extends JpaRepository<CatalogRecord, String> {

    @Query("SELECT book " +
           "FROM CatalogRecord book " +
           "WHERE book.bookName = :bookName" +
           " AND book.status IN :statuses")
    Optional<CatalogRecord> findByBookNameAndStatus(@Param("bookName") String bookName,
                                                    @Param("statuses") List<BookStatus> statuses);

}
