package com.ai.SpringAiDemo;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Service;

@Service
public class ChartService {
    private final ChatModel chatModel;

    public ChartService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String getResponse(String prompt) {
        return chatModel.call(prompt);
    }
}
