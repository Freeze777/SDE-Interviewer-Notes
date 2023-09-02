package leetcode.concurrency;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * <a href="https://leetcode.com/problems/print-in-order/">1114. Print in Order</a>
 */
public class PrintInOrder {

    private final Semaphore first = new Semaphore(1);
    private final Semaphore second = new Semaphore(0);
    private final Semaphore third = new Semaphore(0);

    public PrintInOrder() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        first.acquire();
        printFirst.run();
        second.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        second.acquire();
        printSecond.run();
        third.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        third.acquire();
        printThird.run();
        first.release();
    }

    public static void main(String[] args) throws InterruptedException {
        PrintInOrder printInOrder = new PrintInOrder();
        List<Thread> threadsBatch1 = execute(printInOrder);
        List<Thread> threadsBatch2 = execute(printInOrder);
        for (Thread thread : threadsBatch1) thread.join();
        for (Thread thread : threadsBatch2) thread.join();
    }

    private static List<Thread> execute(PrintInOrder printInOrder) {
        Thread first = new Thread(() -> {
            try {
                printInOrder.first(() -> System.out.print("first"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread second = new Thread(() -> {
            try {
                printInOrder.second(() -> System.out.print("second"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread third = new Thread(() -> {
            try {
                printInOrder.third(() -> System.out.print("third"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        var threads = Arrays.asList(first, second, third);
        Collections.shuffle(threads);
        threads.forEach(Thread::start);
        return threads;
    }
}
