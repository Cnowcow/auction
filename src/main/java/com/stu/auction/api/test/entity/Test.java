package com.stu.auction.api.test.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "test")
@Data
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

}
