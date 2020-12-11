package com.soten.java8.functional;

public class Greeting {

    private String name;

    public Greeting() {

    }

    public Greeting(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String hello(String name) {
        return "Hello " + name;
    }

    public static String hi(String name) {
        return "Hi " + name;
    }


    /**
     *
     *
     * @param number1 첫번째 매개변수
     * @param number2 두번째 매개변수
     * @return number1과 number2를 더한 합
     */
    private int sum(int number1, int number2) {
        return number1 + number2;
    }
}
