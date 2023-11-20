import java.util.Queue;
import java.util.LinkedList;
import java.util.Random;

public class CarQueue {
    private Queue<Integer> queue = new LinkedList<>(); // A queue to hold the directions
    private Random random = new Random(); // Random object to generate random directions

    // Constructor of CarQueue
    public CarQueue() {
        // Pre-populate the queue with initial random directions
        for (int i = 0; i < 5; i++) {
            addToQueue();
        }
    }

    // Method to add random directions to the queue
    public synchronized void addToQueue() {
        // Runnable that adds random directions to the queue continuously
        class QueueRunnable implements Runnable {
            public void run() {
                try {
                    while (true) {
                        // Adding a random direction (0 to 3) to the queue
                        queue.add(random.nextInt(4)); // 0 (up), 1 (down), 2 (right), 3 (left)
                        Thread.sleep(1000); // Sleep for 1 second before adding the next direction
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace(); // Print stack trace in case of an interruption
                }
            }
        }
        // Start a new thread with the QueueRunnable
        new Thread(new QueueRunnable()).start();
    }

    // Method to remove and return the next direction from the queue
    public synchronized int deleteQueue() {
        // Remove the next direction from the queue; if queue is empty, default to 0 (up)
        return queue.poll() != null ? queue.poll() : 0; 
    }
}