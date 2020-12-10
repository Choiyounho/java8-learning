package com.soten.java8.stream;

import com.sun.org.glassfish.gmbal.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class FooStreamTest {

    List<String> names;

    @BeforeEach
    void setUp() {
        names = new ArrayList<>();
        names.add("younho");
        names.add("hangyeol");
        names.add("woorim");
        names.add("harim");
    }

    @Test
    @DisplayName("중개형 오퍼레이션과 종료 오퍼레이션")
    @Description("리턴 값은 Stream 이다. " +
            "중간에 연산 해주는 역할만 하며 출력을 하려면 (1) 처럼 종료 오퍼레이션을 사용해야한다" +
            "(3) 은 종료 오퍼레이션이며 ")
    void stream() {
        Stream<String> stringStream = names.stream()
                .map(String::toUpperCase);

        System.out.println(stringStream); // 주소값 출력

        List<String> collect = stringStream.collect(Collectors.toList()); // (1)

        System.out.println(collect); // 출력

        System.out.println("===============");

        names.forEach(System.out::println); // (3)
    }

}
