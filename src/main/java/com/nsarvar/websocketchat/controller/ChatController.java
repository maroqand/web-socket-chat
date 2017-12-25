package com.nsarvar.websocketchat.controller;

import com.nsarvar.websocketchat.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Created by nsarvar on 12/20/17.
 */
@Controller
public class ChatController {

    /*
    * all the messages sent from clients with a destination starting with /app will be routed to
    * these message handling methods annotated with @MessageMapping
    * For example, a message with destination /app/chat.sendMessage will be routed to
    * the sendMessage() method, and a message with destination /app/chat.addUser will be routed to
    * the addUser() method.
    */

    @CrossOrigin
    @MessageMapping("/chat.sendMessage")
    @SendTo("/channel/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage){
        return chatMessage;
    }

    @CrossOrigin
    @MessageMapping("/chat.addUser")
    @SendTo("/channel/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

}
