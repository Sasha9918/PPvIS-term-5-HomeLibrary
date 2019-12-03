package com.iit.ppvis.repository;

import com.iit.ppvis.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    Optional<Book> findByBookName(String name);

    boolean existsByBookName(String bookName);

}
