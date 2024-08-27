package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class SharedResource {
    private Queue<Integer> sharedBuffer;
    private int bufferSize;

    public SharedResource(int bufferSize) {
        sharedBuffer = new LinkedList<>();
        this.bufferSize = bufferSize;
    }

    public synchronized void produce(int item) throws Exception {
        while (sharedBuffer.size() == bufferSize) {
            System.out.println("Buffer is full. Producer is waiting...");
            wait();
        }
        sharedBuffer.add(item);
        System.out.println("Produced " + item);
        notify(); // Notify consumer that there is an item to consume
    }

    public synchronized int consume() throws Exception {
        while (sharedBuffer.isEmpty()) {
            System.out.println("Buffer is empty. Consumer is waiting...");
            wait();
        }
        int item = sharedBuffer.poll();
        System.out.println("Consumed " + item);
        notify(); // Notify producer that there is space in the buffer
        return item;
    }
}
