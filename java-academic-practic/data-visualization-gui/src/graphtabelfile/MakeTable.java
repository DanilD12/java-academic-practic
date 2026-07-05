/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphtabelfile;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MakeTable {
    
    public JTable getTable(double xMin, double xMax, int n) {
        String[] colNames = new String[]{"№", "Метод", "Значение"};

        String[][] cells = new String[10][3];
        
        for (int i = 0; i < 10; i++) {
            cells[i][0] = Integer.toString(i); 
            

            String[] result = MyFunc.getMethodsIntegr(i, n, xMin, xMax);
            cells[i][1] = result[0];
            cells[i][2] = result[1];
        }
        
        return new MyJTable(cells, colNames);
    }

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