package ui;

import functions.ArrayTabulatedFunction;
import functions.TabulatedFunction;
import operations.TabulatedFunctionOperationService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperationWindow extends JDialog {
    private TabulatedFunctionOperationService operationService;
    private JTable firstFunctionTable;
    private JTable secondFunctionTable;
    private JTable resultTable;

    public OperationWindow(TabulatedFunctionOperationService operationService, JFrame owner) {
        super(owner, "Function Operation Window", true); // создаем модальное диалоговое окно, указывая владельца и устанавливаем его видимость
        this.operationService = operationService;

        // Инициализация компонентов
        // Создание областей для первого операнда, второго операнда и результата, представленных в виде JTable
        // Создание кнопок для создания, загрузки и сохранения функции для каждого операнда
        // Создание кнопок для выполнения операций (сложение, вычитание, умножение, деление)

        // ... создание компонентов и добавление их на форму ...

        // Пример кнопки для сложения
        JButton addButton = new JButton("Сложить");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TabulatedFunction firstFunction = getTabulatedFunctionFromTable(firstFunctionTable);
                TabulatedFunction secondFunction = getTabulatedFunctionFromTable(secondFunctionTable);
                TabulatedFunction resultFunction = operationService.add(firstFunction, secondFunction);
                displayFunctionInTable(resultFunction, resultTable);
            }
        });

        JButton subtractButton = new JButton("Вычесть");
        subtractButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TabulatedFunction firstFunction = getTabulatedFunctionFromTable(firstFunctionTable);
                TabulatedFunction secondFunction = getTabulatedFunctionFromTable(secondFunctionTable);
                TabulatedFunction resultFunction = operationService.subtraction(firstFunction, secondFunction);
                displayFunctionInTable(resultFunction, resultTable);
            }
        });

        JButton multiplyButton = new JButton("Умножить");
        multiplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TabulatedFunction firstFunction = getTabulatedFunctionFromTable(firstFunctionTable);
                TabulatedFunction secondFunction = getTabulatedFunctionFromTable(secondFunctionTable);
                TabulatedFunction resultFunction = operationService.multiplication(firstFunction, secondFunction);
                displayFunctionInTable(resultFunction, resultTable);
            }
        });
        // Пример кнопки для деления
        JButton divideButton = new JButton("Поделить");
        divideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TabulatedFunction firstFunction = getTabulatedFunctionFromTable(firstFunctionTable);
                TabulatedFunction secondFunction = getTabulatedFunctionFromTable(secondFunctionTable);
                TabulatedFunction resultFunction = operationService.division(firstFunction, secondFunction);
                displayFunctionInTable(resultFunction, resultTable);
            }
        });
        JLabel functionTypeLabel1 = new JLabel("Тип данных для первого операнда:");
        JComboBox<String> functionTypeComboBox1 = new JComboBox<>(new String[] {"Массивы", "Табулированная функция"});

        JButton createFunctionButton1 = new JButton("Создать");
        createFunctionButton1.addActionListener(e -> {
            String selectedType = (String) functionTypeComboBox1.getSelectedItem();
            if (selectedType.equals("Массивы")) {
                // Открыть окно для создания функции из массивов: TabulatedFunctionI
                // После создания функции она должна добавиться в соответствующую таблицу
            } else {
                // Открыть окно для создания функции из другой табулированной функции: TabulatedFunctionII
                // После создания функции она должна добавиться в соответствующую таблицу
            }
        });
        JLabel functionTypeLabel2 = new JLabel("Тип данных для второго операнда:");
        JComboBox<String> functionTypeComboBox2 = new JComboBox<>(new String[]{"Массивы", "Табулированная функция"});

        JButton createFunctionButton2 = new JButton("Создать");
        createFunctionButton2.addActionListener(e -> {
            String selectedType = (String) functionTypeComboBox2.getSelectedItem();
            if (selectedType.equals("Массивы")) {
                // Логика создания функции из массивов: TabulatedFunctionI
                // После создания функции она должна добавиться в соответствующую таблицу
            } else {
                // Логика создания функции из другой табулированной функции: TabulatedFunctionII
                // После создания функции она должна добавиться в соответствующую таблицу
            }
        });

        JPanel mainPanel = new JPanel(new GridLayout(3, 1));
        // Секция для первого операнда
        JPanel firstFunctionPanel = new JPanel(new FlowLayout());
        firstFunctionPanel.add(functionTypeLabel1);
        firstFunctionPanel.add(functionTypeComboBox1);
        firstFunctionPanel.add(createFunctionButton1);
        mainPanel.add(firstFunctionPanel);

        // Создание JPanel и добавление компонентов для второго операнда
        JPanel secondFunctionPanel = new JPanel(new FlowLayout());
        secondFunctionPanel.add(functionTypeLabel2);
        secondFunctionPanel.add(functionTypeComboBox2);
        secondFunctionPanel.add(createFunctionButton2);
        mainPanel.add(secondFunctionPanel);


        JPanel thirdPanel = new JPanel(new FlowLayout());
        JPanel operationsPanel = new JPanel(new FlowLayout());
        operationsPanel.add(addButton);
        operationsPanel.add(subtractButton);
        operationsPanel.add(multiplyButton);
        operationsPanel.add(divideButton);

        // Секция для результатов операций
        JPanel resultPanel = new JPanel(new FlowLayout());
        JLabel resultLabel = new JLabel("Результат операции:");
        resultPanel.add(resultLabel);
        // Добавление компонентов для отображения результата
        thirdPanel.add(operationsPanel,TOP_ALIGNMENT);
        thirdPanel.add(resultPanel,BOTTOM_ALIGNMENT);
        mainPanel.add(thirdPanel);
        // Добавление компонентов к окну
        // Установка layout и распределение компонентов на форме
        add(mainPanel, BorderLayout.CENTER);
        // Подготовка окна для отображения
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
        setSize(1400, 900);
        setLocationRelativeTo(owner);
        setVisible(true);
    }

    // Метод для создания табулированной функции из JTable
    private TabulatedFunction getTabulatedFunctionFromTable(JTable table) {
        // Получение данных из JTable и создание табулированной функции
        int rowCount = table.getRowCount();
        double[] xValues = new double[rowCount];
        double[] yValues = new double[rowCount];
        for (int i = 0; i < rowCount; i++) {
            xValues[i] = (double) table.getValueAt(i, 0);
            yValues[i] = (double) table.getValueAt(i, 1);
        }
        // Предположим, что для создания табулированной функции у вас есть конструктор, который принимает массивы x и y
        TabulatedFunction tabulatedFunction = new ArrayTabulatedFunction(xValues, yValues); // Заменить на вашу реализацию
        return tabulatedFunction;
    }

    // Метод для отображения табулированной функции в JTable
    private void displayFunctionInTable(TabulatedFunction function, JTable table) {
        // Отображение значений табулированной функции в JTable
        int rowCount = function.getCount();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Начнем с очистки таблицы
        for (int i = 0; i < rowCount; i++) {
            model.addRow(new Object[]{function.getX(i), function.getY(i)});
        }
    }
}