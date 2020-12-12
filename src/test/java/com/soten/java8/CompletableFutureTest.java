package com.soten.java8;

import com.sun.org.glassfish.gmbal.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CompletableFutureTest {


    @Test
    @DisplayName("사용법")
    void useTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();

        future.complete("younho");

        System.out.println(future.get()); // get 으로 호출 하는 것은 필수

        CompletableFuture<String> future1 = CompletableFuture.completedFuture("harim");

        System.out.println(future1.get());
    }

    @Test
    @DisplayName("리턴 값이 없을 경우 runAsync")
    void noType() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> // 리턴 값이 없을 경우 runAsync
                System.out.println("Hello! " + Thread.currentThread().getName()));

        future.get();
    }

    @Test
    @DisplayName("리턴 값이 있는 경우 supplyAsync")
    void haveType() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = getCompletableFuture("Hello");

        future.get();
        System.out.println(future.get());
    }

    @Test
    @DisplayName("콜백 제공 하기 : thenApply(Function)결과 타입을 변경할 경우")
    void thenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = getCompletableFuture("Hello").thenApply(s -> {
            System.out.println(Thread.currentThread().getName());
            return s.toUpperCase();
        });

        System.out.println(future.get());
    }

    @Test
    @DisplayName("콜백 제공 하기 : thenAccept(Consumer) 리턴하는 타입이 없고, 간단하게 받아서 쓰기만 할때")
    @Description("Consumer 가 들어오며 리턴하는 타입이 없으므로 타입이 Void 다.")
    void thenAcceptTest() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = getCompletableFuture("Hello").thenAccept((s) -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(s.toUpperCase());
        });

        future.get();
    }

    @Test
    @DisplayName("콜백 제공 하기 : thenRun(Runnable) 구현되기만 하면 될 때")
    void thenRun() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = getCompletableFuture("Hello").thenRun(() ->
                System.out.println(Thread.currentThread().getName()));

        future.get();
    }

    @Test
    @DisplayName("콜백 을 또 다른 스레드에서 실행할 때 Executor 을 사용한 CompletableFuture")
    void executorCompletableFuture() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }, executorService).thenRun(() -> { // 위의 스레드 풀과 다른 스레드 풀을 사용할 때는 thenRunAsync 같은 메서드를 사용
            System.out.println(Thread.currentThread().getName());
        });

        future.get();

        executorService.shutdown();
    }

    @Test
    @DisplayName("두 Future 간의 의존성이 있는 경우의 조합")
    void association() throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = getCompletableFuture("Hello");

        CompletableFuture<String> future = hello.thenCompose(CompletableFuture::completedFuture);

        System.out.println(future.get());
    }

    @Test
    @DisplayName("연관이 없는 Future 조합")
    void association2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = getCompletableFuture("Hello");

        CompletableFuture<String> world = getCompletableFuture("world");

        CompletableFuture<String> future = hello.thenCombine(world, (h, w) -> h + " " + w);
        System.out.println(future.get());
    }

    @Test
    @DisplayName("2개 이상일 때 모든 서브 테스크를 합쳐서 실행하는 방법")
    void allOf() throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = getCompletableFuture("Hello");

        CompletableFuture<String> world = getCompletableFuture("world");

        List<CompletableFuture<String>> futures = Arrays.asList(hello, world);

//        CompletableFuture<Void> future = CompletableFuture.allOf(hello, world)
//                .thenAccept(System.out::println); // 모든 테스크의 타입이 동일 하다는 보장이 없음
                                                    // 결과를 잘 가져오지 못할 수 있음
        CompletableFuture[] futuresArray = futures.toArray(new CompletableFuture[0]);

        CompletableFuture<List<Object>> results = CompletableFuture.allOf(futuresArray)
                .thenApply(v -> futures.stream()
                            .map(CompletableFuture::join)
                            .collect(Collectors.toList()));

        results.get().forEach(System.out::println);

        CompletableFuture<Void> future = CompletableFuture.anyOf(hello, world).thenAccept(System.out::println);
        future.get();
    }

    @Test
    @DisplayName("에러처리")
    @Description("어떤 비동기 처리에서 에러가 발생할 때")
    void exceptionTest() throws ExecutionException, InterruptedException {
        boolean throwError = true;

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            if (throwError) {
                throw new IllegalArgumentException();
            }

            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).exceptionally(ex -> {
            System.out.println(ex);
            return "Error!";
        });

        System.out.println(hello.get());

        // 정상적으로 종료된 경우나 오류 둘 다 일어날 때 모두 사용 가능
        CompletableFuture<String> hello1 = CompletableFuture.supplyAsync(() -> {
            if (throwError) {
                throw new IllegalArgumentException();
            }

            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).handle((result, ex) -> {
            if (ex != null) {
                System.out.println(ex);
                return "ERROR!";
            }
            return result;
        });

        System.out.println(hello1.get());
    }
    private static CompletableFuture<String> getCompletableFuture(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(message + Thread.currentThread().getName());
            return message + " CompletableFuture";
        });
    }

}
