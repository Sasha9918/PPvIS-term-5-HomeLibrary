package com.iit.ppvis.repository;

import com.iit.ppvis.entity.VisitorCounting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VisitorCountingRepository extends JpaRepository<VisitorCounting, UUID>  {

    Optional<VisitorCounting> findByBookNameAndVisitorLastName(String bookName, String visitorLastName);

}
