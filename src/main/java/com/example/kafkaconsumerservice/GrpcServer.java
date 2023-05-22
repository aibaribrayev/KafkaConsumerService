package com.example.kafkaconsumerservice;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class GrpcServer {

    private Server server;

    private final ParkingSpotServiceImpl parkingSpotService;

    public GrpcServer(ParkingSpotServiceImpl parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    @PostConstruct
    private void start() throws IOException {
        /* Сервер будет слушать на порту 9090. */
        int port = 9090;
        server = ServerBuilder.forPort(port)
                .addService(parkingSpotService)  // Добавляем сервис
                .build()
                .start();

        System.out.println("Server started, listening on " + port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** JVM is shutting down. Turning off grpc server ***");
            this.stop();
            System.err.println("*** Server shut down ***");
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    // Запускаем сервер
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}

