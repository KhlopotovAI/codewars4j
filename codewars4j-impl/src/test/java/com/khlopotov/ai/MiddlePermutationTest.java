package com.khlopotov.ai;

import com.khlopotov.ai.MiddlePermutation;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MiddlePermutationTest {
    @Test
    public void basicTests() {
        assertEquals("bac", MiddlePermutation.findMidPerm("abc"));
        assertEquals("bdca", MiddlePermutation.findMidPerm("abcd"));
        assertEquals("cbxda", MiddlePermutation.findMidPerm("abcdx"));
        assertEquals("cxgdba", MiddlePermutation.findMidPerm("abcdxg"));
        assertEquals("dczxgba", MiddlePermutation.findMidPerm("abcdxgz"));
    }
}
