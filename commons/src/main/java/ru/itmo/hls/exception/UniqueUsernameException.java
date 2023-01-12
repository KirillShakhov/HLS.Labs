package ru.itmo.hls.exception;


public class UniqueUsernameException extends RuntimeException {

    public UniqueUsernameException(String username) {
        super("Username '" + username + "' is already in use!");
    }

}
