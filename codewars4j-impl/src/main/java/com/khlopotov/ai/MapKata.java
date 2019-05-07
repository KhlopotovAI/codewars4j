package com.khlopotov.ai;

import java.util.function.Function;

/**
 * https://www.codewars.com/kata/fun-with-lists-map/train/java
 */
public class MapKata {
    private MapKata() {/*nothing to do*/}

    public static <T, R> Node<R> map(Node<T> head, Function<T, R> f) {
        return head == null ? null : new Node<>(f.apply(head.data), map(head.next, f));
    }
}

class Node<T> {
    public T data;
    public Node<T> next;

    Node(T data, Node next) {
        this.data = data;
        this.next = next;
    }

    Node(T data) {
        this(data, null);
    }
}
