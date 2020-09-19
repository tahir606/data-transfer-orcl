package com.bits.datatransfer.transfercontrols;

import org.springframework.stereotype.Component;

@Component
public class ExportThread extends Thread {

    @Override
    public void run() {
        int counter = 0;
        while (true) {
            System.out.println("Thread: " + counter);
            counter++;
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
