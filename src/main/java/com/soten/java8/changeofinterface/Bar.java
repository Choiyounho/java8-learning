package com.soten.java8.changeofinterface;

public interface Bar {

    default void printNameUpperCase() {
        System.out.println("BAR");
    }

}
