package concurrent;

import functions.TabulatedFunction;

public class ReadTask implements Runnable {
    private final TabulatedFunction tabulatedFunction;

    public ReadTask(TabulatedFunction tabulatedFunction) {
        this.tabulatedFunction = tabulatedFunction;

    }
    @Override
    public void run() {
        for (int i = 0; i < tabulatedFunction.getCount(); i++) {
            synchronized (tabulatedFunction) {
                String str = String.format("After read: i=%d, x=%f, y=%f", i, tabulatedFunction.getX(i), tabulatedFunction.getY(i));
                System.out.println(str);
            }
        }
    }
}