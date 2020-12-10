package com.soten.java8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FooStream {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("younho");
        names.add("hangyeol");
        names.add("woorim");
        names.add("harim");

        /*
          이 로직을 사용할 경우 원본 names 의 요소를 가지고 변경하는 것이 아니라 요소를 추출해서 따로 작업한다.
          중개형 오퍼레이터이다.
         */
        Stream<String> stringStream = names.stream().map(String::toUpperCase);

        names.stream().map((s -> {
            System.out.println(s);
            return s.toUpperCase();
        }));

        System.out.println("===============");

        names.forEach(System.out::println);


    }
}
