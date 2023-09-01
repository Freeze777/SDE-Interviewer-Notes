package leetcode.concurrency;

import java.util.Arrays;
import java.util.concurrent.Semaphore;

/**
 * <a href="https://leetcode.com/problems/fizz-buzz-multithreaded/">1195. Fizz Buzz Multithreaded</a>
 */
public class FizzBuzz {
    final int n;
    final Semaphore fizz = new Semaphore(0);
    final Semaphore buzz = new Semaphore(0);
    final Semaphore fizzBuzz = new Semaphore(0);
    final Semaphore number = new Semaphore(1); //initially number should be printed

    FizzBuzz(int n) {
        this.n = n;
    }

    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int current = 1; current <= n; current++) {
            if (current % 3 == 0 && current % 5 != 0) {
                fizz.acquire();
                printFizz.run();
                releaseNextLock(current);
            }
        }
    }

    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int current = 1; current <= n; current++) {
            if (current % 3 != 0 && current % 5 == 0) {
                buzz.acquire();
                printBuzz.run();
                releaseNextLock(current);
            }
        }
    }

    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int current = 1; current <= n; current++) {
            if (current % 15 == 0) {
                fizzBuzz.acquire();
                printFizzBuzz.run();
                releaseNextLock(current);
            }
        }
    }

    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int current = 1; current <= n; current++) {
            if (current % 3 != 0 && current % 5 != 0) {
                number.acquire();
                printNumber.accept(current);
                releaseNextLock(current);
            }
        }
    }

    public void releaseNextLock(int current) {
        int next = current + 1;
        if (next % 15 == 0) fizzBuzz.release();
        else if (next % 5 == 0) buzz.release();
        else if (next % 3 == 0) fizz.release();
        else number.release();
    }

    public static void main(String[] args) throws InterruptedException {
        FizzBuzz fizzBuzz = new FizzBuzz(15);
        Thread fizz = new Thread(() -> {
            try {
                fizzBuzz.fizz(() -> System.out.println("fizz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread buzz = new Thread(() -> {
            try {
                fizzBuzz.buzz(() -> System.out.println("buzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread fizzbuzz = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(() -> System.out.println("fizzbuzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread number = new Thread(() -> {
            try {
                fizzBuzz.number(new IntConsumer());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        var threads = Arrays.asList(fizz, buzz, fizzbuzz, number);
        threads.forEach(Thread::start);
        for (Thread thread : threads) thread.join();
    }
}
