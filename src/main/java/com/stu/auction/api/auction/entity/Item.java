package com.stu.auction.api.auction.entity;

import com.stu.auction.api.auction.dto.ItemDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="item_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id", nullable = true)
    private Auction auction;

    //요청받은 dto Item Entity 로 형변환
    public Item(ItemDto itemDto) {
        this.name = itemDto.getName();
        this.description = itemDto.getDescription();
        this.imageUrl = itemDto.getImageUrl();
    }
}
