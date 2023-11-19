package dev.almeida.henrique.chatgptspringboot.controller;

import com.theokanning.openai.OpenAiResponse;
import com.theokanning.openai.assistants.Assistant;
import com.theokanning.openai.assistants.AssistantFile;
import dev.almeida.henrique.chatgptspringboot.service.AssistantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/${api.version}/bot")
public class AssistantController {

    private final AssistantService assistantService;

    @Autowired
    public AssistantController(AssistantService assistantService) {
        this.assistantService = assistantService;
    }

    @GetMapping("/assistant/file/{assistantFileId}")
    public ResponseEntity<AssistantFile> getAssistantFileById(@PathVariable String assistantFileId) {
        return ResponseEntity.ok(assistantService.getAssistantFileById(assistantFileId));
    }

    @GetMapping("/assistant/file")
    public ResponseEntity<OpenAiResponse<Assistant>> getAllAssistantFiles() {
        return ResponseEntity.ok(assistantService.getAllAssistantFiles());
    }

    @GetMapping("/assistant/{assistantId}")
    public ResponseEntity<Assistant> getAssistantById(@PathVariable String assistantId) {
        return ResponseEntity.ok(assistantService.getAssistantById(assistantId));
    }

    @GetMapping("/assistant")
    public ResponseEntity<OpenAiResponse<Assistant>> getAllAssistants() {
        return ResponseEntity.ok(assistantService.getAllAssistants());
    }
}
