package com.example.kafkaconsumerservice.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "parkingspot")
public class ParkingSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sensor_id")
    private String sensorId;
    private boolean isOccupied;
    @Column(name = "spot_number")
    private String spotNumber;

    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;
    @Column(name = "latitude")
    private double latitude;
    @Column(name = "longitude")
    private double longitude;
    @Column(name = "current_user_id")
    private Long currentUserId;
    @Column(name = "current_car_number")
    private String currentCarNumber;
    //private boolean violation;


    public ParkingSpot() {

    }

    public Long getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public String getSensorId() {
        return sensorId;
    }
    public boolean getIsOccupied(){
        return isOccupied;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    public ParkingSpot(String sensorId, double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
        this.sensorId = sensorId;
    }

    public Long getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(Long currentUserId) {
        this.currentUserId = currentUserId;
    }

    @Override
    public String toString() {
        return "Parking spot "+spotNumber+" with sensor id "+sensorId+ " occupancy "+isOccupied;
    }

    public void resetParkingOccupancy(){
        this.currentUserId = null;
        this.endTime = null;
        this.startTime = null;
        isOccupied = false;
    }

    public String getSpotNumber() {
        return spotNumber;
    }

//    public void setViolation(boolean violation) {
//        this.violation = violation;
//    }

    public String getCurrentCarNumber() {
        return currentCarNumber;
    }

    public void setCurrentCarNumber(String currentCarNumber) {
        this.currentCarNumber = currentCarNumber;
    }
}
