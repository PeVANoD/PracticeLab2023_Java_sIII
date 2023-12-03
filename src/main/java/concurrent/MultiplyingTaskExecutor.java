package concurrent;

import functions.*;

import java.util.concurrent.*;

public class MultiplyingTaskExecutor {
    public static void main(String[] args) throws InterruptedException {
        TabulatedFunction func = new LinkedListTabulatedFunction(new UnitFunction(), 1, 1000, 20000);
        int threads = 10;

        ExecutorService executorService = Executors.newFixedThreadPool(threads);
        CountDownLatch latch = new CountDownLatch(threads);

        for (int i = 0; i < threads; i++) {
            Runnable multTask = new MultiplyingTask(func, latch);
            executorService.execute(multTask);
        }

        latch.await();
        executorService.shutdown();

        System.out.println(func);
    }
}