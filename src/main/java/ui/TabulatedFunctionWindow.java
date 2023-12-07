package ui;

import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import exceptions.PointsFieldListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class TabulatedFunctionWindow extends JFrame {
    private JTextField pointsField;
    private JButton createButton;
    private JTable table;

    public TabulatedFunctionWindow() {
        setTitle("Tabulated Function Creator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Проверка исключений
        JTextField pointsField = new JTextField(10);
        pointsField.addActionListener(new PointsFieldListener(pointsField));

        createButton = new JButton("Создать");

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Кол-во точек: "));
        topPanel.add(pointsField);
        topPanel.add(createButton);
        add(topPanel, BorderLayout.NORTH);



        table = new JTable(new DefaultTableModel(0, 2));
        table.setFillsViewportHeight(true);
        add(new JScrollPane(table), BorderLayout.CENTER);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numPoints = Integer.parseInt(pointsField.getText());
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(numPoints);

                createButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        double[] xValues = new double[numPoints];
                        double[] yValues = new double[numPoints];
                        for (int i = 0; i < numPoints; i++) {
                            xValues[i] = Double.parseDouble(model.getValueAt(i, 0).toString());
                            yValues[i] = Double.parseDouble(model.getValueAt(i, 1).toString());
                        }

                        double[][] sortedValues = sortValues(xValues, yValues);

                        ArrayTabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
                        TabulatedFunction function = factory.create(sortedValues[0], sortedValues[1]);
                        System.out.println("Created function: " + function);

                        dispose();
                    }

                    private static double[][] sortValues(double[] xValues, double[] yValues) {
                        int n = xValues.length;

                        List<Map.Entry<Double, Double>> entryList = new ArrayList<>();
                        for (int i = 0; i < n; i++) {
                            entryList.add(new AbstractMap.SimpleEntry<>(xValues[i], yValues[i]));
                        }

                        Collections.sort(entryList, Comparator.comparing(Map.Entry::getKey));

                        double[][] sortedValues = new double[2][n];

                        for (int i = 0; i < n; i++) {
                            sortedValues[0][i] = entryList.get(i).getKey();
                            sortedValues[1][i] = entryList.get(i).getValue();
                        }

                        return sortedValues;
                    }
                });
            }

        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TabulatedFunctionWindow().setVisible(true);
            }
        });
    }
}