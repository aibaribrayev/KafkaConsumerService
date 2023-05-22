package com.example.kafkaconsumerservice.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "parking_history")
public class ParkingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long userId;
    @Column
    private String parkingSpotNumber;
    @Column
    private LocalDateTime startTime;
    @Column
    private LocalDateTime endTime;
    @Column
    private String carNumber;
    @Column
    private double price;
    @Column
    private boolean isPaid;

    public ParkingSession(String parkingSpotNumber, LocalDateTime startTime, LocalDateTime endTime, Long userId, String carNumber){
        this.parkingSpotNumber = parkingSpotNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.userId = userId;
        this.carNumber = carNumber;
        price = (endTime.getMinute() - startTime.getMinute())*100/60;
    }

    public ParkingSession() {

    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public String getParkingSpotNumber() {
        return parkingSpotNumber;
    }

    public double getPrice() {
        return price;
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

    public String getCarNumber() {
        return carNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
