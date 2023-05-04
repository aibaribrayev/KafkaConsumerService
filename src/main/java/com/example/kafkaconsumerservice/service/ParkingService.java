package com.example.kafkaconsumerservice.service;


import com.example.kafkaconsumerservice.model.ParkingSpot;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParkingService {
    List<ParkingSpot> getAllParkingSpots();
    ParkingSpot addParkingSpot(ParkingSpot parkingSpot);
    ParkingSpot getParkingSpotBySensorId(String id);
    ParkingSpot getParkingSpotById(String id);
    ParkingSpot updateParkingSpotStatus(Long id, boolean isOccupied);
    ParkingSpot startParkingSession(String id);
    ParkingSpot stopParkingSession(String id);
    //double calculateParkingFee(String id, LocalDateTime startTime, LocalDateTime endTime);
    List<ParkingSpot> getNearbyAvailableSpots(double latitude, double longitude, double radius);
//    List<ParkingHistory> getParkingHistory(long userId);
//    List<ParkingViolation> getViolations(long userId);
//    //boolean payParkingFee(long parkingSpotId, PaymentMethod paymentMethod);

}
