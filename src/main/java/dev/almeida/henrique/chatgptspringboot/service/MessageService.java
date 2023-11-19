package dev.almeida.henrique.chatgptspringboot.service;

import com.theokanning.openai.OpenAiResponse;
import com.theokanning.openai.messages.Message;
import com.theokanning.openai.messages.MessageRequest;
import com.theokanning.openai.service.OpenAiService;
import dev.almeida.henrique.chatgptspringboot.util.Constant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final OpenAiService aiService = new OpenAiService(Constant.OPENAPI_TOKEN);

    public Message postAddMessageInThread(String threadId, String message, String fileId) {
        return aiService.createMessage(threadId, MessageRequest.builder().content(message).fileIds(List.of(fileId)).build());
    }

    public Message getMessageById(String threadId, String messageId) {
        return aiService.retrieveMessage(threadId, messageId);
    }

    public OpenAiResponse<Message> getAllMessagesByThread(String threadId) {
        return aiService.listMessages(threadId);
    }
}
