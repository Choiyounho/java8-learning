package com.soten.java8;

import com.sun.org.glassfish.gmbal.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CallableAndFutureTest {

    @Description("Future 는 외부에서 완료 시킬 수 없다. 취소하거나, get()에 타입아웃 설정은 할 수 있다." +
            "예외 처리용 API 제공 X , 여러 Future 조합 불가")

    @Test
    @DisplayName("Callable 과 Future")
    void callableAndFutureTest() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };

        Future<String> helloFuture = executorService.submit(hello);
        System.out.println(helloFuture.isDone()); // 끝나면 true 끝나지 않았으면 false
        System.out.println("Started!");

        helloFuture.get();
        helloFuture.cancel(true); // 인터럽트 하며 종료
        helloFuture.cancel(false); // 기다린다. cancel 을 하면 get 으로 가져올 수가 없다. 가져오게 된다면 에러

        System.out.println(helloFuture.isDone()); // cancel 이 되면 true 가 된다. (작업이 종료돼서 true 가 된 게 아님
        System.out.println("End!");
        executorService.shutdown();
    }

    @Test
    @DisplayName("한 번에 Callable 실행")
    void manyCallable() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };

        Callable<String> java = () -> {
            Thread.sleep(3000L);
            return "java";
        };

        Callable<String> younho = () -> {
            Thread.sleep(1000L);
            return "younho";
        };

        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(hello, java, younho));
        for (Future<String> future : futures) {
            System.out.println(future.get());
        }
    }

}
