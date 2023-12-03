package dev.almeida.henrique.chatgptspringboot.service;

import com.theokanning.openai.DeleteResult;
import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.threads.Thread;
import com.theokanning.openai.threads.ThreadRequest;
import dev.almeida.henrique.chatgptspringboot.exception.ThreadBotNotFoundException;
import dev.almeida.henrique.chatgptspringboot.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ThreadBotService {

    private final OpenAiService aiService = new OpenAiService(Constant.OPENAPI_TOKEN);

    public Thread getThreadById(String threadId) {
        try {
            log.info(String.format("Search Thread by ID %s", threadId));
            return aiService.retrieveThread(threadId);
        } catch (RuntimeException exception) {
            log.error(String.format("Not found Thread by ID %s", threadId));
            throw new ThreadBotNotFoundException(threadId);
        }
    }

    public Thread postCreateThread() {
        log.info("Create a new Thread");
        return aiService.createThread(ThreadRequest.builder().build());
    }

    public DeleteResult deleteThread(String threadId) {
        try {
            log.info(String.format("Delete Thread with ID %s", threadId));
            return aiService.deleteThread(threadId);
        } catch (RuntimeException exception) {
            log.error(String.format("Not found Thread by ID %s", threadId));
            throw new ThreadBotNotFoundException(threadId);
        }
    }
}
