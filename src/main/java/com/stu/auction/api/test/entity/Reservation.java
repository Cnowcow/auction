package com.stu.auction.api.test.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reservation")
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationIndex;

    @Column(nullable = false)
    private String reservationName;

    @Column(nullable = false)
    private String reservationPhone;

    @Column(nullable = false)
    private String reservationType;

    @Column(nullable = false)
    private LocalDate reservationDate;

    @Column(nullable = false)
    private LocalTime reservationTime;

    private String reservationInfo;

}
