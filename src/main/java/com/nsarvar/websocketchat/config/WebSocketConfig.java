package com.nsarvar.websocketchat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by nsarvar on 12/20/17.
 */

//The @EnableWebSocketMessageBroker is used to enable our WebSocket server
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

/*
 STOMP stands for Simple Text Oriented Messaging Protocol
 It is a messaging protocol that defines the format and rules for data exchange.
 Why do we need STOMP?
 Well, WebSocket is just a communication protocol.
 It doesn’t define things like - How to send a message only to users who are subscribed to a particular
 channel, or how to send a message to a particular user. We need STOMP for these functionalities.

*/
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
//        register a websocket endpoint that the clients will use to connect to our websocket server
//        SockJS is used to enable fallback options for browsers that don’t support websocket
        stompEndpointRegistry.addEndpoint("/ws").withSockJS();
    }

//     configuring a message broker that will be used to route messages from one client to another.

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
//       messages whose destination starts with “/app” should be routed to message-handling methods
        registry.setApplicationDestinationPrefixes("/app");
//        messages whose destination starts with “/channel” should be routed to the message broker
        registry.enableSimpleBroker("/channel");
    }
}
