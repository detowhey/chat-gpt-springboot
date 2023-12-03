package dev.almeida.henrique.chatgptspringboot.exception;

public class FileNotFoundException extends NotFoundException {

    public FileNotFoundException(String id) {
        super("File not found by id" + id);
    }
}
