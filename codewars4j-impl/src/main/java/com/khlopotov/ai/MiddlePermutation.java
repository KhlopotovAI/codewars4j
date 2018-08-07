package com.khlopotov.ai;

import java.util.Arrays;

/**
 * https://www.codewars.com/kata/58ad317d1541651a740000c5
 */
public class MiddlePermutation {

    private MiddlePermutation() {
        //def ctor
    }

    public static String findMidPerm(String str) {
        if (str.length() % 2 == 0) {
            char[] word = str.toCharArray();
            Arrays.sort(word);
            return middlePermutation(word);
        } else {
            char[] word = str.toCharArray();
            Arrays.sort(word);
            int mid = word.length / 2;
            char[] even = new char[word.length - 1];
            System.arraycopy(word, 0, even, 0, mid);
            System.arraycopy(word, mid + 1, even, mid, even.length - mid);
            return word[mid] + middlePermutation(even);
        }
    }

    private static String middlePermutation(char[] word) {
        char midSymbol = word[word.length / 2 - 1];
        String result = String.valueOf(midSymbol);
        for (int i = word.length - 1; i >= 0; i--) {
            if (midSymbol != word[i]) result += word[i];
        }
        return result;
    }
}
