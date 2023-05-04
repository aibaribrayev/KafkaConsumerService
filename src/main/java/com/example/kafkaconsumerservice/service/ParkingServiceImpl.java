package com.example.kafkaconsumerservice.service;

import com.example.kafkaconsumerservice.respository.ParkingSpotRepository;
import com.example.kafkaconsumerservice.model.ParkingSpot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingServiceImpl implements ParkingService {

//    @Autowired
//    private ParkingHistoryRepository parkingHistoryRepository;
//
//    @Autowired
//    private ParkingViolationRepository parkingViolationRepository;
    private final ParkingSpotRepository parkingSpotRepository;

    @Autowired
    public ParkingServiceImpl(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    @Override
    public List<ParkingSpot> getAllParkingSpots() {
        return parkingSpotRepository.findAll();
    }

    @Override
    public ParkingSpot addParkingSpot(ParkingSpot parkingSpot) {
        return this.parkingSpotRepository.save(parkingSpot);
    }

    @Override
    public ParkingSpot getParkingSpotBySensorId(String id) {
        //return parkingSpotRepository.findById(Long.valueOf(id)).orElse(null);
        return parkingSpotRepository.findBySensorId(id);
    }

    @Override
    public ParkingSpot getParkingSpotById(String id) {
        return parkingSpotRepository.findById(Long.valueOf(id)).orElse(null);
    }

    @Override
    public ParkingSpot updateParkingSpotStatus(Long id, boolean isOccupied) {
        ParkingSpot parkingSpot = parkingSpotRepository.findById(Long.valueOf(id)).orElse(null);
        if (parkingSpot != null) {
            parkingSpot.setOccupied(isOccupied);
            //parkingSpot.(LocalDateTime.now());
            return parkingSpotRepository.save(parkingSpot);
        }
        return null;
    }
    @Override
    public ParkingSpot startParkingSession(String id) {
//        ParkingSpot parkingSpot = parkingSpotRepository.findById(Long.valueOf(id)).orElse(null);
//        if (parkingSpot != null) {
//            parkingSpot.setStartTime(LocalDateTime.now());
//            return parkingSpotRepository.save(parkingSpot);
//        }
//        return null;
        ParkingSpot parkingSpot = parkingSpotRepository.findById(Long.valueOf(id)).orElse(null);
        if (parkingSpot != null) {
            parkingSpot.setStartTime(LocalDateTime.now());
            return parkingSpotRepository.save(parkingSpot);
        }
        return null;
    }
    @Override
    public ParkingSpot stopParkingSession(String id) {
        ParkingSpot parkingSpot = parkingSpotRepository.findById(Long.valueOf(id)).orElse(null);
        if (parkingSpot != null) {
            parkingSpot.setEndTime(LocalDateTime.now());
            return parkingSpotRepository.save(parkingSpot);
        }
        return null;
    }

//    @Override
//    public double calculateParkingFee(String id, LocalDateTime startTime, LocalDateTime endTime) {
//        // Реализуйте расчет стоимости парковки в зависимости от времени начала и окончания.
//        // Например, вы можете использовать фиксированную ставку за час.
//        double hourlyRate = 2.0;
//        long parkingDuration = Duration.between(startTime, endTime).toHours();
//        return hourlyRate * parkingDuration;
//    }
    @Override
    public List<ParkingSpot> getNearbyAvailableSpots(double latitude, double longitude, double radius) {
        List<ParkingSpot> allSpots = parkingSpotRepository.findAll();
        List<ParkingSpot> nearbyAvailableSpots = new ArrayList<>();

        for (ParkingSpot spot : allSpots) {
            if (!spot.getIsOccupied()) {
                double distance = calculateDistance(latitude, longitude, spot.getLatitude(), spot.getLongitude());
                if (distance <= radius) {
                    nearbyAvailableSpots.add(spot);
                }
            }
        }

        return nearbyAvailableSpots;
    }
    private static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double earthRadius = 6371e3; // радиус Земли в метрах
        double lat1Radians = Math.toRadians(lat1);
        double lat2Radians = Math.toRadians(lat2);
        double deltaLat = Math.toRadians(lat2 - lat1);
        double deltaLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(lat1Radians) * Math.cos(lat2Radians) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c;
    }

//    @Override
//    public List<ParkingHistory> getParkingHistory(long userId) {
//        return parkingHistoryRepository.findByUserId(userId);
//    }
//
//    @Override
//    public List<ParkingViolation> getViolations(long userId) {
//        return parkingViolationRepository.findByUserId(userId);
//    }
//
//    @Override
//    public boolean payParkingFee(long parkingSpotId, PaymentMethod paymentMethod) {
//        // Здесь вы можете интегрировать сервис оплаты и обработать оплату.
//        // Если оплата успешна, обновите информацию о парковке и верните true.
//        // В противном случае верните false.
//        return false; // временная заглушка
//    }
}

//@Service
//public class ParkingServiceImpl implements ParkingService {
//    // Используйте HashMap для хранения и обработки данных о парковочных местах
//    private Map<Long, ParkingSpot> parkingSpots;
//
//    public ParkingServiceImpl() {
//        parkingSpots = new HashMap<>();
//
//        // Заполните парковочные места тестовыми данными
//        parkingSpots.put(1L, new ParkingSpot("S001", 55.759132, 37.705266));
//        //parkingSpots.put(2L, new ParkingSpot("S002", 49.8429, 24.0313));
//        //parkingSpots.put(3L, new ParkingSpot("S003", 49.8432, 24.0265));
//    }
//
//    @Override
//    public List<ParkingSpot> getAllParkingSpots() {
//        return new ArrayList<>(parkingSpots.values());
//    }
//
//    @Override
//    public ParkingSpot getParkingSpotById(String id) {
//        return parkingSpots.get(id);
//    }
//
//    @Override
//    public ParkingSpot updateParkingSpotStatus(String id, boolean isOccupied) {
//        ParkingSpot parkingSpot = parkingSpots.get(id);
//
//        if (parkingSpot != null) {
//            parkingSpot.setOccupied(isOccupied);
//            parkingSpot.setTimestamp(LocalDateTime.now());
//        }
//
//        return parkingSpot;
//    }
//}
