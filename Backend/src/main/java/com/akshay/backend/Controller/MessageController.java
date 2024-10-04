package com.akshay.backend.Controller;

import com.akshay.backend.Model.Message;
import com.akshay.backend.Model.User;
import com.akshay.backend.Service.ChatService;
import com.akshay.backend.Service.MessageService;
import com.akshay.backend.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/message/")
public class MessageController {
    private final MessageService messageService;
    private final UserService  userService;
    private final ChatService chatService;

    @PostMapping("chat/{chatId}")
    public Message createMessage(
            @RequestHeader("Authorization") String token, @RequestBody Message message, @PathVariable Integer chatId) throws Exception {
        User user=userService.getUserFromToken(token);
        return messageService.createMessage(user,chatId,message);
    }

    @GetMapping("chat/{chatId}")
    public List<Message> findChatsMessages(@PathVariable Integer chatId) throws Exception {
        return messageService.findChatsMessage(chatId);
    }
}
