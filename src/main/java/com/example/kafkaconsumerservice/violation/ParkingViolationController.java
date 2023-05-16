package com.example.kafkaconsumerservice.violation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/violations")
public class ParkingViolationController {

    @Autowired
    private ParkingViolationService parkingViolationService;

    @GetMapping
    public List<ParkingViolation> getAllViolations() {
        return parkingViolationService.getAllViolations();
    }
}

