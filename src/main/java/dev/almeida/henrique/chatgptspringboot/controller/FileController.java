package dev.almeida.henrique.chatgptspringboot.controller;

import com.theokanning.openai.file.File;
import dev.almeida.henrique.chatgptspringboot.dto.request.FileRequest;
import dev.almeida.henrique.chatgptspringboot.service.FileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/api/${api.version}/bot")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/file")
    public ResponseEntity<List<File>> returnAllFiles() {
        return ResponseEntity.ok().body(fileService.listFiles());
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<File> getFileById(@PathVariable String id) {
        return ResponseEntity.ok(fileService.getFileById(id));
    }


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
