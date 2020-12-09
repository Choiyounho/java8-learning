package com.soten.java8.changeofinterface;

import java.util.Locale;

/*
   인터페이스에는 Object 에서 상속 받는 것들은 재정의 할 수 없다.
   (1) 번 처럼 추상메서드로는 가능하지만, (2) 번 처럼은 불가능하다.
 */

public interface Foo3 {

    void printName();

    /**
     * @implSpec
     * 이 구현체는 getName()으로 가져온 문자열을 대문자로 바꿔 출력한다.
     */
    default void printNameUpperCase() {
        System.out.println(getName().toUpperCase(Locale.ROOT));
    }

    String toString(); // (1)

//    default void toString() {} (2)

    static void printAnyThing() { // 유틸리티나 헬터 메서드를 제공하고 싶을 때 static 메서드 사용
        System.out.println("Foo3");
    }

    String getName();

}
