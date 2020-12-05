package com.soten.java8.functional;
/*
  함수형 인터페이스일 경우 아래 처럼 람다로 바로 구현할 수 있다.
  변수가 (1)번이나 (2)번에 있다면 순수 함수가 아니다.
  순수 함수란 (3)번 자리에 변수가 있고, 그 값을 참조할 때만 순수함수라고 한다.
  그리고 만약 함수가 특정 변수를 사용한다면 그 변수는 final이라는 가정하에 사용하게된다.
  아래 구현 처럼 구현하고 있다면 순수함수는 아니며, (4)를 사용할 경우 컴파일 에러가 난다.
 */

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
}
