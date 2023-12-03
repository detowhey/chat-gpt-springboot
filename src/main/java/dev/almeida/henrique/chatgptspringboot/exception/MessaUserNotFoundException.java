package dev.almeida.henrique.chatgptspringboot.exception;

public class MessaUserNotFoundException extends NotFoundException {

    public MessaUserNotFoundException(String id) {
        super("Message not found by id " + id);
    }
}
