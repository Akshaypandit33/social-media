package com.akshay.backend.Service;

import com.akshay.backend.Model.Chat;
import com.akshay.backend.Model.User;

import java.util.List;

public interface ChatService {

    Chat createChat(User reqUser, User user2);
    Chat findChatById(Integer chatId) throws Exception;

    List<Chat> findUsersChat(Integer userId);

    String deleteChat(Integer chatId, User user) throws Exception;

}
