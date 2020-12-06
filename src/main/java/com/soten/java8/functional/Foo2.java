package com.soten.java8.functional;

import java.util.function.*;

/*
  함수형 인터페이스를 생성하지않고, 이미 구현되어 있는 인터페이스를 사용할 수 있다.
  T : 타입 (받는 값)
  R : 리턴 (내주는 값)
 */
public class Foo2 {

    public static void main(String[] args) {
        // Function<T, R>
        Function<Integer, Integer> plus10 = (i) -> i + 10;
        Function<Integer, Integer> multiply2 = (i) -> i * 2;

        // compose를 이용하면 역순으로 계산하게 된다.
        System.out.println(plus10.compose(multiply2).apply(5));
        // andThen을 이용하면 순차적으로 계산하게 된다.
        System.out.println(plus10.andThen(multiply2).apply(2));

        // Consumer<T> T 타입을 받아서 아무값도 리턴하지 않는 함수 인터페이스
        // void 타입이다. 주로 함수를 조합할 때 쓴다. ex) andThen
        Consumer<Integer> printNumber = System.out::println;
        printNumber.accept(10);

        //Supplier<T> T 타입의 값을 제공해 주는 함수 인터페이스스
        Supplier<Integer> get10 = () -> 10;
        System.out.println(get10.get());

        // Predicate<T> T 타입을 받아서 boolean을 리턴하는 함수 인터페이스
        Predicate<String> startsWithName = (s) -> s.startsWith("younho");
        System.out.println(startsWithName.test("younho is good")); // -> true
        System.out.println(startsWithName.test("Choi younho")); // -> false

        Predicate<Integer> isEven = i -> i % 2 == 0;
        System.out.println(isEven.test(10)); // -> true
        System.out.println(isEven.test(15)); // -> false

        // UnaryOperator<T> 는  T와 R이 같은 타입일 경우 사용
        UnaryOperator<Integer> plus5 = i -> i +5;
        System.out.println(plus5.apply(5));
    }

}
