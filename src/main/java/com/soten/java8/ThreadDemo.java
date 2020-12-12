package com.soten.java8;

public class ThreadDemo {
    /*
      스레드 구현 방법 (1) 은 상속받아 구현하는 것. 불편하다.
      그 외에 람다로 구현할 수도 있다.
     */

    public static void main(String[] args) throws InterruptedException {
//        MyThread myThread = new MyThread();
//        myThread.start();

        Thread thread = new Thread(() -> { // 1초 후에 출력하는 부분
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread : " + Thread.currentThread().getName());
        });

        Thread thread1 = new Thread(() -> { // 인터럽트가 되면 종료
            while (true) {
                System.out.println("Thread : " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    System.out.println("interrupt !!");
                    return;
                }
            }
        });

        thread.start();
        System.out.println("Hello : " + Thread.currentThread().getName());
        thread1.start();
        Thread.sleep(5000L);
        thread1.interrupt(); // 에러 발생 했을 때, 종료시키거나 하던 일을 계속 하는 메서드
//        thread1.join(); 스레드 대기 ex) thread 가 종료되고 나서 실행한다는 메서드
    }

//    static class MyThread extends Thread {     (1)
//        @Override
//        public void run() {
//            System.out.println("Thread : " + Thread.currentThread().getName());
//        }
//    }
}
