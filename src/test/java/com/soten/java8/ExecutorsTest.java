package com.soten.java8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorsTest {

    @Test
    @DisplayName("싱글 스레드")
    void singleTest() {
        ExecutorService executorService = Executors.newSingleThreadExecutor(); // 스레드를 하나만 쓰는 executor
        executorService.submit(() -> System.out.println("Thread " + Thread.currentThread().getName()));

        executorService.shutdown(); // 현재 작업 중인 작업은 다 끝마치고 종료
        executorService.shutdownNow(); // 이 메서드를 쓰면 즉시 종료이다.
    }

    @Test
    @DisplayName("pool 스레드")
    void poolTest() {
        ExecutorService executorService1 = Executors.newFixedThreadPool(2);
        executorService1.submit(getRunnable("Hello"));
        executorService1.submit(getRunnable("My"));
        executorService1.submit(getRunnable("Name"));
        executorService1.submit(getRunnable("Is"));
        executorService1.submit(getRunnable("Younho"));

        executorService1.shutdown();
    }

    private static Runnable getRunnable(String message) {
        System.out.println(message + Thread.currentThread().getName());
        return () -> System.out.println(message + Thread.currentThread().getName());
    }


}
