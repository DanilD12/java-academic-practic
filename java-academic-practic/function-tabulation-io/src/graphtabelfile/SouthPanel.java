/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphtabelfile;

import javax.swing.*;
import java.awt.*;

public class SouthPanel extends JPanel {
    public SouthPanel() {
        String str = MyFunc.getMyFuncStr();
        JLabel lbl1 = new JLabel("Функция: " + str);
        lbl1.setFont(new Font("SansSerif", Font.ITALIC + Font.BOLD, 22));
        add(lbl1);
    }
}