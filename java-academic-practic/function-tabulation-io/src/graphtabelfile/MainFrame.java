/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphtabelfile;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static int height;
    private static int width;
    private static Container c;
    
    public MainFrame() {
        setPosition();
        setResizable(true); 
        c = getContentPane();
        c.setLayout(new BorderLayout());
        
        c.add(new NorthPanel(), BorderLayout.NORTH);
        c.add(new WestPanel(), BorderLayout.WEST);
        c.add(new SouthPanel(), BorderLayout.SOUTH);
    }
    
    private void setPosition() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        width = Math.round(0.8f * screenSize.width);
        height = Math.round(0.8f * screenSize.height);
        int top = (screenSize.height - height) / 2;
        int left = (screenSize.width - width) / 2;
        setSize(width, height);
        setLocation(left, top);
    }
    
    public static int getH() { return height; }
    public static int getW() { return width; }
    public static Container getC() { return c; }
}