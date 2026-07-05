/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphtabelfile;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class WestPanel extends JPanel {
    
    private static JTextField txt1 = new JTextField("0");
    private static JTextField txt2 = new JTextField("5");
    private static JTextField txt3 = new JTextField("10");

    public WestPanel() {
        
        int w = 200; 
        
        JPanel jp = new JPanel();
        jp.setBorder(new TitledBorder("Данные"));

        jp.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL; 
        c.anchor = GridBagConstraints.NORTHWEST; 
        c.weightx = 1.0;
        c.gridx = 0;

        JLabel lbl1 = new JLabel("X мин:");
        c.gridy = 0; 
        c.insets = new Insets(5, 5, 2, 5);
        jp.add(lbl1, c);

        c.gridy = 1; 
        c.insets = new Insets(0, 5, 10, 5);
        jp.add(txt1, c);

        JLabel lbl2 = new JLabel("X макс:");
        c.gridy = 2;
        c.insets = new Insets(0, 5, 2, 5);
        jp.add(lbl2, c);

        c.gridy = 3;
        c.insets = new Insets(0, 5, 10, 5);
        jp.add(txt2, c);

        JLabel lbl3 = new JLabel("Кол. точек:");
        c.gridy = 4;
        c.insets = new Insets(0, 5, 2, 5);
        jp.add(lbl3, c);

        c.gridy = 5;
        c.insets = new Insets(0, 5, 10, 5);
        jp.add(txt3, c);

        JPanel spacer = new JPanel();
        c.gridy = 6;
        c.weighty = 1.0;
        jp.add(spacer, c);

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(w, 400)); 
        this.add(jp, BorderLayout.CENTER);
    }

    public static String getText1() { return txt1.getText(); }
    public static String getText2() { return txt2.getText(); }
    public static String getText3() { return txt3.getText(); }

    public static void setText1(String str) { txt1.setText(str); }
    public static void setText2(String str) { txt2.setText(str); }
    public static void setText3(String str) { txt3.setText(str); }
}