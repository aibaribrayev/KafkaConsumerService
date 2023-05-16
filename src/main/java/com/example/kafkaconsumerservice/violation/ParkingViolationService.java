package com.example.kafkaconsumerservice.violation;

import com.example.kafkaconsumerservice.model.ParkingSpot;
import com.example.kafkaconsumerservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParkingViolationService {

    @Autowired
    private ParkingViolationRepository parkingViolationRepository;

    public List<ParkingViolation> getAllViolations() {
        return parkingViolationRepository.findAll();
    }

    public void addPaymentViolation(ParkingSpot spot) {
        ParkingViolation violation = new ParkingViolation();
        violation.setUserId(spot.getCurrentUserId());
        //violation.setCarNumber(car);
        violation.setFineAmount(10000);
        violation.setViolationTime(LocalDateTime.now());
        parkingViolationRepository.save(violation);
    }
}

