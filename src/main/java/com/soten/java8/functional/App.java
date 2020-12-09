package com.soten.java8.functional;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/*
   객체를 생성할 때는 (1) 방식을 이용해야한다.
   (2) 처럼 객체를 생성하려고 한다면 반드시 (3) 처럼 .get()을 해줘야한다.
   (2) 는 Supplier 일 뿐이다. 해석하자면 Greeting::new;를 참고하고있는 Supplier 이다.

   그리고 객체를 생성함에 있어 (2) 와 (4) 는 각자 다른 생성자를 참조하고있다.
   (2) 는 입력값이 없는 디폴트 생성자를 참조하고, (4) 는 입력 값이 있는 생성자를 참조한다.
 */

public class App {

    public static void main(String[] args) {
        Greeting greeting = new Greeting(); // (1)

        Supplier<Greeting> newGreeting = Greeting::new; // (2)
        newGreeting.get(); // (3)

        UnaryOperator<String> hi = Greeting::hi; // == (s) -> "hi " + s; 스태틱 메서드 참조

        UnaryOperator<String> hello = greeting::hello; // non-static 메서드 참조

        System.out.println(hi.apply("younho"));

        Function<String, Greeting> nameGreeting = Greeting::new; // (4)

        Greeting younho = nameGreeting.apply("younho");
        System.out.println(younho.getName());

        String[] names = {"younho", "woorim", "harim"};
        Arrays.sort(names, String::compareToIgnoreCase);

        System.out.println(Arrays.toString(names));


    }
}
