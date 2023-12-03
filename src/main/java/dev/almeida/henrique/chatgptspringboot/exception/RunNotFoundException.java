package dev.almeida.henrique.chatgptspringboot.exception;

public class RunNotFoundException extends NotFoundException {

    public RunNotFoundException(String id) {
        super("Run not found by id " + id);
    }
}
