package com.example.kafkaconsumerservice.respository;

import com.example.kafkaconsumerservice.model.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {
    ParkingSpot findBySensorId(String sensorId);
    //ParkingSpot findBySensorId(String sensorId);
}

