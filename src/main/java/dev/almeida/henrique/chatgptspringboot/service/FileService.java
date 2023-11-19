package dev.almeida.henrique.chatgptspringboot.service;

import com.theokanning.openai.file.File;
import com.theokanning.openai.service.OpenAiService;
import dev.almeida.henrique.chatgptspringboot.util.Constant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    private final OpenAiService aiService = new OpenAiService(Constant.OPENAPI_TOKEN);

    public File uploadFile(String purpose, String filePath) {
        return aiService.uploadFile(purpose, filePath);
    }

    public List<File> listFiles() {
        return aiService.listFiles();
    }

    public File getFileById(String fileId) {
        return aiService.retrieveFile(fileId);
    }
}
