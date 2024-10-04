package com.akshay.backend.ServiceImplementation;

import com.akshay.backend.Model.Chat;
import com.akshay.backend.Model.User;
import com.akshay.backend.Repository.ChatRepository;
import com.akshay.backend.Service.ChatService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class ChatServiceImplementation implements ChatService {
    private final ChatRepository chatRepository;
    @Override
    public Chat createChat(User reqUser, User user2) {
        Chat isExist= chatRepository.findChatByUsersId(user2, reqUser);
        if(isExist != null)
        {
            return isExist;
        }
        Chat chat= new Chat();
        chat.getUsers().add(user2);
        chat.getUsers().add(reqUser);
        chat.setTimestamp(LocalDateTime.now());
        return chatRepository.save(chat);
    }

    @Override
    public Chat findChatById(Integer chatId) throws Exception {
        return chatRepository.findById(chatId).orElseThrow(
                () -> new Exception("Chat not found")
        );
    }

    @Override
    public List<Chat> findUsersChat(Integer userId) {
        return chatRepository.findByUsersId(userId);
    }

    @Override
    public String deleteChat(Integer chatId, User user) throws Exception {

        Chat chat=chatRepository.findById(chatId).orElseThrow(
                () -> new Exception("Chat Not  found !!")
        );

        if (Objects.equals(user.getId(), chat.getUsers().get(1).getId())) {
            chatRepository.delete(chat);

            return "Chat Deleted Successfully";
        }
        else {
            return "You can not delete this chat";
        }
    }
}
