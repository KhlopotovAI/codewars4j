package com.khlopotov.ai;

import java.util.concurrent.*;

/**
 * https://www.codewars.com/kata/549e70e994e517ed8b00043e
 */
public class ThreadedCounting {

    private static CyclicBarrier barrier = new CyclicBarrier(3);

    private ThreadedCounting(){
        //def ctor
    }

    public static void countInThreads(Counter counter) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            executorService.execute(() -> {
                for (int j = 1; j <= 100; j++) {
                    try {
                        if (j % 3 == finalI) counter.count(j);
                        barrier.await();
                    } catch (InterruptedException | BrokenBarrierException ignored){}
                }
            });
        }
        try {
            executorService.shutdown();
            executorService.awaitTermination(100, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}

//mock
class Counter{
    public void count(int i){};
}

