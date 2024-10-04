package com.akshay.backend.ServiceImplementation;

import com.akshay.backend.Model.Chat;
import com.akshay.backend.Model.Message;
import com.akshay.backend.Model.User;
import com.akshay.backend.Repository.ChatRepository;
import com.akshay.backend.Repository.MessageRepository;
import com.akshay.backend.Service.ChatService;
import com.akshay.backend.Service.MessageService;
import com.akshay.backend.Service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Data
@RequiredArgsConstructor
@Service
public class MessageServiceImplementation implements MessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;
    private final ChatService chatService;
    private final ChatRepository chatRepository;
    @Override
    public Message createMessage(User user, Integer chatId, Message req) throws Exception {

        Chat chat= chatService.findChatById(chatId);
        Message message=new Message();
        message.setChat(chat);
        message.setContent(req.getContent());
        message.setUser(user);
        message.setTimestamp(LocalDateTime.now());
        message.setImage(req.getImage());

        Message savedMessage= messageRepository.save(message);
        chatRepository.save(chat);
        return savedMessage;
    }

    @Override
    public List<Message> findChatsMessage(Integer chatId) throws Exception {
        return messageRepository.findByChatId(chatId);
    }
}
