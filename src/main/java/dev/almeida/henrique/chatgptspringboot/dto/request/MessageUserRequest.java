package dev.almeida.henrique.chatgptspringboot.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(name = "MessageUserRequest", description = "Message request properties")
public record MessageUserRequest(
        @Schema(description = "The ID of Thread", example = "thread_ZnlCzdXT4lqfr7FNik5Xxmdas")
        @NotBlank(message = "The thread id cannot be blank or empty")
        @Size(min = 10, message = "The thread id property cannot be less 10 characters")
        String threadId,
        @Schema(description = "The text of message", example = "What is ChatGPT?")
        @NotBlank(message = "The message cannot be blank or empty")
        @Size(min = 3, message = "The message property cannot be less 3 characters")
        String message,
        @Schema(description = "The ID of File", example = "file-Oz9K1tA3q5jA1n3ZdCuydsada", nullable = true)
        @Nullable
        String fileId
) {
}
