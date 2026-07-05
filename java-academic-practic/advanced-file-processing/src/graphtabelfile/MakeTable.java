/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphtabelfile;

import javax.swing.JTable;
import java.util.Locale;

public class MakeTable {
    
    public JTable getTable(double xMin, double xMax, int n) {
        String[] colNames = new String[]{"X", "2x^2-10x+5...", "a2x^2+a1x+a0"};
        String[][] cells = new String[n][3];
        
        // Получаем коэффициенты аппроксимации
        Approxim2 apr = new Approxim2();
        double[] a = apr.mnk(xMin, xMax, n);
        
        double dx = (xMax - xMin) / (n - 1);
        
        for (int i = 0; i < n; i++) {
            double x = xMin + dx * i;
            double y_orig = MyFunc.getMyFunc(x);
            double y_appr = MyFunc.getApproxFunc(x, a);
            
            // Форматирование значений (как в PDF на стр. 7)
            // Используем Locale.US чтобы была точка, а не запятая
            cells[i][0] = String.format(Locale.US, "%1.5f", x);
            cells[i][1] = String.format(Locale.US, "%1.5f", y_orig);
            cells[i][2] = String.format(Locale.US, "%1.5f", y_appr);
        }
        
        return new MyJTable(cells, colNames);
    }
    
    // Вспомогательный класс для таблицы (нередактируемый)
    public static class MyJTable extends JTable {
        public MyJTable(Object[][] rowData, Object[] columnNames) {
            super(rowData, columnNames);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
}