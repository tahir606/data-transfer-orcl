package com.bits.datatransfer.transfercontrols;

public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException() {
        super("Request Invalid. Keys Not Found");
    }

}
