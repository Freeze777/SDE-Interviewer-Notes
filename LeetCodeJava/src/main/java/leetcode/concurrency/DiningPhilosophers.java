package leetcode.concurrency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <a href="https://leetcode.com/problems/the-dining-philosophers/">1226. The Dining Philosophers</a>
 */
public class DiningPhilosophers {
    private final ReentrantLock[] forks;

    public DiningPhilosophers() {
        forks = new ReentrantLock[5];
        Arrays.fill(forks, new ReentrantLock());
    }

    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        var forksRequired = new ArrayList<>(List.of(philosopher, (philosopher + 1) % 5));
        forksRequired.sort(Integer::compareTo);
        var firstFork = forksRequired.get(0);
        var secondFork = forksRequired.get(1);
        System.out.println(Thread.currentThread().getName() + " wants to eat");
        while (true) { //philosopher should eat and think alternatively.
            if (forks[firstFork].tryLock(1, TimeUnit.MILLISECONDS)) {
                if (forks[secondFork].tryLock(1, TimeUnit.MILLISECONDS)) { //wait is required to avoid deadlock
                    pickLeftFork.run();
                    pickRightFork.run();
                    eat.run();
                    putRightFork.run();
                    putLeftFork.run();
                    System.out.println(Thread.currentThread().getName() + " finished eating");
                    forks[secondFork].unlock();
                    forks[firstFork].unlock();
                    break;
                } else {
                    forks[firstFork].unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        final DiningPhilosophers diningPhilosophers = new DiningPhilosophers();
        final Runnable pickLeftFork = () -> System.out.println(Thread.currentThread().getName() + ": pickLeftFork");
        final Runnable pickRightFork = () -> System.out.println(Thread.currentThread().getName() + ": pickRightFork");
        final Runnable think = () -> System.out.println(Thread.currentThread().getName() + ": think");
        final Runnable eat = () -> System.out.println(Thread.currentThread().getName() + ": eat");
        final Runnable putLeftFork = () -> System.out.println(Thread.currentThread().getName() + ": putLeftFork");
        final Runnable putRightFork = () -> System.out.println(Thread.currentThread().getName() + ": putRightFork");

        final int numPhilosophers = 5;
        final int numTimesEat = 15;
        for (int philosopher = 0; philosopher < numPhilosophers; philosopher++) {
            final int finalPhilosopher = philosopher;
            Thread t = new Thread(() -> {
                for (int eatIdx = 0; eatIdx < numTimesEat; eatIdx++) {
                    try {
                        think.run();
                        diningPhilosophers.wantsToEat(finalPhilosopher, pickLeftFork, pickRightFork, eat, putLeftFork, putRightFork);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.setName("Philosopher-" + philosopher);
            t.start();
        }
    }
}

