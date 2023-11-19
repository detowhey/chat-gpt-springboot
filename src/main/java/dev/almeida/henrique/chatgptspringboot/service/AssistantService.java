package dev.almeida.henrique.chatgptspringboot.service;

import com.theokanning.openai.ListSearchParameters;
import com.theokanning.openai.assistants.Assistant;
import com.theokanning.openai.assistants.AssistantFile;
import com.theokanning.openai.assistants.AssistantFileRequest;
import com.theokanning.openai.service.OpenAiService;
import dev.almeida.henrique.chatgptspringboot.util.Constant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssistantService {

    private final OpenAiService aiService = new OpenAiService(Constant.OPENAPI_TOKEN);

    public AssistantFile getAssistantFileById(String fileId) {
        return aiService.retrieveAssistantFile(Constant.ASSISTANT_ID, fileId);
    }

    public List<Assistant> getAllAssistantFiles() {
        return aiService.listAssistantFiles(Constant.ASSISTANT_ID, ListSearchParameters.builder().build()).data;
    }

    public Assistant getAssistantById(String id) {
        return aiService.retrieveAssistant(id);
    }

    public List<Assistant> getAllAssistants() {
        return aiService.listAssistants(ListSearchParameters.builder().build()).data;
    }

    public AssistantFile postAddFileInTheAssistant(String fileId) {
        return aiService.createAssistantFile(Constant.ASSISTANT_ID, AssistantFileRequest.builder().fileId(fileId).build());
    }
}
