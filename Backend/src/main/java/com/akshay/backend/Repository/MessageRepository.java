package com.akshay.backend.Repository;

import com.akshay.backend.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Integer> {

    List<Message> findByChatId(Integer chatId);
}
