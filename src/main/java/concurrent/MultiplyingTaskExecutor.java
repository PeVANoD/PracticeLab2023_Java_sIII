package concurrent;

import functions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

public class MultiplyingTaskExecutor {
    public static void main(String[] args) throws InterruptedException {
        TabulatedFunction func = new LinkedListTabulatedFunction(new UnitFunction(), 1, 1000, 20000);
        int threads = 10;

        ExecutorService executorService = Executors.newFixedThreadPool(threads);
        CountDownLatch latch = new CountDownLatch(threads);

        List<MultiplyingTask> multiplyingTasks = new ArrayList<>();

        for (int i = 0; i < threads; i++) {
            MultiplyingTask multTask = new MultiplyingTask(func, latch);
            multiplyingTasks.add(multTask);
            executorService.execute(multTask);
        }

        while (true) {
            Iterator<MultiplyingTask> iterator = multiplyingTasks.iterator();
            while (iterator.hasNext()) {
                MultiplyingTask task = iterator.next();
                if (task.isCompleted()) {
                    iterator.remove();
                }
            }
            if (multiplyingTasks.isEmpty()) {
                break;
            }
        }

        executorService.shutdown();
        System.out.println(func);
    }
}