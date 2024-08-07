package com.stu.auction.api.auction.repository;

import com.stu.auction.api.auction.entity.Category;
import com.stu.auction.api.auction.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemJpaRepository extends JpaRepository<Item, Long> {
}
