package leetcode.concurrency;

public class StaticObjectLock {
    // A single static lock object shared by all instances
    private static final Object func1Lock = new Object();

    // func1: Synchronizes on the STATIC lock
    public void func1() {
        synchronized (func1Lock) {
            // do something
        }
    }

    // func2: Independent. Does not need the static lock.
    public void func2() {
        // do something
    }
}
