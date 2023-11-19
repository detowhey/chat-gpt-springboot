package dev.almeida.henrique.chatgptspringboot.controller;

import com.theokanning.openai.DeleteResult;
import com.theokanning.openai.threads.Thread;
import dev.almeida.henrique.chatgptspringboot.service.ThreadBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/${api.version}/bot", produces = MediaType.APPLICATION_JSON_VALUE)
public class ThreadBotController {

    private final ThreadBotService threadBotService;

    @Autowired
    public ThreadBotController(ThreadBotService threadBotService) {
        this.threadBotService = threadBotService;
    }

    @GetMapping("/thread/{threadBotId}")
    public ResponseEntity<Thread> getThreadBotById(@PathVariable String threadBotId) {
        return ResponseEntity.ok(threadBotService.getThreadById(threadBotId));
    }

    @PostMapping("/thread/create")
    public ResponseEntity<Thread> createThread() {
        return ResponseEntity.status(HttpStatus.CREATED).body(threadBotService.postCreateThread());
    }

    @DeleteMapping("/thread/{threadId}/delete")
    public ResponseEntity<DeleteResult> deleteThreadById(@PathVariable String threadId) {
        return ResponseEntity.ok().body(threadBotService.deleteThread(threadId));
    }
}
