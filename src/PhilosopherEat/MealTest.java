package PhilosopherEat;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MealTest {
    // Declare an array contains 5 philosophers, another with tableware.
    private static Philosopher[] myPhilosophers = new Philosopher[5];
    private static Object[] myTableware = new Object[5];

    public static void main(String[] args) throws Exception {
        // Initialize the philosophers and tableware
        for (int i = 0; i < myTableware.length; i++) {
            myTableware[i] = new Object();
        }

        for (int i = 0; i < myPhilosophers.length; i++) {
            Object left = myTableware[i];
            Object right = myTableware[(i + 1) % myTableware.length];
            myPhilosophers[i] = new Philosopher(left, right,  i);

            Thread myThread = new Thread(myPhilosophers[i], "Philosopher" + (i + 1));
            myThread.start();
        }
    }
}
