import java.util.*;
import java.util.concurrent.*;

//boundedbuffer class reps a buffer w/ fixed capacity
class BoundedBuffer {
    private final Queue<Integer> buffer = new LinkedList<>();
    private final int capacity;

    private final Semaphore empty; //semaphore tracks empty slots in buffer
    private final Semaphore full; //semaphore tracks filled slots
    private final Semaphore mutex; //semaphoreprovide mutual exclusion

    public BoundedBuffer(int capacity) {
        this.capacity = capacity;
        this.empty = new Semaphore(capacity);
        this.full = new Semaphore(0);
        this.mutex = new Semaphore(1);
    }

    //produce item + add to buffer
    public void produce(int item, int pid) throws InterruptedException {
        System.out.println("[Producer " + pid + "] Waiting for empty slot...");
        empty.acquire();

        System.out.println("[Producer " + pid + "] Waiting for mutex...");
        mutex.acquire();

        buffer.add(item);
        System.out.println("[Producer " + pid + "] Produced item: " + item);

        mutex.release();
        full.release();
    }

    //consume item from buffer
    public int consume(int pid) throws InterruptedException {
        System.out.println("[Consumer " + pid + "] Waiting for item...");
        full.acquire();

        System.out.println("[Consumer " + pid + "] Waiting for mutex...");
        mutex.acquire();

        int item = buffer.remove();
        System.out.println("[Consumer " + pid + "] Consumed item: " + item);

        mutex.release();
        empty.release();

        return item;
    }
}

//producer class reps producer thread
class Producer extends Thread {
    private final BoundedBuffer buffer;
    private final int pid;

    public Producer(BoundedBuffer buffer, int pid) {
        this.buffer = buffer;
        this.pid = pid;
    }

    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                buffer.produce(i, pid);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}

//consumer class reps consumer thread
class Consumer extends Thread {
    private final BoundedBuffer buffer;
    private final int pid;

    public Consumer(BoundedBuffer buffer, int pid) {
        this.buffer = buffer;
        this.pid = pid;
    }

    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                buffer.consume(pid);
                Thread.sleep(800);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}

//main class runs Producer-Consumer simulation
public class ProducerConsumer {
    public static void main(String[] args) {
        BoundedBuffer buffer = new BoundedBuffer(3);

        Producer p1 = new Producer(buffer, 1);
        Consumer c1 = new Consumer(buffer, 1);

        p1.start();
        c1.start();

        try {
            p1.join();
            c1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Producer-Consumer simulation completed.");
    }
}