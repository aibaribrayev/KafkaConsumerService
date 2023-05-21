package com.example.kafkaconsumerservice;

import com.example.kafkaconsumerservice.grpc.ParkingSpotProto.ParkingSpot;
import com.example.kafkaconsumerservice.grpc.ParkingSpotProto.StreamRequest;
import com.example.kafkaconsumerservice.grpc.ParkingSpotProto.ParkingSpotServiceGrpc.ParkingSpotServiceImplBase;
import io.grpc.stub.StreamObserver;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ParkingSpotService extends ParkingSpotServiceImplBase {

    private Queue<StreamObserver<ParkingSpot>> observers = new ConcurrentLinkedQueue<>();

    @Override
    public void streamParkingSpots(StreamRequest request, StreamObserver<ParkingSpot> responseObserver) {
        observers.add(responseObserver);
    }

    public void sendParkingSpotUpdate(ParkingSpot parkingSpot) {
        for (StreamObserver<ParkingSpot> observer : observers) {
            observer.onNext(parkingSpot);
        }
    }
}
