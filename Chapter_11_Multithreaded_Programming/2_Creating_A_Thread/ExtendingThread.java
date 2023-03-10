/**
 * Another way to create a thread is to create a new class that extends Thread
 * The new class must override the run() method
 * 
 * The key point is really the overriding of the run method from the Runnable interface
 */

public class ExtendingThread {
    public static void main(String[] args) {
        //Create 5 timers, all counting down simultaneously
        //Due to the scheduling mechanism, child threads do not execute in chronological order
        Thread[] timers = new Thread[5];
        for (int i = 0; i < timers.length; i++) {
            timers[i] = new LittleTimerThread(String.format("Timer %d", i));
            timers[i].start();
        }
    }
}

class LittleTimerThread extends Thread{
    LittleTimerThread(String threadName) {
        super(threadName);
    }

    @Override
    public void run() {
        final int TIME = 5;
        try {
            for (int i = TIME; i > 0; i--) {
                System.out.printf("Child Thread [%s]: %d\n", Thread.currentThread().getName(), i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.printf("Child Thread [%s] interrupted\n", Thread.currentThread().getName());
        }

        System.out.printf("Exiting child thread [%s]\n", Thread.currentThread().getName());
    }
}