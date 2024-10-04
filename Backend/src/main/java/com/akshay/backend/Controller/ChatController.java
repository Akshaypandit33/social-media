package com.akshay.backend.Controller;

import com.akshay.backend.Model.Chat;
import com.akshay.backend.Model.User;
import com.akshay.backend.Request.CreateChatRequest;
import com.akshay.backend.Service.ChatService;
import com.akshay.backend.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/chats")
public class ChatController {

    private final ChatService chatService;
    private final UserService userService;

    @PostMapping("")
    public Chat createChat(@RequestHeader("Authorization") String token, @RequestBody CreateChatRequest req) throws Exception {
        User reqUser=userService.getUserFromToken(token);
        User user2=userService.findUserById(req.getUserId());
        return chatService.createChat(reqUser,user2) ;
    }

    @GetMapping("")
    public List<Chat> findUsersChat (@RequestHeader("Authorization") String token){
        return chatService.findUsersChat(userService.getUserFromToken(token).getId()) ;
    }

    @DeleteMapping("{chatId}")
    public String deleteChat(@PathVariable Integer chatId, @RequestHeader("Authorization") String token) throws Exception {
        User user=userService.getUserFromToken(token);
        return chatService.deleteChat(chatId,user);
    }

}
