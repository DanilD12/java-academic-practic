/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphtabelfile;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class EastPanel extends JPanel {

    public EastPanel() {

        setLayout(new BorderLayout());

        double xMin = Verify.xMin;
        double xMax = Verify.xMax;
        int n = Verify.n;

        JTable tbl = null;

        try {

            MakeTable makeTable = new MakeTable();

            tbl = makeTable.getTable(xMin, xMax, n);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ошибка при построении таблицы: " + ex.getMessage());
        }

        if (tbl != null) {
            TableColumnModel cm = tbl.getColumnModel();

            cm.getColumn(0).setMaxWidth(30);
            cm.getColumn(0).setPreferredWidth(30);
            
            cm.getColumn(1).setPreferredWidth(160);

            cm.getColumn(2).setPreferredWidth(100);

            JScrollPane jsp = new JScrollPane(tbl);
            
            add(jsp, BorderLayout.CENTER);
        }
    }
}