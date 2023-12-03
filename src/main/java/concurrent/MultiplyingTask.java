package concurrent;

import functions.TabulatedFunction;
import java.util.concurrent.CountDownLatch;

class MultiplyingTask implements Runnable {
    private final TabulatedFunction function;
    private final CountDownLatch latch;
    private boolean isCompleted = false;
    public MultiplyingTask(TabulatedFunction function, CountDownLatch latch) {
        this.function = function;
        this.latch = latch;
    }
    public boolean isCompleted() {
        return isCompleted;
    }
    @Override
    public void run() {
        for (int i = 0; i < function.getCount(); i++) {
            synchronized (function) {
                function.setY(i, function.getY(i) * 2);
            }
        }
        isCompleted = true;
        latch.countDown();
        String name = Thread.currentThread().getName();
        System.out.println("Thread " + name + " has completed its task.");

        latch.countDown();
    }
}