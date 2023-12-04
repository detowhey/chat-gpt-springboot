package dev.almeida.henrique.chatgptspringboot.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(name = "FileRequest", description = "File request properties")
public record FileRequest(
        @Schema(description = "The purpose of file", example = "fine-tune")
        @NotBlank(message = "The purpose can't be empty")
        String purpose,
        @Schema(description = "The path of file", example = "path/file/file.java")
        @Size(min = 3, message = "The file path cannot by 3 characters less")
        @NotBlank(message = "The file path cannot be empty")
        String filePath
) {
}
