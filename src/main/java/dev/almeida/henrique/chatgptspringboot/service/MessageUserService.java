package dev.almeida.henrique.chatgptspringboot.service;

import com.theokanning.openai.messages.Message;
import com.theokanning.openai.messages.MessageRequest;
import com.theokanning.openai.service.OpenAiService;
import dev.almeida.henrique.chatgptspringboot.exception.MessaUserNotFoundException;
import dev.almeida.henrique.chatgptspringboot.exception.ThreadBotNotFoundException;
import dev.almeida.henrique.chatgptspringboot.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MessageUserService {

    private final OpenAiService aiService = new OpenAiService(Constant.OPENAPI_TOKEN);

    public Message postAddMessageInThread(String threadId, String message, String fileId) {
        try {
            if (fileId == null)
                return this.postAddMessageInThread(threadId, message);

            log.info(String.format("Create message in Thread %s", threadId));
            return aiService.createMessage(threadId, MessageRequest.builder().content(message).fileIds(List.of(fileId)).build());
        } catch (RuntimeException exception) {
            log.error(String.format("Not found Thread by ID %s", threadId));
            throw new ThreadBotNotFoundException(threadId);
        }
    }

    public Message getMessageById(String threadId, String messageId) {
        try {
            log.info(String.format("Search Message by ID %s", messageId));
            return aiService.retrieveMessage(threadId, messageId);
        } catch (RuntimeException exception) {
            log.error(String.format("Not found Thread by ID %s", threadId));
            throw new ThreadBotNotFoundException(threadId);
        } catch (Exception exception) {
            log.error(String.format("Not found Message by ID %s", messageId));
            throw new MessaUserNotFoundException(messageId);
        }
    }

    public List<Message> getAllMessagesByThread(String threadId) {
        try {
            log.info(String.format("Search all Messages thread by ID %s", threadId));
            return aiService.listMessages(threadId).data;
        } catch (RuntimeException exception) {
            log.error(String.format("Not found Messages by thread ID %s", threadId));
            throw new ThreadBotNotFoundException(threadId);
        }

    }

    private Message postAddMessageInThread(String threadId, String message) {
        log.info(String.format("Create message in Thread with ID %s", threadId));
        return aiService.createMessage(threadId, MessageRequest.builder().content(message).build());
    }
}
