package com.iit.ppvis.extended.repository;

import com.iit.ppvis.extended.entity.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, String> {
}
