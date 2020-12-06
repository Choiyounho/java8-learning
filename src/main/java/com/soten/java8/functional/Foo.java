package com.soten.java8.functional;
/*
  함수형 인터페이스일 경우 아래 처럼 람다로 바로 구현할 수 있다.
  변수가 (1)번이나 (2)번에 있다면 순수 함수가 아니다.
  순수 함수란 (3)번 자리에 변수가 있고, 그 값을 참조할 때만 순수함수라고 한다.
  그리고 만약 함수가 특정 변수를 사용한다면 그 변수는 final이라는 가정하에 사용하게된다.
  아래 구현 처럼 구현하고 있다면 순수함수는 아니며, (4)를 사용할 경우 컴파일 에러가 난다.
 */

import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class Foo {

    public static void main(String[] args) {
        int baseNumber = 10;  // (1)

        RunSomething runSomething = new RunSomething() {
            // (2) int baseNumber = 10;
            @Override
            public int doIt(int number) {
                // (3) int baseNumber = 10;
                return number + baseNumber;
            }
        };
        // (4) baseNumber++;
    }

    /*
      익명 클래스와 로컬클래스는 baseNumber를 참조할 수 있다.
      그리고 (1)과 (2) 처럼 같은 변수명을 가지고 있다면 자기 자신의 것을 사용할 수 있고,
      baseNumber2 처럼 참조만 할 수도 있다.
      이에 반해 람다에서는 같은 변수명을 재정의 할 수 없다.

      즉, 람다는 같은 스코프(공유 범위)이고, 로컬 클래스와 익명 클래스는 다른 스코프다.

      또한, Java 1.8 버전 부터는 변수 앞에 final을 선언하지 않아도 되는 경우가 있다. (사실상 final인 경우)


     */

    private void run() {
        final int baseNumber = 10; // final은 한번 선언해주면 값이 변하지 않아야함. (여기서는 생략 가능)
        int baseNumber2 = 20;

        // 로컬 클래스
        class LocalClass {
            void printBaseNumber() {
                int baseNumber = 15; // (1)
                System.out.println(baseNumber);
                System.out.println(baseNumber2);
            }
        }

        // 익명 클래스
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                int baseNumber = 15; // (2)
                System.out.println(baseNumber);
            }
        };

        // 람다
        IntConsumer printInt = (i) -> {
//            int baseNumber = 5;    <<< 사용 불가
            System.out.println(i + baseNumber);
        };

        printInt.accept(10);
     }

}
