package Threading;

public class SimpleThreading {
// Define a SimpleThread class that extends the Thread class.
// This class is designed for threads
// that execute without synchronization, allowing their operations to interleave randomly.
    static class SimpleThread extends Thread {
        // threadName stores the name of the thread for identification purposes.
        private final String threadName;

        // Constructor to initialize the SimpleThread with a name.
        SimpleThread(String name) {
            this.threadName = name;
        }

        // The run method contains the code that the thread will execute when started.
        public void run() {
            // Loop to print a message five times.
            for (int i = 1; i <= 5; i++) {
                System.out.println(threadName + ": " + i);
                // Simulate some processing time by putting the thread to sleep for 100 milliseconds.
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // Handle the case where the thread's sleep is interrupted.
                    System.out.println(threadName + " interrupted.");
                }
            }
            // Print a message when the thread has finished its execution.
            System.out.println(threadName + " exiting.");
        }
    }


    public static void main(String[] args) throws InterruptedException {
        System.out.println("Simple Threading: ");
        SimpleThread t1 = new SimpleThread("Thread 1");
        SimpleThread t2 = new SimpleThread("Thread 2");
        t1.start();
        t2.start();

        // Wait for t1 and t2 to finish
        t1.join();
        t2.join();

        System.out.println();
        System.out.println("NEXT Alternate Threading:");

        // Now, start t3 and t4, which will execute in a synchronized, alternating sequence
        Thread t3 = new Thread(new AlternateThreading.AlternatingThread("Thread 3", true));
        Thread t4 = new Thread(new AlternateThreading.AlternatingThread("Thread 4", false));
        t3.start();
        t4.start();
    }

}
