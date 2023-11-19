package dev.almeida.henrique.chatgptspringboot.service;

import com.theokanning.openai.messages.Message;
import com.theokanning.openai.messages.MessageRequest;
import com.theokanning.openai.service.OpenAiService;
import dev.almeida.henrique.chatgptspringboot.util.Constant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageUserService {

    private final OpenAiService aiService = new OpenAiService(Constant.OPENAPI_TOKEN);

    public Message postAddMessageInThread(String threadId, String message, String fileId) {

        if (fileId == null)
            return this.postAddMessageInThread(threadId, message);

        return aiService.createMessage(threadId, MessageRequest.builder().content(message).fileIds(List.of(fileId)).build());
    }

    public Message getMessageById(String threadId, String messageId) {
        return aiService.retrieveMessage(threadId, messageId);
    }

    public List<Message> getAllMessagesByThread(String threadId) {
        return aiService.listMessages(threadId).data;
    }

    private Message postAddMessageInThread(String threadId, String message) {
        return aiService.createMessage(threadId, MessageRequest.builder().content(message).build());
    }
}
