/**
 * The easiest way to create a thread is to create a class that implements the Runnable interface
 * To do so, a class need only implement a single method called run()
 * The run() method establishes the entry point for the new thread, and the new thread ends when run() returns
 * 
 */

public class ImplementingRunnable {
    public static void main(String[] args) {
        //Create 5 timers, all counting down simultaneously
        //Due to the scheduling mechanism, child threads do not execute in chronological order
        Thread[] timers = new Thread[5];
        for (int i = 0; i < timers.length; i++) {
            timers[i] = new Thread(new LittleTimerRunnable(), String.format("Timer %d", i));
            timers[i].start();
        }
    }
    
}

class LittleTimerRunnable implements Runnable{
    //entry point for the new thread
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