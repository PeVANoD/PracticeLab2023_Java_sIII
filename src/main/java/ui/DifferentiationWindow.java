package ui;

import functions.LnFunction;
import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import operations.TabulatedDifferentialOperator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DifferentiationWindow {
    private JFrame frame;
    private JTextField inputFunctionField;
    private JTextArea resultArea;
    private double[] xValues;
    private double[] yValues;

    public DifferentiationWindow(double[] xValues, double[] yValues) {
        this.xValues = xValues;
        this.yValues = yValues;
        frame = new JFrame("Differentiate Function");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        inputFunctionField = new JTextField(20);
        JButton differentiateButton = new JButton("Differentiate");
        resultArea = new JTextArea(10, 20);

        differentiateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputFunction = inputFunctionField.getText();
                LnFunction lnFunction = new LnFunction();
                TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator();
                TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
                TabulatedFunction myFunction = factory.create(xValues, yValues);
                TabulatedFunction result = differentialOperator.derive(myFunction);
                resultArea.setText("Result of differentiation for function: " + inputFunction);
            }
        });

        panel.add(inputFunctionField);
        panel.add(differentiateButton);
        panel.add(resultArea);

        frame.add(panel);
    }

    public void show() {
        frame.setVisible(true);
    }
}