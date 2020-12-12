package com.soten.java8.etc.annotation;

import java.util.Arrays;

@Chicken("양념")
@Chicken("마늘간장")
public class ChickenRestaurant {

    public static void main(String[] args) {
        Chicken[] chickens = ChickenRestaurant.class.getAnnotationsByType(Chicken.class);
        Arrays.stream(chickens).forEach(c -> {
            System.out.println(c.value());
        });

    }

}
