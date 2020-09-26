package com.bits.datatransfer.transfercontrols;

import org.springframework.stereotype.Component;

@Component
public class ExportThread extends Thread {

    private final ExportHandler exportHandler;

    public ExportThread(ExportHandler exportHandler) {
        this.exportHandler = exportHandler;
    }

    @Override
    public void run() {
        int counter = 0;
        while (true) {
            System.out.println("Thread: " + counter);
            exportHandler.exportData();
            counter++;
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
