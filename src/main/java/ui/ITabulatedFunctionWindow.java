package ui;

import exceptions.TableModelExceptionHandler;
import exceptions.UncaughtExceptionHandlerImpl;
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

public class ITabulatedFunctionWindow extends JFrame {
    private JButton createButton;

    public ITabulatedFunctionWindow() {
        setTitle("Tabulated Function Creator");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Font font = new Font("Arial", Font.PLAIN, 22);

        JTextField pointsField = new JTextField(10);
        pointsField.addActionListener(new PointsFieldListener(pointsField));

        createButton = new JButton("Создать");
        createButton.setFont(font);

        JPanel topPanel = new JPanel();
        JLabel cPoints = new JLabel("Кол-во точек: ");
        cPoints.setFont(font);
        topPanel.add(cPoints);
        topPanel.add(pointsField);
        topPanel.add(createButton);
        add(topPanel, BorderLayout.NORTH);

        JTable myTable = new JTable(new DefaultTableModel(0, 2));
        myTable.setFillsViewportHeight(true);
        add(new JScrollPane(myTable), BorderLayout.CENTER);




        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandlerImpl(ITabulatedFunctionWindow.this));
                try {
                    int numPoints = Integer.parseInt(pointsField.getText());
                    DefaultTableModel model = (DefaultTableModel) myTable.getModel();
                    model.setRowCount(numPoints);
                    TableModelExceptionHandler IIexceptionHandler = new TableModelExceptionHandler(myTable);


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
                } catch (Exception ex) {
                    Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), ex);
                }

            }

        });
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ITabulatedFunctionWindow().setVisible(true);
            }
        });
    }
}