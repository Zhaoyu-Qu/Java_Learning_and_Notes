/**
 * The thread that calls the join() method will wait until the specified thread joins it.
 * When the join() method is invoked, the calling thread enters the WAITING state.
 * Once the specified thread is finished, the calling thread then enters the RUNNABLE state and waits for its turn.
 * Incidentally, the isAlive() method can be used to detect if the specified thread is still running.
 */

public class JoiningThreads {
    public static void main(String[] args) {
        //create child threads
        Thread[] timers = new Thread[3];
        for (int i = 0; i < timers.length; i++) {
            timers[i] = new Thread(new LittleTimerRunnable(), Integer.toString(i));
        }

        //start child threads
        for (int i = 0; i < timers.length; i++) {
            timers[i].start();
        }

        //check if alive
        for (int i = 0; i < timers.length; i++) {
            System.out.printf("Timer [%s] is alive: %s\n", timers[i].getName(), timers[i].isAlive());
        }

        //wait for child threads to join
        System.out.println("Waiting for threads to finish.");
        try {
            for (int i = 0; i < timers.length; i++) {
                //Once the join() method has been invoked, the main thread will enter the WAITING state
                //No further instruction from the main thread will be executed until the specified thread is finished.
                //In case you wonder why thread 0 has indicated it's done but the main thread doesn't display the 
                //next "join() method invoked on timer[1/2]" immediately, it's because the main thread enters the RUNNABLE state
                //and has to wait for its turn to run.
                System.out.printf("join() method invoked on timer [%s]\n", timers[i].getName());
                timers[i].join();

            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        //check again if child threads are still alive. they shouldn't be.
        for (int i = 0; i < timers.length; i++) {
            System.out.printf("Timer [%s] is alive: %s\n", timers[i].getName(), timers[i].isAlive());
        }

        System.out.println("Main thread exiting.");

    }
}

class LittleTimerRunnable implements Runnable{
    @Override
    public void run() {
        final int TIME = 3;
        String threadName = Thread.currentThread().getName();
        try {
            for (int i = TIME; i > 0; i--) {
                System.out.printf("Child thread [%s]: %d\n", threadName, i);
                Thread.sleep(200);
            }
    } catch (InterruptedException e) {
        System.out.printf("Child thread [%s] interrupted.\n", threadName);
    }
        System.out.printf("Child thread [%s] finished.\n", threadName);
    }
}
