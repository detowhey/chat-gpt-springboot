package dev.almeida.henrique.chatgptspringboot.controller;

import com.theokanning.openai.messages.Message;
import dev.almeida.henrique.chatgptspringboot.dto.request.MessageUserRequest;
import dev.almeida.henrique.chatgptspringboot.service.MessageUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Tag(name = "Message User endpoint", description = "REST service for message users methods")
@RestController
@RequestMapping(value = "/api/${api.version}/bot", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageUserController {

    private final MessageUserService messageUserService;

    @Autowired
    public MessageUserController(MessageUserService messageUserService) {
        this.messageUserService = messageUserService;
    }

    @Operation(
            summary = "Create message for OpenAi Assistant",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully create message",
                            content = @Content(schema = @Schema(implementation = Message.class))
                    )
            }
    )
    @PostMapping("/message/create")
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

    @Operation(
            summary = "Search Message by ID and Thread",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully found Message by ID and Thread",
                            content = @Content(schema = @Schema(implementation = Message.class))
                    )
            }
    )
    @GetMapping("/message/{threadId}/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable String threadId, @PathVariable String messageId) {
        return ResponseEntity.ok(messageUserService.getMessageById(threadId, messageId));
    }

    @Operation(
            summary = "Return messages by Thread",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully found Messages by Thread ID",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Message.class)))
                    )
            }
    )
    @GetMapping("/message/{threadId}")
    public ResponseEntity<List<Message>> getAllMessageByThread(@PathVariable String threadId) {
        return ResponseEntity.ok(messageUserService.getAllMessagesByThread(threadId));
    }
}
