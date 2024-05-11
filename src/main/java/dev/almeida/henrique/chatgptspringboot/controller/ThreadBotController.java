package dev.almeida.henrique.chatgptspringboot.controller;

import com.theokanning.openai.DeleteResult;
import com.theokanning.openai.threads.Thread;
import dev.almeida.henrique.chatgptspringboot.service.ThreadBotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Thread", description = "Thread methods")
@RestController
@RequestMapping(value = "/api/${api.version}/bot", produces = MediaType.APPLICATION_JSON_VALUE)
public class ThreadBotController {

    private final ThreadBotService threadBotService;

    @Autowired
    public ThreadBotController(ThreadBotService threadBotService) {
        this.threadBotService = threadBotService;
    }

    @Operation(
            summary = "Search Thread by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully found Thread by ID",
                            content = @Content(schema = @Schema(implementation = Thread.class))
                    )
            }
    )
    @GetMapping("/thread/{threadBotId}")
    public ResponseEntity<Thread> getThreadBotById(@PathVariable String threadBotId) {
        return ResponseEntity.ok(threadBotService.getThreadById(threadBotId));
    }

    @Operation(
            summary = "Create a Thread",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully create a Thread",
                            content = @Content(schema = @Schema(implementation = Thread.class))
                    )
            }
    )
    @PostMapping("/thread/create")
    public ResponseEntity<Thread> createThread() {
        return ResponseEntity.status(HttpStatus.CREATED).body(threadBotService.postCreateThread());
    }

    @Operation(
            summary = "Delete a Thread by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully delete Thread by ID",
                            content = @Content(schema = @Schema(implementation = Thread.class))
                    )
            }
    )
    @DeleteMapping("/thread/{threadId}/delete")
    public ResponseEntity<DeleteResult> deleteThreadById(@PathVariable String threadId) {
        return ResponseEntity.ok().body(threadBotService.deleteThread(threadId));
    }
}
