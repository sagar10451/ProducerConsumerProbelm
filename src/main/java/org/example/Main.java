package org.example;

public class Main {
    public static void main(String[] args) {
        SharedResource sharedBuffer = new SharedResource(3);

        // Creating producer thread using lambda expression
        Thread producerThread = new Thread(() -> {
            try {
                for (int i = 1; i <= 6; i++) {
                    sharedBuffer.produce(i);
                    // Optional sleep to simulate time taken for producing
                    Thread.sleep(100);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Creating consumer thread using lambda expression
        Thread consumerThread = new Thread(() -> {
            try {
                for (int i = 1; i <= 6; i++) {
                    sharedBuffer.consume();
                    // Optional sleep to simulate time taken for consuming
                    Thread.sleep(150);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Start both threads
        producerThread.start();
        consumerThread.start();

        // Wait for both threads to complete
        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Producer and Consumer have finished execution.");
    }
}
