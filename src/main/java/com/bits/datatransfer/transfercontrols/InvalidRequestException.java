package com.bits.datatransfer.transfercontrols;

public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException(String message) {
        super("Request Invalid. " + message);
    }

}
