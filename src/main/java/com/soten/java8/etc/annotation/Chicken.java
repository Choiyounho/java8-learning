package com.soten.java8.etc.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) // 어느 시점까지 어노테이션의 메모리를 가져갈 지 설정
@Target(ElementType.TYPE_USE) // 필드, 메소드, 클래스 ,파라미어 등 선언할 수 있는 타입을 설정하여 커스텀 어노테이션 생성
@Repeatable(ChickenContainer.class)
public @interface Chicken {

    String value();
}
