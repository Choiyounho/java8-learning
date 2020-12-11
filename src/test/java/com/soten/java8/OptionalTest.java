package com.soten.java8;

import com.soten.java8.stream.OnlineClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalTest {

    private List<OnlineClass> springClasses;

    @BeforeEach
    void setUp() {
        initSpringClasses();
    }

    @Test
    @DisplayName("값이 있을 수도 없을 수도 있는 값은 Optional 로 표현")
    void test() {
        Optional<OnlineClass> optional = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();

        boolean present = optional.isPresent();
        System.out.println(present);

        optional.ifPresent(oc -> System.out.println(oc.getTitle()));

        // 값이 없으면 객체를 생성하라
        OnlineClass onlineClass = optional.orElseGet(this::createNewOnlineClass);
        System.out.println(onlineClass.getTitle());

        // 값이 없으면 오류
        OnlineClass onlineClass1 = optional.orElseThrow(IllegalStateException::new);
    }

    private OnlineClass createNewOnlineClass() {
        System.out.println("creating new online class");
        return new OnlineClass(10, "New Class", false);
    }

    private void initSpringClasses() {
        springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(5, "rest api development", false));
    }

}
