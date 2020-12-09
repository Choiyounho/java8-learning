package com.soten.java8.changeofinterface;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class App2 {

    public static void main(String[] args) {
        Foo3 foo3 = new DefaultFoo("younho");
        foo3.printName();
        foo3.printNameUpperCase();

        Foo3.printAnyThing();

        List<String> names = new ArrayList<>();
        names.add("younho");
        names.add("hangyeol");
        names.add("woorim");
        names.add("harim");

        names.stream().map(String::toUpperCase)
                .filter(s -> s.startsWith("H"))
                .collect(Collectors.toSet());

        names.removeIf(s -> s.startsWith("h"));

        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        names.sort(compareToIgnoreCase); // 오름차순 정렬
        names.sort(compareToIgnoreCase.reversed()); // 내림차순 정렬

        // List 의 순회하는 메소드, 이 메소드는 요소들을 각각 순회하여 출력하는 메소드
        names.forEach(System.out::println);

        System.out.println("=======================");
        Spliterator<String> spliterator = names.spliterator(); // 홀수 번째 요소들 제거
        Spliterator<String> spliterator1 = spliterator.trySplit(); // 홀수 번쨰 제거 된 요소들
        while (spliterator.tryAdvance(System.out::println)) ;
        System.out.println("=======================");
        while (spliterator1.tryAdvance(System.out::println)) ;
    }

}
