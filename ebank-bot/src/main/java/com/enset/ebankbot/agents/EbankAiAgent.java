package com.enset.ebankbot.agents;

import io.modelcontextprotocol.client.McpClient;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.ToolCallingAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

@Service
public class EbankAiAgent {
    private final ChatClient chatClient;

    public EbankAiAgent(ChatClient.Builder builder, ChatMemory chatMemory,
                        ToolCallbackProvider tools) {
        this.chatClient = builder
                .defaultSystem("Vous êtes un assistant bancaire. Vous répondez aux questions sur les clients et les comptes. " +
                        "Si la question est hors de ce contexte, répondez 'Je ne sais pas'.")
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .defaultTools(tools)
                .build();
    }

    public String chat(String query, String conversationId) {
        return chatClient.prompt(query)
                .advisors(advisors -> advisors.param(ChatMemory.CONVERSATION_ID, conversationId))
                .call()
                .content();
    }


}