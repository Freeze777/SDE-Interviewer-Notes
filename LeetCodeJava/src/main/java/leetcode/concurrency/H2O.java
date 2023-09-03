package leetcode.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * <a href="https://leetcode.com/problems/building-h2o/">1117. Building H2O</a>
 */
public class H2O {

    private final CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
    private final Semaphore oxygen = new Semaphore(1);
    private final Semaphore hydrogen = new Semaphore(2);

    public H2O() {
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hydrogen.acquire();
        awaitBarrier();
        releaseHydrogen.run();
        hydrogen.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oxygen.acquire();
        awaitBarrier();
        releaseOxygen.run();
        oxygen.release();

    }

    private void awaitBarrier() {
        try {
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final H2O h2O = new H2O();
        final Runnable releaseHydrogen = () -> System.out.print("H");
        final Runnable releaseOxygen = () -> System.out.print("O");
        waterGenerator(h2O, releaseHydrogen, releaseOxygen, "OOHHHHOOHHHHOOHHHH");
        System.out.println();
        waterGenerator(h2O, releaseHydrogen, releaseOxygen, "HHHHOOHHHHOOHHHHOO");
        System.out.println();
        waterGenerator(h2O, releaseHydrogen, releaseOxygen, "HHHHHHHHHHHHHHHOHOOOHOHHHOHOOHOHHHOOOOOHHHOOOHOOHHOHHHHHHHHH");
    }

    private static void waterGenerator(H2O h2O, Runnable releaseHydrogen, Runnable releaseOxygen, String input) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for (char c : input.toCharArray()) {
            switch (c) {
                case 'H' -> threads.add(new Thread(() -> {
                    try {
                        h2O.hydrogen(releaseHydrogen);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }));
                case 'O' -> threads.add(new Thread(() -> {
                    try {
                        h2O.oxygen(releaseOxygen);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }));
            }
        }
        threads.forEach(Thread::start);
        for (Thread thread : threads) thread.join();
    }
}
