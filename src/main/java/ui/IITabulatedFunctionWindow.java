package ui;

import exceptions.PointsFieldListener;
import exceptions.TableModelExceptionHandler;
import functions.*;
import functions.factory.ArrayTabulatedFunctionFactory;

import java.util.Map;
import java.util.TreeMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.UIManager;


public class IITabulatedFunctionWindow extends JFrame {
    private JButton createButton;
    private Map<String, MathFunction> functionMap;

    public IITabulatedFunctionWindow() {
        functionMap = createFunctionMap();
        setTitle("Chosen Function Creator");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font = new Font("Arial", Font.PLAIN, 22);

        UIManager.put("Button.font", new Font("Arial", Font.PLAIN, 22));
        UIManager.put("Label.font", new Font("Arial", Font.PLAIN, 22));
        UIManager.put("ComboBox.font", new Font("Arial", Font.PLAIN, 22));

        JPanel mainPanel = new JPanel(new GridLayout(4, 1));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(180, 180, 180));


        JTextField pointsField = new JTextField(15);
        pointsField.addActionListener(new PointsFieldListener(pointsField));

        createButton = new JButton("Create");
        createButton.setFont(font);

        JPanel topPanel = new JPanel(new GridLayout(1, 3));
        topPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 20));
        topPanel.setBackground(new Color(220, 220, 220));
        JComboBox<String> functionComboBox = new JComboBox<>(functionMap.keySet().toArray(new String[0]));
        functionComboBox.setFont(font);
        functionComboBox.setBackground(new Color(250, 250, 250));
        JLabel funclab = new JLabel("Функция: ");
        funclab.setFont(font);
        funclab.setBorder(BorderFactory.createEmptyBorder(0,230,0,0));
        topPanel.add(funclab);
        topPanel.add(functionComboBox);

        JPanel topmidPanel = new JPanel(new GridLayout(1, 2));
        topmidPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 20));
        topmidPanel.setBackground(new Color(180, 180, 180));

        JPanel righttopPanel = new JPanel(new GridLayout(1, 2));
        righttopPanel.setBorder(BorderFactory.createEmptyBorder(0,30,0,0));
        righttopPanel.setBackground(new Color(200, 200, 200));
        JLabel pCountlab = new JLabel("Кол-во точек: ");
        pCountlab.setFont(font);
        righttopPanel.add(pCountlab);
        righttopPanel.add(pointsField, BorderLayout.CENTER);

        topmidPanel.add(righttopPanel);
        topmidPanel.add(createButton);

        add(topPanel, BorderLayout.NORTH);


        JPanel midPanel = new JPanel(new GridLayout(1, 2));
        midPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        midPanel.setBackground(new Color(200, 200, 200));

        JPanel textPanel = new JPanel();
        JLabel intlab = new JLabel("Интервал разбиения ");
        intlab.setFont(font);
        textPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 10, 10));
        textPanel.setBackground(new Color(220, 220, 220));
        textPanel.add(intlab, BorderLayout.CENTER);

        JPanel intervalPanel = new JPanel(new GridLayout(2, 1));
        intervalPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        intervalPanel.setBackground(new Color(240, 240, 240));

        JPanel LIintlab = new JPanel();
        JLabel Iintlab = new JLabel("От: ");
        Iintlab.setFont(font);
        LIintlab.add(Iintlab);
        JTextField fromInterval = new JTextField(10);
        LIintlab.add(fromInterval);
        fromInterval.addActionListener(new PointsFieldListener(fromInterval));

        JPanel RIintlab = new JPanel();
        JLabel IIintlab = new JLabel("До: ");
        IIintlab.setFont(font);
        RIintlab.add(IIintlab);
        JTextField toInterval = new JTextField(10);
        RIintlab.add(toInterval);
        toInterval.addActionListener(new PointsFieldListener(toInterval));

        intervalPanel.add(LIintlab);
        intervalPanel.add(RIintlab);

        midPanel.add(textPanel, BorderLayout.NORTH);
        midPanel.add(intervalPanel, BorderLayout.CENTER);

        add(midPanel, BorderLayout.CENTER);

        mainPanel.add(topPanel);
        mainPanel.add(topmidPanel);
        mainPanel.add(midPanel);
        add(mainPanel, BorderLayout.CENTER);

        pointsField.setFont(font);
        fromInterval.setFont(font);
        toInterval.setFont(font);

        abstract class CreateButtonListener implements ActionListener {
            private JTextField pointsField;
            private JTextField fromInterval;
            private JTextField toInterval;
            private JComboBox<String> functionComboBox;
            public int points;
            public double from;
            public double to;


            public CreateButtonListener(JTextField pointsField, JTextField toInterval, JTextField fromInterval, JComboBox<String> functionComboBox) {
                this.pointsField = pointsField;
                this.fromInterval = fromInterval;
                this.toInterval = toInterval;
                this.functionComboBox = functionComboBox;

            }
        }

        createButton.addActionListener(new CreateButtonListener(pointsField, toInterval, fromInterval, functionComboBox) {

            public void actionPerformed(ActionEvent e) {
                String text = pointsField.getText();
                if (!(text.matches("[0-9]+")))
                    JOptionPane.showMessageDialog(null, "Неверное значение");
                else {

                    try {
                        points = Integer.parseInt(pointsField.getText());
                        from = Integer.parseInt(fromInterval.getText());
                        to = Integer.parseInt(toInterval.getText());

                        String selectedFunction = (String) functionComboBox.getSelectedItem();
                        MathFunction function = functionMap.get(selectedFunction);
                        ArrayTabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
                        TabulatedFunction tabulatedFunction = factory.createTabulatedFunction(function, from, to, points); // Создание табулированной функции
                        System.out.println("Created function: " + tabulatedFunction);

                        dispose();
                    } catch (NumberFormatException ex) {
                        TableModelExceptionHandler.handleException("Некорректное количество точек");
                    }

                    dispose();
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new IITabulatedFunctionWindow().setVisible(true);
            }
        });
    }

    private Map<String, MathFunction> createFunctionMap() {
        Map<String, MathFunction> map = new TreeMap<>();
        map.put("Квадратичная функция", new SqrFunction());
        map.put("Тождественная функция", new IdentityFunction());
        map.put("Косинус двойного угла", new Cos2xFunction());
        map.put("Натуральный логарифм", new LnFunction());
        return map;
    }
}