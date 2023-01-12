package ru.itmo.hls.lab1.authorization.exceptions;

public class PageNotFoundException extends RuntimeException {

    public PageNotFoundException(String message) {
        super(message);
    }
}