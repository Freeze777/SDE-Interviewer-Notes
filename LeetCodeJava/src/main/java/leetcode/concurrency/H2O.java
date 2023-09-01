package leetcode.concurrency;

import java.util.concurrent.Semaphore;

/**
 * <a href="https://leetcode.com/problems/building-h2o/">1117. Building H2O</a>
 */
public class H2O {

    private final Semaphore oxygen = new Semaphore(0);
    private final Semaphore hydrogen = new Semaphore(2);

    public H2O() {
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hydrogen.acquire();
        releaseHydrogen.run();
        if (hydrogen.availablePermits() == 0) oxygen.release(1);
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oxygen.acquire();
        releaseOxygen.run();
        hydrogen.release(2);
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
        final char[] chars = input.toCharArray();
        for (char c : chars) {
            switch (c) {
                case 'H' -> new Thread(() -> {
                    try {
                        h2O.hydrogen(releaseHydrogen);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
                case 'O' -> new Thread(() -> {
                    try {
                        h2O.oxygen(releaseOxygen);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
        Thread.sleep(100);
    }
}
