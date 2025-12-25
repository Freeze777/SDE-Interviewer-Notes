package leetcode.concurrency;

public class Deadlock {
    public static void main(String[] args) {
        final String resource1 = "Resource #1";
        final String resource2 = "Resource #2";

        // Thread 1: Locks resource1 then tries for resource2
        Thread t1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1: Locked " + resource1);
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }
                System.out.println("Thread 1: Waiting for " + resource2 + "...");
                synchronized (resource2) { // deadlock happens here
                    System.out.println("Thread 1: Locked " + resource2);
                }
                System.out.println("Thread 1: Exited.");
            }
        });

        // Thread 2: Locks resource2 then tries for resource1
        Thread t2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println("Thread 2: Locked " + resource2);
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }
                System.out.println("Thread 2: Waiting for " + resource1 + "...");
                synchronized (resource1) { // deadlock happens here
                    System.out.println("Thread 2: Locked " + resource1);
                }
                System.out.println("Thread 2: Exited.");
            }
        });

        t1.start();
        t2.start();
    }
}