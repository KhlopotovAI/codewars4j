package tests;

import com.khlopotov.ai.MaxContinuous;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class MaxContinuousTest {
    private static int randint(int a, int b) {
        return a + new Random().nextInt(b - a + 1);
    }

    @Test
    public void fixedTests() {
        doTest(new int[]{3, -4, 8, 7, -10, 19, -3}, 24);
        doTest(new int[]{2, -3, -3, 9, -29, 8, -9}, 9);
        doTest(new int[]{1, 2, 3, -8, 3, 3, 4, -2, 7, -2}, 15);
        doTest(new int[]{-8, 1, 7, -2, -3, 4, -2, 5}, 10);
        doTest(new int[]{7, -7, 8, -2, 3, -2, 1, -1}, 9);
        doTest(new int[]{-2, -1, 4, -2, 2, 3, -2}, 7);
        doTest(new int[]{32, -11, -56, 78, -8, 1, -2}, 78);
        doTest(new int[]{-10, 8, 3, -100, 23, 12, 56}, 91);
        doTest(new int[]{58, 10, -32, -22, 3, -4, 34}, 68);
        doTest(new int[]{7, -7, 8, -2, 3, -2, 1, -11 - 2}, 9);
        doTest(new int[]{3, 4, -3, 2, -9, -9, -4, -2, 32}, 32);
        doTest(new int[]{-1, -2, -3}, 0);
    }

    @Test
    public void randomTests() {
        for (int trial = 0; trial < 100; trial++) {
            int[] v = new int[randint(1, 30)];
            for (int i = 0; i < v.length; i++)
                v[i] = randint(-10, 10);
            doTest(v, solution(v));
        }
    }

    @Test
    public void moreRandomTests() {
        for (int trial = 0; trial < 1000; trial++) {
            int[] v = new int[10000];
            for (int i = 0; i < 10000; i++)
                v[i] = randint(-5000, 5000);
            doTest(v, solution(v));
        }
    }

    private int solution(final int[] arr) {
        int max_so_far = arr[0];
        int curr_max = arr[0];
        int cnt = (arr[0] < 0) ? 1 : 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < 0) cnt++;
            curr_max = Math.max(arr[i], curr_max + arr[i]);
            max_so_far = Math.max(max_so_far, curr_max);
        }
        return cnt == arr.length ? 0 : max_so_far;
    }

    private void doTest(final int[] numbers, final int expected) {
        assertEquals(expected, MaxContinuous.maxContiguousSum(numbers));
    }
}

