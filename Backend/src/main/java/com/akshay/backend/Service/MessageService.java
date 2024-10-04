package com.akshay.backend.Service;

import com.akshay.backend.Model.Chat;
import com.akshay.backend.Model.Message;
import com.akshay.backend.Model.User;

import java.util.List;

public interface MessageService {

    Message createMessage(User user, Integer chatId, Message req) throws Exception;

    List<Message> findChatsMessage(Integer chatId) throws Exception;


}
