package Threading;

public class AlternateThreading {
    // Define a class that implements the Runnable interface for threads that should execute
// in a strict alternating sequence.
    public static class AlternatingThread implements Runnable {
        // A shared lock object for synchronizing access between threads.
        private static final Object lock = new Object();
        // A shared boolean flag to determine whose turn it is to execute.
        private static boolean turn = true; // Initially set to true, indicating Thread 1's turn.

        // threadName for identification, myTurn indicates if it's this thread's turn to run.
        private final String threadName;
        private final boolean myTurn;

        // Constructor to initialize the AlternatingThread with a name and its turn flag.
        public AlternatingThread(String name, boolean myTurn) {
            this.threadName = name;
            this.myTurn = myTurn;
        }

        // The run method contains the code that the thread will execute when started.
        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                // Synchronize on the shared lock to ensure exclusive access.
                synchronized (lock) {
                    // Wait if it's not this thread's turn to run.
                    while (turn != myTurn) {
                        try {
                            // Wait releases the lock and waits until notified.
                            lock.wait();
                        } catch (InterruptedException e) {
                            // If the thread is interrupted while waiting, exit the method.
                            Thread.currentThread().interrupt();
                            System.out.println(threadName + " interrupted.");
                            return;
                        }
                    }
                    // Once it's this thread's turn, print a message.
                    System.out.println(threadName + ": " + i);
                    // Toggle the turn flag to switch turns between threads.
                    turn = !turn;
                    // Notify all waiting threads, potentially waking up the other thread.
                    lock.notifyAll();
                }
            }
            // Print a message when the thread has completed its execution sequence.
            System.out.println(threadName + " exiting.");
        }
    }


    public static void main(String[] args) throws InterruptedException {
        System.out.println("Simple Threading: ");
        SimpleThreading.SimpleThread t1 = new SimpleThreading.SimpleThread("Thread 1");
        SimpleThreading.SimpleThread t2 = new SimpleThreading.SimpleThread("Thread 2");
        t1.start();
        t2.start();

        // Wait for t1 and t2 to finish
        t1.join();
        t2.join();

        System.out.println();
        System.out.println("NEXT Alternate Threading:");

        // Now, start t3 and t4, which will execute in a synchronized, alternating sequence
        Thread t3 = new Thread(new AlternatingThread("Thread 3", true));
        Thread t4 = new Thread(new AlternatingThread("Thread 4", false));
        t3.start();
        t4.start();
    }
}
