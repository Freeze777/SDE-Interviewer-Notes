package leetcode.concurrency;

import java.util.concurrent.Semaphore;

/**
 * <a href="https://leetcode.com/problems/print-foobar-alternately/">1115. Print FooBar Alternately</a>
 */
public class FooBar {
    private final int n;
    private final Semaphore foo = new Semaphore(1);
    private final Semaphore bar = new Semaphore(0);

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            foo.acquire();
            printFoo.run();
            bar.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            bar.acquire();
            printBar.run();
            foo.release();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FooBar fooBar = new FooBar(5);
        Thread foo = new Thread(() -> {
            try {
                fooBar.foo(() -> System.out.print("foo"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread bar = new Thread(() -> {
            try {
                fooBar.bar(() -> System.out.print("bar"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        foo.start();
        bar.start();

        foo.join();
        bar.join();
    }
}
