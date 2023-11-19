package dev.almeida.henrique.chatgptspringboot.controller;

import com.theokanning.openai.runs.Run;
import com.theokanning.openai.runs.RunStep;
import dev.almeida.henrique.chatgptspringboot.service.RunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/${api.version}/bot", produces = MediaType.APPLICATION_JSON_VALUE)
public class RunController {

    private final RunService runService;

    @Autowired
    public RunController(RunService runService) {
        this.runService = runService;
    }

    @GetMapping("/run/{threadId}/{runId}")
    public ResponseEntity<Run> getRunById(@PathVariable String threadId, @PathVariable String runId) {
        return ResponseEntity.ok().body(runService.getRunById(threadId, runId));
    }

    @GetMapping("/run/{threadId}")
    public ResponseEntity<List<Run>> getAllRunsByThreadId(@PathVariable String threadId) {
        return ResponseEntity.ok().body(runService.getAllRuns(threadId));
    }

    @GetMapping("/thread/{threadId}/run/{runId}/step")
    public List<RunStep> getAllStepByRun(@PathVariable String threadId, @PathVariable String runId) {
        return runService.geAllSteps(threadId, runId);
    }

    @GetMapping("/thread/{threadId}/run/{runId}/step/{stepId}")
    public ResponseEntity<RunStep> getStepById(
            @PathVariable String threadId,
            @PathVariable String runId,
            @PathVariable String stepId
    ) {
        return ResponseEntity.ok().body(runService.getRunStepById(threadId, runId, stepId));
    }

    @PostMapping("/run/{threadId}/create")
    public ResponseEntity<Run> createRun(@PathVariable String threadId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(runService.postCreateRun(threadId));
    }

    @PostMapping("/run/{threadId}/cancel/{runId}")
    public ResponseEntity<Run> cancelRun(@PathVariable String threadId, @PathVariable String runId) {
        return ResponseEntity.ok(runService.postCancelRun(threadId, runId));
    }
}
