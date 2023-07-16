package PhilosopherEat;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Philosopher implements Runnable{

    // Declare tableware on left and right sides
    private Object left, right;
    private int philosopherID;
    private Lock leftLock = new ReentrantLock();
    private Lock rightLock = new ReentrantLock();


    // Constructor
    public Philosopher(Object left, Object right, int philosopherID) {
        this.left = left;
        this.right = right;
        this.philosopherID = philosopherID;
    }

    // Actions
    public boolean pickUpTableware() throws InterruptedException {

        if (leftLock.tryLock()) {
            System.out.println("The philosopher with ID: " + (philosopherID + 1) + " picked up the tableware on the left.");
            if (rightLock.tryLock()) {
                System.out.println("The philosopher with ID: " + (philosopherID + 1) + " picked up the tableware on the right.");
                return true;
            } else {
                leftLock.unlock();
                System.out.println("The philosopher with ID: " + (philosopherID + 1) + " put down the tableware on the left.");
                System.out.println("The philosopher with ID: " + (philosopherID + 1) + " is thinking.");
            } // end if
        }
        return false;
    }

    public void putDownTableware() throws InterruptedException {
        leftLock.unlock();
        System.out.println("The philosopher with ID: " + (philosopherID + 1) + " put down the tableware on the left.");
        rightLock.unlock();
        System.out.println("The philosopher with ID: " + (philosopherID + 1) + " put down the tableware on the right.");
        System.out.println("The philosopher with ID: " + (philosopherID + 1) + " is thinking.");
        Thread.sleep((int)(Math.random()*2000));
    }

    public void enjoyFood() {
        System.out.println("The philosopher with ID: " + (philosopherID + 1) + " is enjoying meal.");
    }

    public void thinking() throws InterruptedException {
        System.out.println("The philosopher with ID: " + (philosopherID + 1) + " is thinking.");
        Thread.sleep((int)(Math.random()*2000));
    }

    @Override
    public void run() {
        while (true) {
            try {
                thinking();
                if (pickUpTableware()) {
                    enjoyFood();
                    putDownTableware();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
