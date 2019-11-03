package com.chatapp.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.chatapp.model.ChatMessage;

@Controller
public class ChatController {

	//Register new user
	@MessageMapping("/chat.register") //Map Client to server
	@SendTo("/topic/public") //Specify the queue
	public ChatMessage newUser(@Payload ChatMessage chatMessage, 
			SimpMessageHeaderAccessor headerAccessor) {
		// Add new user in web socket session
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		return chatMessage;
	}

	//To send a message
	@MessageMapping("/chat.send")
	@SendTo("/topic/public")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
		return chatMessage;
	}

}
