package com.example.shutdownapi.runnables;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyRunnable implements Runnable {

    private final static Logger logger = LoggerFactory.getLogger(MyRunnable.class);

    private final String value;

    public MyRunnable(String value) {
        this.value = value;
    }


    @Override
    public void run() {
        while (true) {

            logger.info("MyRunnable is running\t" + value + "\t" + Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
