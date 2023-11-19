package dev.almeida.henrique.chatgptspringboot.service;

import com.theokanning.openai.DeleteResult;
import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.threads.Thread;
import com.theokanning.openai.threads.ThreadRequest;
import dev.almeida.henrique.chatgptspringboot.util.Constant;
import org.springframework.stereotype.Service;

@Service
public class ThreadBotService {

    private final OpenAiService aiService = new OpenAiService(Constant.OPENAPI_TOKEN);

    public Thread getThreadById(String threadId) {
        return aiService.retrieveThread(threadId);
    }

    public Thread postCreateThread() {
        return aiService.createThread(ThreadRequest.builder().build());
    }

    public DeleteResult deleteThread(String threadId) {
        return aiService.deleteThread(threadId);
    }
}
