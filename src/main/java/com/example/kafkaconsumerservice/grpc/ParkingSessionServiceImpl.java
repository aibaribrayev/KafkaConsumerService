package com.example.kafkaconsumerservice.grpc;


import com.example.parking.ParkingSession;
import com.example.parking.ParkingSessionServiceGrpc;
import com.example.parking.StreamRequest;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class ParkingSessionServiceImpl extends ParkingSessionServiceGrpc.ParkingSessionServiceImplBase {
    private final BlockingQueue<ParkingSession> parkingSessionQueue = new LinkedBlockingQueue<>();

    public void addParkingSession(ParkingSession parkingSession) {
        parkingSessionQueue.add(parkingSession);
    }

    @Override
    public void streamParkingSessions(StreamRequest request, StreamObserver<ParkingSession> responseObserver) {
        while (true) {
            try {
                ParkingSession parkingSession = parkingSessionQueue.take(); // Блокируется, пока не появится новый элемент.
                responseObserver.onNext(parkingSession);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                responseObserver.onError(e);
                break;
            }
        }
    }
}

