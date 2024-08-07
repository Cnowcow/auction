package com.stu.auction.api.auction.repository;

import com.stu.auction.api.auction.entity.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionJpaRepository extends JpaRepository<Auction , Long> {
}
