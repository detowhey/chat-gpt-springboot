package dev.almeida.henrique.chatgptspringboot.controller;

import com.theokanning.openai.runs.Run;
import dev.almeida.henrique.chatgptspringboot.service.RunService;
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

@Tag(name = "Run endpoint", description = "REST service for run methods")
@RestController
@RequestMapping(value = "/api/${api.version}/bot", produces = MediaType.APPLICATION_JSON_VALUE)
public class RunController {

    private final RunService runService;

    @Autowired
    public RunController(RunService runService) {
        this.runService = runService;
    }

    @Operation(
            summary = "Search Run by ID and Thread",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully found Run by id and Thread",
                            content = @Content(schema = @Schema(implementation = Run.class))
                    )
            }
    )
    @GetMapping("/run/{threadId}/{runId}")
    public ResponseEntity<Run> getRunById(@PathVariable String threadId, @PathVariable String runId) {
        return ResponseEntity.ok().body(runService.getRunById(threadId, runId));
    }

    @Operation(
            summary = "Create Run with Thread ID",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully create Run with Thread",
                            content = @Content(schema = @Schema(implementation = Run.class))
                    )
            }
    )
    @PostMapping("/run/{threadId}/create")
    public ResponseEntity<Run> createRun(@PathVariable String threadId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(runService.postCreateRun(threadId));
    }

    @Operation(
            summary = "Cancel Run with ID and Thread ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully cancel Run by ID with Thread",
                            content = @Content(schema = @Schema(implementation = Run.class))
                    )
            }
    )
    @PostMapping("/run/{threadId}/cancel/{runId}")
    public ResponseEntity<Run> cancelRun(@PathVariable String threadId, @PathVariable String runId) {
        return ResponseEntity.ok(runService.postCancelRun(threadId, runId));
    }
}
