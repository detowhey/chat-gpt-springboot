package dev.almeida.henrique.chatgptspringboot.controller;

import com.theokanning.openai.assistants.Assistant;
import com.theokanning.openai.assistants.AssistantFile;
import dev.almeida.henrique.chatgptspringboot.service.AssistantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Assistant", description = "Assistant methods")
@RestController
@RequestMapping(value = "/api/${api.version}/bot")
public class AssistantController {

    private final AssistantService assistantService;

    @Autowired
    public AssistantController(AssistantService assistantService) {
        this.assistantService = assistantService;
    }

    @Operation(
            summary = "Search File Assistant by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully found File Assistant by id",
                            content = @Content(schema = @Schema(implementation = AssistantFile.class))
                    )
            }
    )
    @GetMapping("/assistant/file/{assistantFileId}")
    public ResponseEntity<AssistantFile> getAssistantFileById(@PathVariable String assistantFileId) {
        return ResponseEntity.ok(assistantService.getAssistantFileById(assistantFileId));
    }

    @Operation(
            summary = "Return all Files Assistant",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully found all Files Assistant",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = AssistantFile.class)))
                    )
            }
    )
    @GetMapping("/assistant/file")
    public ResponseEntity<List<Assistant>> getAllAssistantFiles() {
        return ResponseEntity.ok(assistantService.getAllAssistantFiles());
    }

    @Operation(
            summary = "Search Assistant by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully found Assistant by id",
                            content = @Content(schema = @Schema(implementation = Assistant.class))
                    )
            }
    )
    @GetMapping("/assistant/{assistantId}")
    public ResponseEntity<Assistant> getAssistantById(@PathVariable String assistantId) {
        return ResponseEntity.ok(assistantService.getAssistantById(assistantId));
    }

    @Operation(
            summary = "Return all Assistants",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully found all Assistants",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Assistant.class)))
                    )
            }
    )
    @GetMapping("/assistant")
    public ResponseEntity<List<Assistant>> getAllAssistants() {
        return ResponseEntity.ok(assistantService.getAllAssistants());
    }
}
