package com.soten.java8.changeofinterface;

/*
   Foo3 에도 Bar 애도 default 메서드인 printNameUpperCase 가 있다.
   이러한 경우에는 충돌이 일어나기 때문에 컴파일 에러가 발생하게 된다.
   그러하니 같은 네이밍을 갖은 default 메서드가 있다면 (1) 처럼 직접 오버라이딩 해야한다.
 */

import java.util.Locale;

public class DefaultFoo implements Foo3, Bar {

    private String name;

    public DefaultFoo(String name) {
        this.name = name;
    }

    @Override
    public void printName() {
        System.out.println(this.name);
    }

    @Override
    public void printNameUpperCase() {  // (1)
        System.out.println(this.name.toUpperCase(Locale.ROOT));
    }

    @Override
    public String getName() {
        return this.name;
    }

}
