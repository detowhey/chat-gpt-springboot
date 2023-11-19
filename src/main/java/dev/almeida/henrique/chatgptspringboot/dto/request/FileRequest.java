package dev.almeida.henrique.chatgptspringboot.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record FileRequest(

        @NotBlank(message = "The purpose can't be empty")
        String purpose,
        @Min(value = 3, message = "The file path cannot by 3 characters less")
        @NotBlank(message = "The file path cannot be empty")
        String filePath
) {
}
