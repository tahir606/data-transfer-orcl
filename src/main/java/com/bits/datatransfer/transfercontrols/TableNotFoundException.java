package com.bits.datatransfer.transfercontrols;

public class TableNotFoundException extends RuntimeException {

    public TableNotFoundException() {
        super("Table Not Found");
    }

}
