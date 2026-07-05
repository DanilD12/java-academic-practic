/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphtabelfile;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends JPanel {
    public CenterPanel() {
        double xMin = Verify.xMin;
        double xMax = Verify.xMax;
        int n = Verify.n;
        
        try {
            setLayout(new BorderLayout());
            add(new MakeGraph(xMin, xMax, n));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ошибка при построении графика!");
        }
    }
}