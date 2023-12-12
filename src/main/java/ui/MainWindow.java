package ui;

import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import operations.TabulatedFunctionOperationService;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainWindow extends JFrame {
    private TabulatedFunctionFactory factory;
    private TabulatedFunctionFactory selectedFactory;
    private double[] xValues;
    private double[] yValues;

    public MainWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Главное окно");

        JPanel borderPanel = new JPanel(new BorderLayout());
        borderPanel.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 200));
        borderPanel.add(Box.createRigidArea(new Dimension(0, 0)));

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 400));
        borderPanel.add(mainPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        buttonPanel.setBorder(BorderFactory.createLineBorder(new Color(160, 160, 160), 100));

        JButton generateButton = new JButton("Сгенерировать табулированную функцию");
        generateButton.setFont(new Font(generateButton.getFont().getName(), generateButton.getFont().getStyle(), generateButton.getFont().getSize() * 3));
        generateButton.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 30));
        generateButton.addActionListener(e -> openOperationWindow(this));
        buttonPanel.add(generateButton);

        JButton differentiationButton = new JButton("Продифференцировать");
        differentiationButton.setFont(new Font(differentiationButton.getFont().getName(), differentiationButton.getFont().getStyle(), differentiationButton.getFont().getSize() * 3));
        differentiationButton.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 30));
        differentiationButton.addActionListener(e -> openDifferentiationWindow());
        buttonPanel.add(differentiationButton);

        JButton settingsButton = new JButton("Настройки");
        settingsButton.setFont(new Font(settingsButton.getFont().getName(), settingsButton.getFont().getStyle(), settingsButton.getFont().getSize() * 3));
        settingsButton.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 30));
        settingsButton.addActionListener(e -> openSettingsWindow());
        buttonPanel.add(settingsButton);

        mainPanel.add(buttonPanel);

        borderPanel.add(Box.createGlue(), BorderLayout.SOUTH);
        borderPanel.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));

        setContentPane(borderPanel);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void openSettingsWindow() {
        TabulatedFunctionFactory defaultFactory = new ArrayTabulatedFunctionFactory();
        SettingsWindow settingsWindow = new SettingsWindow(defaultFactory);
        JDialog settingsDialog = new JDialog(settingsWindow);
        settingsDialog.setTitle("Settings");
        settingsDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        settingsDialog.setSize(480, 420);
        settingsDialog.setLocationRelativeTo(null);
        settingsDialog.setContentPane(settingsWindow.getContentPane());
        settingsDialog.setModal(true);
        settingsDialog.setVisible(true);
    }

    private void openOperationWindow(JFrame owner) {
        OperationWindow operationWindow = new OperationWindow(new TabulatedFunctionOperationService(), owner);
        operationWindow.setVisible(true);
    }

    private void openDifferentiationWindow() {
        JDialog differentiationWindow = new JDialog();
        differentiationWindow.setModal(true);
        differentiationWindow.setLayout(new BorderLayout());
        differentiationWindow.add(new JLabel("Differentiation Window Content"), BorderLayout.CENTER);
        differentiationWindow.setSize(600, 300);
        differentiationWindow.setLocationRelativeTo(null);
        differentiationWindow.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}