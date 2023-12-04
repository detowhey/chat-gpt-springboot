package dev.almeida.henrique.chatgptspringboot.controller;

import com.theokanning.openai.file.File;
import dev.almeida.henrique.chatgptspringboot.dto.request.FileRequest;
import dev.almeida.henrique.chatgptspringboot.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Tag(name = "File endpoint", description = "REST service for file methods")
@RestController
@RequestMapping(value = "/api/${api.version}/bot")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @Operation(
            summary = "Return all Files",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully found all Files",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = File.class)))
                    )
            }
    )
    @GetMapping("/file")
    public ResponseEntity<List<File>> returnAllFiles() {
        return ResponseEntity.ok().body(fileService.listFiles());
    }

    @Operation(
            summary = "Search File by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully found File by id",
                            content = @Content(schema = @Schema(implementation = File.class))
                    )
            }
    )
    @GetMapping("/file/{id}")
    public ResponseEntity<File> getFileById(@PathVariable String id) {
        return ResponseEntity.ok(fileService.getFileById(id));
    }


    @Operation(
            summary = "Upload File for OpenAi",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully upload File",
                            content = @Content(schema = @Schema(implementation = File.class))
                    )
            }
    )
    @PostMapping("/file")
    public ResponseEntity<File> uploadNewFile(@RequestBody @Valid FileRequest fileRequest) {

        var fileResponse = fileService.uploadFile(fileRequest.purpose(), fileRequest.filePath());

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}").buildAndExpand(fileResponse.getId())
                .toUri();

        return ResponseEntity.created(uri).body(fileResponse);
    }
}
