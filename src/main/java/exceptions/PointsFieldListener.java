package exceptions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PointsFieldListener implements ActionListener {
    private JTextField pointsField;

    public PointsFieldListener(JTextField pointsField) {
        this.pointsField = pointsField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = pointsField.getText();
        try {
            int value = Integer.parseInt(text);
            if (value <= 0) {
                pointsField.setText("");
                throw new IllegalArgumentException("Введите положительное число");
            }
        } catch (NumberFormatException ex) {
            handleException("Введите число");
            pointsField.setText("");
        } catch (IllegalArgumentException ex) {
            handleException(ex.getMessage());
            pointsField.setText("");
        }
    }

    private void handleException(String message) {
        JOptionPane.showMessageDialog(null, message, "Input Error", JOptionPane.WARNING_MESSAGE);
    }
}

