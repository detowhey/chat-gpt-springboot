package dev.almeida.henrique.chatgptspringboot.controller;

import com.theokanning.openai.OpenAiResponse;
import com.theokanning.openai.messages.Message;
import dev.almeida.henrique.chatgptspringboot.dto.request.MessageUserRequest;
import dev.almeida.henrique.chatgptspringboot.service.MessageUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/api/${api.version}/bot", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageUserController {

    private final MessageUserService messageUserService;

    @Autowired
    public MessageUserController(MessageUserService messageUserService) {
        this.messageUserService = messageUserService;
    }

    @PostMapping("/message/add")
    public ResponseEntity<Message> postAddMessageInThread(@RequestBody @Valid MessageUserRequest messageUserRequest) {

        var message = messageUserService.postAddMessageInThread(
                messageUserRequest.threadId(), messageUserRequest.message(), messageUserRequest.fileId()
        );

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}").buildAndExpand(message.getId())
                .toUri();

        return ResponseEntity.created(uri).body(message);
    }

    @GetMapping("/message/{threadId}/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable String threadId, @PathVariable String messageId) {
        return ResponseEntity.ok(messageUserService.getMessageById(threadId, messageId));
    }

    @GetMapping("/message/{threadId}")
    public ResponseEntity<OpenAiResponse<Message>> getAllMessageByThread(@PathVariable String threadId) {
        return ResponseEntity.ok(messageUserService.getAllMessagesByThread(threadId));
    }
}
