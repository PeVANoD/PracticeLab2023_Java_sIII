package exceptions;

import com.sun.java.accessibility.util.SwingEventMonitor;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JTable;

public class TableModelExceptionHandler implements TableModelListener {
    private JTable table;

    public TableModelExceptionHandler(JTable table) {
        this.table = table;
        this.table.getModel().addTableModelListener(this);
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int col = e.getColumn();

        String text = String.valueOf(table.getValueAt(row, col));
        try {
            if (text.equals(""))
                return;
            int value = Integer.parseInt(text);
        } catch (NumberFormatException ex) {
            handleException("Введите число");
            table.setValueAt("", row, col);
        } catch (IllegalArgumentException ex) {
            handleException(ex.getMessage());
            table.setValueAt("", row, col);
        }
    }

    public static void handleException(String message) {
        JOptionPane.showMessageDialog(null, message, "Input Error", JOptionPane.WARNING_MESSAGE);
    }
}