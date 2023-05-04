package com.example.kafkaconsumerservice;

import com.example.kafkaconsumerservice.model.ParkingSpot;
import com.example.kafkaconsumerservice.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/parking-spots")
public class ParkingSpotController {
    private final ParkingService parkingService;
    @Autowired
    public ParkingSpotController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParkingSpot addParkingSpot(@RequestBody ParkingSpot parkingSpot) {
        return parkingService.addParkingSpot(parkingSpot);
    }

    @PostMapping("/new")
    public ResponseEntity<ParkingSpot> createParkingSpot(@RequestBody ParkingSpot parkingSpot) {
        ParkingSpot savedSpot = parkingService.addParkingSpot(parkingSpot);
        return ResponseEntity.created(URI.create("/parking-spots/" + savedSpot.getId()))
                .body(savedSpot);
    }
    @PutMapping("/{id}")
    public ParkingSpot updateParkingSpotStatus(@PathVariable Long id, @RequestParam boolean isOccupied) {
        // Обработка ошибки, если парковочное место не найдено
        //ParkingSpot parkingSpot = parkingService.getParkingSpotById(id).(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Parking spot not found"));
        return parkingService.updateParkingSpotStatus(id, isOccupied);
    }

//    @GetMapping("/sensor/{id}]")
//    public ParkingSpot getParkingSpotBySensorId(@PathVariable String id) {
//        return parkingService.getParkingSpotBySensorId(id);
//    }
    @GetMapping("/sensor/{sensorId}")
    public ResponseEntity<ParkingSpot> getParkingSpotBySensorId(@PathVariable String sensorId) {
        ParkingSpot parkingSpot = parkingService.getParkingSpotBySensorId(sensorId);
        if (parkingSpot == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(parkingSpot);
    }

    @GetMapping
    public List<ParkingSpot> getAllParkingSpots() {
        return parkingService.getAllParkingSpots();
    }

    @GetMapping("/{id}")
    public ParkingSpot getParkingSpotById(@PathVariable String id) {
        // Обработка ошибки, если парковочное место не найдено
        return parkingService.getParkingSpotById(id);//.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Parking spot not found"));
    }

    @PostMapping("/{id}/start-session")
    public ParkingSpot startParkingSession(@PathVariable String id) {
        // Обработка ошибки, если парковочное место не найдено
        ParkingSpot parkingSpot = parkingService.getParkingSpotById(id);
        if (parkingSpot == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Parking spot not found");
        }
        return parkingService.startParkingSession(id);
    }

    @PostMapping("/{id}/stop-session")
    public ParkingSpot stopParkingSession(@PathVariable String id) {
        // Обработка ошибки, если парковочное место не найдено
        ParkingSpot parkingSpot = parkingService.getParkingSpotById(id);
        if (parkingSpot == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Parking spot not found");
        }
        parkingSpot.setEndTime(LocalDateTime.now());
        return parkingService.stopParkingSession(id);
    }

    @GetMapping("/available")
    public List<ParkingSpot> getAvailableParkingSpots(@RequestParam double latitude, @RequestParam double longitude, @RequestParam double radius) {
        return parkingService.getNearbyAvailableSpots(latitude, longitude, radius);
    }

}
