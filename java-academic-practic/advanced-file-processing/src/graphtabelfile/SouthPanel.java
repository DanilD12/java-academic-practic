/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphtabelfile;

import javax.swing.*;
import java.awt.*;

public class SouthPanel extends JPanel {
    public SouthPanel() {
        // Текст по центру, жирным курсивом
        JLabel lbl = new JLabel("Функция: " + MyFunc.getMyFuncStr());
        lbl.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 20));
        add(lbl);
    }
}