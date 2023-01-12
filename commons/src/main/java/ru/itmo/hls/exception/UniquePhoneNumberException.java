package ru.itmo.hls.exception;


public class UniquePhoneNumberException extends RuntimeException {

    public UniquePhoneNumberException(String phoneNumber) {
        super("Phone number " + phoneNumber + " is already in use");
    }

}
