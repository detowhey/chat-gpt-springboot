package dev.almeida.henrique.chatgptspringboot.service;

import com.theokanning.openai.DeleteResult;
import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.threads.Thread;
import com.theokanning.openai.threads.ThreadRequest;
import dev.almeida.henrique.chatgptspringboot.exception.ThreadBotNotFoundException;
import dev.almeida.henrique.chatgptspringboot.util.Constant;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ThreadBotService {

    private static final String NOT_FOUND_LOG_MESSAGE = "Not found Thread by ID {}";
    private final OpenAiService aiService = new OpenAiService(Constant.OPENAPI_TOKEN);

    public Thread getThreadById(String threadId) {
        return Try.of(() -> {
                    log.info("Search Thread by ID {}", threadId);
                    return aiService.retrieveThread(threadId);
                })
                .onFailure(exception -> log.error(NOT_FOUND_LOG_MESSAGE, threadId, exception))
                .getOrElseThrow(exception -> new ThreadBotNotFoundException(threadId));
    }

    public Thread postCreateThread() {
        log.info("Create a new Thread");
        return aiService.createThread(ThreadRequest.builder().build());
    }

    public DeleteResult deleteThread(String threadId) {
        return Try.of(() -> {
                    log.info("Delete Thread with ID {}", threadId);
                    return aiService.deleteThread(threadId);
                })
                .onFailure(exception -> log.error(NOT_FOUND_LOG_MESSAGE, threadId, exception))
                .getOrElseThrow(exception -> new ThreadBotNotFoundException(threadId));
    }
}
