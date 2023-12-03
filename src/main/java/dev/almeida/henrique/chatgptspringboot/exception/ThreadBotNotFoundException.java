package dev.almeida.henrique.chatgptspringboot.exception;

public class ThreadBotNotFoundException extends AssistantNotFoundException {

    public ThreadBotNotFoundException(String id) {
        super("Thread not found by id " + id);
    }
}
