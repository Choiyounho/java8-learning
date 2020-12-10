package com.soten.java8.stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class OnlineClassTest {

    private List<OnlineClass> springClasses;
    private List<OnlineClass> javaClasses;
    private List<List<OnlineClass>> studyEvents;

    @BeforeEach
    void setUp() {
        initSpringClasses();
        initJavaClasses();
        initStudyEvents();
    }

    @Test
    @DisplayName("spring 으로 시작하는 수업")
    void startsSpring() {
        springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .forEach(oc -> {
                    System.out.println(oc.getTitle());
                });
    }

    @Test
    @DisplayName("close 되지 않은 수업")
    void notClose() {
        springClasses.stream()
                .filter(sc -> !sc.isClosed())
                .forEach(sc -> System.out.println(sc.getTitle()));

        javaClasses.stream()
                .filter(jc -> !jc.isClosed())
                .forEach(jc -> System.out.println(jc.getTitle()));
    }

    @Test
    @DisplayName("수업 이름만 모아서 스트림 만들기")
    void onlySubject() {
        springClasses.stream()
                .map(sc -> sc.getTitle())
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("두 수업 목록에 들어있는 모든 수업 아이디 출력")
    void printId() {
        studyEvents.stream()
                .flatMap(Collection::stream)
                .forEach(se -> System.out.println(se.getId()));
    }

    @Test
    @DisplayName("10부터 1씩 증가 하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만")
    void test() {
        Stream.iterate(10, i -> i + 1)
                .skip(10)
                .limit(10)
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("자바 수업 중에 Test 가 들어있는 수업이 있는 지 확인")
    void inTest() {
        boolean test = javaClasses.stream()
                .anyMatch(jc -> jc.getTitle().contains("Test"));
        System.out.println(test);
    }

    @Test
    @DisplayName("스프링 수업 중에 제목에 spring 이 들어간 제목만 모아서 List 만들기")
    void intSpringList() {
        List<String> filerList = springClasses.stream()
                .filter(sc -> sc.getTitle().contains("spring"))
                .map(OnlineClass::getTitle)
                .collect(Collectors.toList());

        System.out.println(filerList);
    }

    private void initSpringClasses() {
        springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));
    }

    private void initJavaClasses() {
        javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "The Java, Test", true));
        javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", false));
    }

    private void initStudyEvents() {
        studyEvents = new ArrayList<>();
        studyEvents.add(springClasses);
        studyEvents.add(javaClasses);
    }
}
