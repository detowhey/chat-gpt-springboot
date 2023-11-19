package dev.almeida.henrique.chatgptspringboot.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MessageUserRequest(
        @NotBlank(message = "The thread id cannot be blank or empty")
        @Size(min = 10, message = "The thread id property cannot be less 10 characters")
        String threadId,
        @NotBlank(message = "The message cannot be blank or empty")
        @Size(min = 3, message = "The message property cannot be less 3 characters")
        String message,
        @Nullable
        String fileId
) {
}
