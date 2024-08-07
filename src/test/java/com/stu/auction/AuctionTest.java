package com.stu.auction;

import com.stu.auction.api.auction.entity.Item;
import com.stu.auction.api.auction.repository.ItemJpaRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
//@Transactional
public class AuctionTest {

    @Autowired
    ItemJpaRepository itemJpaRepository;

    @Test
    public void 아이템등록테스트 (){
        Item tmpItem = new Item();
        tmpItem.setName("아이템");
        tmpItem.setDescription("아이템 설명");
        tmpItem.setImageUrl("www.allmytour.com");

        itemJpaRepository.save(tmpItem);

    }

    @Test
    public void 아이템읽기테스트 (){

    }

}
