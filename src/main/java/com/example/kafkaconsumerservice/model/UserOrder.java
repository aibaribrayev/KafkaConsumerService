package com.example.kafkaconsumerservice.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "parking_history")
public class UserOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column
    private String parkingSpotNumber;
    @Column
    private LocalDateTime startTime;
    @Column
    private LocalDateTime endTime;
    @Column
    private double price;
    @Column
    private boolean isPaid;

    public UserOrder(String parkingSpotNumber, LocalDateTime startTime, LocalDateTime endTime){
        this.parkingSpotNumber = parkingSpotNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        price = (endTime.getMinute() - startTime.getMinute())*100/60;
    }

    public Long getId() {
        return id;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
    public boolean isPaid() {
        return isPaid;
    }
}
