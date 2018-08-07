package com.khlopotov.ai;


import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * https://www.codewars.com/kata/5519a584a73e70fa570005f5
 */
public class Primes {

    private static final Supplier<IntStream> stream = () -> IntStream.range(2, Integer.MAX_VALUE).filter(Primes::isPrime);

    private Primes() {
        //def ctor
    }

    public static IntStream stream() {
        return stream.get();
    }

    public static boolean isPrime(int num) {
        if (num > 2 && num % 2 == 0) {
            return false;
        }
        int top = (int) Math.sqrt(num) + 1;
        for (int i = 3; i < top; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

}