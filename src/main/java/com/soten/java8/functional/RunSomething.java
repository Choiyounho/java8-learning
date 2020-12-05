package com.soten.java8.functional;
/*
  추상 메서드가 하나만 존재한다면 함수형 인터페이스이다.
  그러한 인터페이스에는 @FunctionalInterface 어노테이션을 붙인다.
 */

@FunctionalInterface
public interface RunSomething {

    int doIt(int number);

}
