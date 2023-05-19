package com.example.kafkaconsumerservice.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Service
public class WebSocketConfiguration implements WebSocketConfigurer, WebSocketMessageBrokerConfigurer {
    @Autowired
    private ParkingSpotWebSocketHandler parkingSpotWebSocketHandler;
    @Autowired
    private ParkingWebSocketHandler parkingWebSocketHandler;

    //    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(parkingSpotWebSocketHandler, "/parking-spot-updates")
//                .setAllowedOrigins("*");// .setAllowedOrigins("http://allowed-origin.com"); Замените "http://allowed-origin.com" на конкретные разрешенные источники
//    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/hello").withSockJS();
    }


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(parkingSpotWebSocketHandler, "/parking-spot-updates");

        registry.addHandler(parkingWebSocketHandler, "/parking-history-updates");
    }
}
