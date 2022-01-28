package com.example.shutdownapi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShutDownController implements ApplicationContextAware {

    private final  static Logger logger = LoggerFactory.getLogger(ShutDownController.class);


    private ApplicationContext applicationContext;


    @PostMapping("/shutdownContext")
    public void shutdownContext() {

        logger.info("Shutting down from API");
        ((ConfigurableApplicationContext) applicationContext).close();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
