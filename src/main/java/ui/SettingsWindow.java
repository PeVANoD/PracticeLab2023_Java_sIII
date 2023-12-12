package ui;

import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsWindow extends JDialog {
    private TabulatedFunctionFactory selectedFactory;

    public SettingsWindow(TabulatedFunctionFactory factory) {
        this.selectedFactory = factory;

        setTitle("Settings");
        setSize(480, 480);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel wrapPanel = new JPanel();
        wrapPanel.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 60));
        wrapPanel.setLayout(new BoxLayout(wrapPanel, BoxLayout.Y_AXIS));
        wrapPanel.setBackground(new Color(200, 200, 200));
        JPanel panel = new JPanel();
        wrapPanel.add(panel, CENTER_ALIGNMENT);
        panel.setBackground(new Color(220, 220, 220));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel pLabel = new JPanel();
        JLabel label = new JLabel("Select Factory Type: ");
        label.setFont(new Font("Arial", Font.PLAIN, 24));
        pLabel.add(label);
        pLabel.setBackground(new Color(240, 240, 240));
        pLabel.setMaximumSize(new Dimension(240, 120));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JComboBox<String> factoryComboBox = new JComboBox<>();
        factoryComboBox.addItem("Array");
        factoryComboBox.addItem("Linked");

        if (factory instanceof ArrayTabulatedFunctionFactory) {
            factoryComboBox.setSelectedItem("Array");
        } else if (factory instanceof LinkedListTabulatedFunctionFactory) {
            factoryComboBox.setSelectedItem("Linked");
        }
        factoryComboBox.setPreferredSize(new Dimension(200, 30));
        factoryComboBox.setMaximumSize(factoryComboBox.getPreferredSize());
        factoryComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        factoryComboBox.setFont(new Font("Verdana", Font.PLAIN, 18));


        factoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) factoryComboBox.getSelectedItem();
                if (selectedType.equals("Array")) {
                    selectedFactory = new ArrayTabulatedFunctionFactory();
                } else if (selectedType.equals("Linked")) {
                    selectedFactory = new LinkedListTabulatedFunctionFactory();
                }
            }
        });

        JButton saveButton = new JButton("Save");
        saveButton.setFont(new Font("Arial", Font.BOLD, 18));
        saveButton.setPreferredSize(new Dimension(0, 36));
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedFactory = getSelectedFactory();
                dispose();
            }
        });

        panel.add(Box.createRigidArea(new Dimension(280, 80)));
        panel.add(pLabel);
        panel.add(Box.createVerticalGlue());
        factoryComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(factoryComboBox);
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(saveButton);
        panel.add(Box.createVerticalGlue());

        wrapPanel.add(Box.createVerticalGlue());
        wrapPanel.add(panel);
        wrapPanel.add(Box.createVerticalGlue());
        wrapPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(wrapPanel);
    }

    public TabulatedFunctionFactory getSelectedFactory() {
        return selectedFactory;
    }

    public static void main(String[] args) {
        TabulatedFunctionFactory defaultFactory = new ArrayTabulatedFunctionFactory();
        SettingsWindow settingsWindow = new SettingsWindow(defaultFactory);
        settingsWindow.setModal(true);
        settingsWindow.setVisible(true);
        TabulatedFunctionFactory selectedFactory = settingsWindow.getSelectedFactory();
        System.out.println("Selected Factory: " + selectedFactory);
    }
}