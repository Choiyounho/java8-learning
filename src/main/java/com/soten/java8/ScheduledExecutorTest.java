package com.soten.java8;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ScheduledExecutorTest {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        // 3초 후 실행
        executorService.schedule(getRunnable("Hello"), 3, TimeUnit.SECONDS);

        // 1초 후 시작 2초에 한번씩 출력
        executorService.scheduleAtFixedRate(getRunnable("Hello"), 1, 2, TimeUnit.SECONDS);
    }

    private static Runnable getRunnable(String message) {
        System.out.println(message + Thread.currentThread().getName());
        return () -> System.out.println(message + Thread.currentThread().getName());
    }

}
