package com.stu.auction.api.auction.repository;

import com.stu.auction.api.auction.entity.Bid;
import com.stu.auction.api.auction.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidJpaRepository extends JpaRepository<Bid, Long> {
}
