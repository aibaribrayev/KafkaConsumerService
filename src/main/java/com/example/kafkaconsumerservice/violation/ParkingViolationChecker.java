package com.example.kafkaconsumerservice.violation;

import com.example.kafkaconsumerservice.model.ParkingSpot;
import com.example.kafkaconsumerservice.respository.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ParkingViolationChecker {
//    @Autowired
//    private ParkingSpotRepository parkingSpotRepository;
//
//    @Autowired
//    private ParkingViolationRepository parkingViolationRepository;
//
//    @Scheduled(fixedDelay = 900000) // 15 минут
//    public void checkParkingSpots() {
//        List<ParkingSpot> parkingSpots = parkingSpotRepository.findAll();
//
//        for (ParkingSpot parkingSpot : parkingSpots) {
//            if (parkingSpot.getIsOccupied() && parkingSpot.getCurrentUserId() == null && parkingSpot.getStartTime().plusMinutes(15).isBefore(LocalDateTime.now())) {
//                // Пользователь не начал парковку, считаем его нарушителем
//                //ParkingViolation parkingViolation = new ParkingViolation(parkingSpot.get(), parkingSpot.getCarNumber(), parkingSpot.getFullName());
//                //parkingViolationRepository.save(parkingViolation);
//
//            }
//
//            if (!parkingSpot.getIsPaid() && parkingSpot.getEndTime() != null && parkingSpot.getEndTime().plusMinutes(15).isBefore(LocalDateTime.now())) {
//                // Пользователь не оплатил парковку в течение 15 минут после окончания
//                ParkingViolation parkingViolation = new ParkingViolation(parkingSpot.getUserId(), parkingSpot.getCarNumber(), parkingSpot.getFullName());
//                parkingViolationRepository.save(parkingViolation);
//            }
//        }
//
//    }
}

