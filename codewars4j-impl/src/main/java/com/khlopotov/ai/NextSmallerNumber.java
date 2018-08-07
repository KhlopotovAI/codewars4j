package com.khlopotov.ai;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://www.codewars.com/kata/5659c6d896bc135c4c00021e
 */
public class NextSmallerNumber {

    public static long nextSmaller(long n) {
        if (n <= 11) return -1;

        List<Integer> digits = getDigits(n);

        nextPerm(digits);

        long next = digits.stream()
                .map(String::valueOf)
                .reduce((s1, s2) -> s1 + s2)
                .map(Long::valueOf)
                .orElseThrow(IllegalStateException::new);

        if (next == n) return -1;
        else return next;
    }

    private static List<Integer> getDigits(long digit) {
        return String.valueOf(digit).chars().map(Character::getNumericValue).boxed().collect(Collectors.toList());
    }

    private static void nextPerm(List<Integer> a) {
        int i = a.size() - 2;
        while (i >= 0 && a.get(i) <= a.get(i + 1))
            i--;

        if (i < 0)
            return;

        int j = a.size() - 1;
        while (a.get(i) <= a.get(j))
            j--;

        Collections.swap(a, i, j);
        if (a.get(0) == 0) { //oops
            Collections.swap(a, i, j);
            return;
        }
        Collections.reverse(a.subList(i + 1, a.size()));
    }
}
