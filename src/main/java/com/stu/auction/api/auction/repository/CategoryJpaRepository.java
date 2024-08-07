package com.stu.auction.api.auction.repository;

import com.stu.auction.api.auction.entity.Auction;
import com.stu.auction.api.auction.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<Category, Long> {
}
