package concurrent;

import functions.TabulatedFunction;

public class WriteTask implements Runnable {
    private TabulatedFunction tabulatedFunction;
    private double value;

    public WriteTask(TabulatedFunction tabulatedFunction, double value) {
        this.tabulatedFunction = tabulatedFunction;
        this.value = value;
    }

    @Override
    public void run() {
        for (int i = 0; i < tabulatedFunction.getCount(); i++) {
            synchronized (tabulatedFunction) {
                tabulatedFunction.setY(i, value);
                String str = String.format("Writing for index %d complete", i);
                System.out.println(str);
            }
        }
    }
}