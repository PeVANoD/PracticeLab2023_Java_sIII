package concurrent;

import functions.ConstantFunction;
import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;

public class ReadWriteTaskExecutor {
    public static void main(String[] args) {
        TabulatedFunction function = new LinkedListTabulatedFunction(new ConstantFunction(-1), 1, 500, 500);

        Runnable readTask = new ReadTask(function);
        Runnable writeTask = new WriteTask(function, 0.5);

        Thread threadRead = new Thread(readTask);
        Thread threadWrite = new Thread(writeTask);

        threadRead.start();
        threadWrite.start();
    }
}