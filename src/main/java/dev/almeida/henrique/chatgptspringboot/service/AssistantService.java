package dev.almeida.henrique.chatgptspringboot.service;

import com.theokanning.openai.ListSearchParameters;
import com.theokanning.openai.assistants.Assistant;
import com.theokanning.openai.assistants.AssistantFile;
import com.theokanning.openai.assistants.AssistantFileRequest;
import com.theokanning.openai.service.OpenAiService;
import dev.almeida.henrique.chatgptspringboot.exception.AssistantNotFoundException;
import dev.almeida.henrique.chatgptspringboot.exception.FileNotFoundException;
import dev.almeida.henrique.chatgptspringboot.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AssistantService {

    private final OpenAiService aiService = new OpenAiService(Constant.OPENAPI_TOKEN);

    public AssistantFile getAssistantFileById(String fileId) {
        try {
            log.info(String.format("Search file assistant by ID %s", fileId));
            return aiService.retrieveAssistantFile(Constant.ASSISTANT_ID, fileId);
        } catch (RuntimeException exception) {
            log.error(String.format("Not found file assistant by ID %s", fileId));
            throw new FileNotFoundException(fileId);
        }
    }

    public List<Assistant> getAllAssistantFiles() {
        log.info("Return all file assistant");
        return aiService.listAssistantFiles(
                Constant.ASSISTANT_ID,
                ListSearchParameters.builder().build()
        ).data;
    }

    public Assistant getAssistantById(String id) {
        try {
            log.info(String.format("Search assistant by ID %s", id));
            return aiService.retrieveAssistant(id);
        } catch (RuntimeException exception) {
            log.error(String.format("Not found assistant by ID %s", id));
            throw new AssistantNotFoundException(id);
        }
    }

    public List<Assistant> getAllAssistants() {
        log.info("Return all assistants");
        return aiService.listAssistants(ListSearchParameters.builder().build()).data;
    }

    public AssistantFile postAddFileInTheAssistant(String fileId) {
        return aiService.createAssistantFile(
                Constant.ASSISTANT_ID,
                AssistantFileRequest.builder().fileId(fileId).build()
        );
    }
}
