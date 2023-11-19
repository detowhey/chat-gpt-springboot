package dev.almeida.henrique.chatgptspringboot.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;

public record MessageUserRequest(
        @NotBlank(message = "The thread id cannot be blank or empty")
        @Min(value = 10, message = "The thread id property cannot be less 10 characters")
        String threadId,
        @NotBlank(message = "The message cannot be blank or empty")
        @Min(value = 3, message = "The message property cannot be less 3 characters")
        String message,
        @Null
        String fileId
) {
}
