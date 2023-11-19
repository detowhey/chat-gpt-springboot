package dev.almeida.henrique.chatgptspringboot.service;

import com.theokanning.openai.ListSearchParameters;
import com.theokanning.openai.OpenAiResponse;
import com.theokanning.openai.assistants.Assistant;
import com.theokanning.openai.assistants.AssistantFile;
import com.theokanning.openai.service.OpenAiService;
import dev.almeida.henrique.chatgptspringboot.util.Constant;
import org.springframework.stereotype.Service;

@Service
public class AssistantService {

    private final OpenAiService aiService = new OpenAiService(Constant.OPENAPI_TOKEN);

    public AssistantFile getAssistantFileById(String fileId) {
        return aiService.retrieveAssistantFile(Constant.ASSISTANT_ID, fileId);
    }

    public OpenAiResponse<Assistant> getAllAssistantFiles() {
        return aiService.listAssistantFiles(Constant.ASSISTANT_ID, ListSearchParameters.builder().build());
    }

    public Assistant getAssistantById(String id) {
        return aiService.retrieveAssistant(id);
    }

    public OpenAiResponse<Assistant> getAllAssistants() {
        return aiService.listAssistants(ListSearchParameters.builder().build());
    }

}
