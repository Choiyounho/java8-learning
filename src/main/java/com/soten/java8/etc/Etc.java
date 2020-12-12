package com.soten.java8.etc;

import java.util.Arrays;

@Chicken("양념")
@Chicken("마늘간장")
public class Etc {

    public static void main(String[] args) {
        Chicken[] chickens = Etc.class.getAnnotationsByType(Chicken.class);
        Arrays.stream(chickens).forEach(c -> {
            System.out.println(c.value());
        });

    }

}
