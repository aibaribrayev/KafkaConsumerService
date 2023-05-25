package com.example.kafkaconsumerservice.grpc;


import com.example.parking.ParkingSession;
import com.example.parking.ParkingSessionServiceGrpc;
import com.example.parking.StreamRequest;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class ParkingSessionServiceImpl extends ParkingSessionServiceGrpc.ParkingSessionServiceImplBase {
    private final ConcurrentLinkedQueue<StreamObserver<ParkingSession>> observers = new ConcurrentLinkedQueue<>();

    public void addParkingSession(ParkingSession parkingSession) {
        Iterator<StreamObserver<ParkingSession>> iterator = observers.iterator();
        while (iterator.hasNext()) {
            StreamObserver<ParkingSession> observer = iterator.next();
            try {
                observer.onNext(parkingSession);
            } catch (Exception e) {
                // Если произошла ошибка при отправке данных, удаляем observer из списка
                // Необходимо отметить, что метод remove() итератора CopyOnWriteArrayList не поддерживается, поэтому вместо него используется observers.remove().
                observers.remove(observer);
            }
        }
    }

    @Override
    public void streamParkingSessions(StreamRequest request, StreamObserver<ParkingSession> responseObserver) {
        // Добавить observer в список при подключении нового клиента
        observers.add(responseObserver);

        // Если серверу нужно отключить соединение с клиентом, он может вызвать responseObserver.onCompleted();
        // Если серверу нужно сообщить клиенту об ошибке, он может вызвать responseObserver.onError(t);
    }
}

