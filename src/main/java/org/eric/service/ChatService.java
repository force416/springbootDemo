package org.eric.service;

import org.eric.Repository.ChatRepository;
import org.eric.model.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("chatService")
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;

    @Transactional(rollbackFor = Exception.class)
    public Chat addChat(Chat chat) {
        Optional<Chat> userOptional = chatRepository.findById(chat.getId());
        Chat dbChat = userOptional.orElse(null);

        boolean exists = dbChat != null ? true : false;

        if (!exists) {
            return chatRepository.save(chat);
        }

        return userOptional.get();
    }
}
