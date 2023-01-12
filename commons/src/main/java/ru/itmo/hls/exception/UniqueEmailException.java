package ru.itmo.hls.exception;


public class UniqueEmailException extends RuntimeException {

    public UniqueEmailException(String email) {
        super("Email '" + email + "' is already in use");
    }

}
