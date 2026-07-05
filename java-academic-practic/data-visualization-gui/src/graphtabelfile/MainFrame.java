/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphtabelfile;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public static MainFrame hFrame; 
    public static WestPanel westPanel;
    public static JPanel centerPanel; 

    public MainFrame() {
        hFrame = this; 
        setTitle("Лаб 5 Методы численного интегрирования");
        setSize(950, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        westPanel = new WestPanel();
        westPanel.setPreferredSize(new Dimension(200, getHeight()));

        centerPanel = new JPanel(new BorderLayout()); 
        centerPanel.setBackground(Color.WHITE);
        
        add(new NorthPanel(), BorderLayout.NORTH);
        
        add(westPanel, BorderLayout.WEST);

        add(centerPanel, BorderLayout.CENTER);

        add(new SouthPanel(), BorderLayout.SOUTH);
    }

    public static Container getC() {
        return centerPanel;
    }
}