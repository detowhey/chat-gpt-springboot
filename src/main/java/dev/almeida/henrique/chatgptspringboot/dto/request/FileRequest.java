package dev.almeida.henrique.chatgptspringboot.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FileRequest(

        @NotBlank(message = "The purpose can't be empty")
        String purpose,
        @Size(min = 3, message = "The file path cannot by 3 characters less")
        @NotBlank(message = "The file path cannot be empty")
        String filePath
) {
}
