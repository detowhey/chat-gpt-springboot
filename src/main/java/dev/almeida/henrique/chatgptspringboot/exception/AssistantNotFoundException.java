package dev.almeida.henrique.chatgptspringboot.exception;

public class AssistantNotFoundException extends NotFoundException {

    public AssistantNotFoundException(String id) {
        super("Assistant not found by Id " + id);
    }
}
