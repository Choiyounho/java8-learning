package com.soten.java8.changeofinterface;

public class App2 {

    public static void main(String[] args) {
        Foo3 foo3 = new DefaultFoo("younho");
        foo3.printName();
        foo3.printNameUpperCase();

        Foo3.printAnyThing();

    }
}
