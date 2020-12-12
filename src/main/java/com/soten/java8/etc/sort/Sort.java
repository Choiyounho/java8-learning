package com.soten.java8.etc.sort;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Sort {

    public static void main(String[] args) {
        int size = 1500;
        int[] numbers = new int[size];
        Random random = new Random();

        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
        long start = System.nanoTime();
        Arrays.sort(numbers); // 스레드 하나를 사용 Single Thread
        System.out.println("serial sorting took " + (System.nanoTime() - start));

        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
        start = System.nanoTime();
        Arrays.parallelSort(numbers);
        System.out.println("parallel sorting took " + (System.nanoTime() - start));
    }

}
