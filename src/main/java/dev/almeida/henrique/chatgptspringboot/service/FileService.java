package dev.almeida.henrique.chatgptspringboot.service;

import com.theokanning.openai.file.File;
import com.theokanning.openai.service.OpenAiService;
import dev.almeida.henrique.chatgptspringboot.exception.FileNotFoundException;
import dev.almeida.henrique.chatgptspringboot.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FileService {

    private final OpenAiService aiService = new OpenAiService(Constant.OPENAPI_TOKEN);

    public File uploadFile(String purpose, String filePath) {
        log.info("Upload file with name {}", filePath);
        return aiService.uploadFile(purpose, filePath);
    }

    public List<File> listFiles() {
        log.info("Return all files");
        return aiService.listFiles();
    }

    public File getFileById(String fileId) {
        try {
            log.info("Search file with ID {}", fileId);
            return aiService.retrieveFile(fileId);
        } catch (RuntimeException exception) {
            log.error("File not found by ID {}", fileId);
            throw new FileNotFoundException(fileId);
        }
    }
}
