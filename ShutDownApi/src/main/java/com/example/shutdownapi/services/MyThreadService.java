package com.example.shutdownapi.services;

import com.example.shutdownapi.runnables.MyRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Testing how the server will shut down with a shutdown api when having threads running.
 * If we do not shut down the threads the application still stays open, even through the spring boot context is off.
 * So we use ContextClosedHandler to shut down these threads.
 * This also works with actuator/shutdown.
 *
 * Threads opened by ThreadPoolTaskExecutor shut down with contexts since they are managed by it.
 */
@Service
public class MyThreadService {
    private final static Logger logger = LoggerFactory.getLogger(MyThreadService.class);

    @Bean
    public List<Thread> threadStarter() {
        logger.info("MyService [start]");


        Thread thread = new Thread(new MyRunnable("thread"));
        thread.start();

        return List.of(thread);

    }


    @Bean
    public ThreadPoolTaskExecutor myExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);
        executor.initialize();
        return executor;
    }

    @Bean
    public String createTasks(ThreadPoolTaskExecutor myExecutor) {
        myExecutor.execute(new MyRunnable("executor1"));
        myExecutor.execute(new MyRunnable("executor2"));
        myExecutor.execute(new MyRunnable("executor3"));
        return "";
    }

    @Scheduled(fixedDelay = 1000)
    public void scheduler() {
        logger.info("MyService [scheduler]");
    }

}
