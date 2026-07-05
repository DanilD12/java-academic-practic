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
        double xMin = Verify.xMin;
        double xMax = Verify.xMax;
        int n = Verify.n;
        JTable tbl = null;
        
        try {
            setLayout(new BorderLayout());
            
            MakeTable makeTable = new MakeTable();
            tbl = makeTable.getTable(xMin, xMax, n);

            tbl.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            
            JScrollPane jsp = new JScrollPane(tbl);
            
           
            int w = Math.round(0.3f * MainFrame.getW());
            int h = Math.round(0.8f * MainFrame.getH());
            
            jsp.setPreferredSize(new Dimension(w, h));
            
            add(jsp, BorderLayout.CENTER);
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ошибка при построении таблицы!");
        }
    }
}