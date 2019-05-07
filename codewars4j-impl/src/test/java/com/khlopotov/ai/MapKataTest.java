package com.khlopotov.ai;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Function;

import static org.junit.Assert.assertArrayEquals;

public class MapKataTest {
    @Test
    public void basicTests() {
        testMap(null, __ -> false, null);

        testMap(new Node(1, new Node(2, new Node(3))), x -> x, new Node(1, new Node(2, new Node(3))));

        Function<Integer, Integer> f = x -> x * 2;
        testMap(new Node(1, new Node(2, new Node(3))), f, new Node(2, new Node(4, new Node(6))));
    }

    @Test
    public void moreTests() {
        Function<Integer, Integer> f1 = x -> x + 3;
        testMap(new Node(1, new Node(2, new Node(3))), f1, new Node(4, new Node(5, new Node(6))));

        Function<Integer, Boolean> f2 = x -> x > 1;
        testMap(new Node(1, new Node(2, new Node(3))), f2, new Node(false, new Node(true, new Node(true))));

        Function<String, String> f3 = x -> x + "x";
        testMap(new Node("aaa", new Node("b", new Node("abc"))), f3, new Node("aaax", new Node("bx", new Node("abcx"))));
    }

    @Test
    public void listIsNotModified() {
        Node<Integer> head = randomList(randomInt(10, 20));
        Object[] before = listToArray(head);
        Function<Integer, Integer> f = x -> x + 1;
        MapKata.map(head, f);
        Object[] after = listToArray(head);
        assertArrayEquals(before, after);
    }

    @Test
    public void randomTests() {
        for (int i = 0; i < 10; i++) {
            Node<Integer> head = randomList(randomInt(20, 100));
            Function<Integer, Integer> f = func(i);
            Node<Integer> expected = mapSol(head, f);
            testMap(head, f, expected);
        }
    }

    private static Function<Integer, Integer> func(int i) {
        int rand = randomInt(-10, 100);
        switch (i % 3) {
            case 0: return x -> x + rand;
            case 1: return x -> x * 2;
            case 2: return x -> x - rand;
            default: return __ -> 0;
        }
    }

    private static Node<Integer> randomList(int size) {
        return arrayToList(randomArray(size));
    }

    private static int[] randomArray(int size) {
        return new java.util.Random().ints(-10, 100).limit(size).toArray();
    }

    private static int randomInt(int min, int max) {
        return (int) Math.floor(min + Math.random() * max);
    }

    private static <T, R> void testMap(Node<T> head, Function<T, R> f, Node<R> expected) {
        assertArrayEquals(listToArray(expected), listToArray(MapKata.map(head, f)));
    }

    private static Node<Integer> arrayToList(int[] array) {
        return array.length == 0 ? null : new Node<>(array[0], arrayToList(Arrays.copyOfRange(array, 1, array.length)));
    }

    private static <T> Object[] listToArray(Node<T> head) {
        Collection<T> values = new LinkedList<>();
        for (; head != null; head = head.next) values.add(head.data);
        return values.toArray();
    }

    private static <T, R> Node<R> mapSol(Node<T> head, Function<T, R> f) {
        return head == null ? null : new Node<>(f.apply(head.data), mapSol(head.next, f));
    }
}