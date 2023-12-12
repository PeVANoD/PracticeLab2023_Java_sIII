package exceptions;

import javax.swing.*;

public class UncaughtExceptionHandlerImpl implements Thread.UncaughtExceptionHandler {
    private final JFrame parentFrame;

    public UncaughtExceptionHandlerImpl(JFrame parentFrame) {
        this.parentFrame = parentFrame;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        JOptionPane.showMessageDialog(parentFrame, "Error: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
    }
}