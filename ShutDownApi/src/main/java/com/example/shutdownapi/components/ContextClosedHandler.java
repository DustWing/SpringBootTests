package com.example.shutdownapi.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContextClosedHandler implements ApplicationListener<ContextClosedEvent> {

    private final static Logger logger = LoggerFactory.getLogger(ContextClosedHandler.class);


    private final List<Thread> threads;


    public ContextClosedHandler(List<Thread> threads) {
        this.threads = threads;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        logger.info("ContextClosedHandler [onApplicationEvent]");
        threads.forEach(Thread::interrupt);
    }
}
