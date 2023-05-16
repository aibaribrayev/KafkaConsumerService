package com.example.kafkaconsumerservice.violation;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingViolationRepository extends JpaRepository<ParkingViolation, Long> {
    List<ParkingViolation> findByUserId(long userId);
}
