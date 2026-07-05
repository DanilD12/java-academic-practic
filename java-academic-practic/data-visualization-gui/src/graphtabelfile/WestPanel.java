/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphtabelfile;

import javax.swing.*;
import java.awt.*;

public class WestPanel extends JPanel {
    JTextField txtXMin, txtXMax, txtN;

    public WestPanel() {
        // Используем BoxLayout для вертикального списка
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Задаем ширину панели
        setPreferredSize(new Dimension(140, 0));
        
        // Рамка с отступами
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Данные"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        // --- Элементы ---
        add(new JLabel("X мин:"));
        txtXMin = new JTextField("0");
        // Фиксируем высоту поля, чтобы оно не растягивалось на пол-экрана
        txtXMin.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25)); 
        add(txtXMin);
        
        add(Box.createVerticalStrut(10)); // Пустое место
        
        add(new JLabel("X макс:"));
        txtXMax = new JTextField("5");
        txtXMax.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        add(txtXMax);
        
        add(Box.createVerticalStrut(10)); // Пустое место

        add(new JLabel("Кол. точек:"));
        txtN = new JTextField("10");
        txtN.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        add(txtN);
        
        // "Пружина" внизу, чтобы прижать всё к верху
        add(Box.createVerticalGlue());
    }
    
    // Метод сохранения данных
    public void saveDataToVerify() {
        try {
            Verify.xMin = Double.parseDouble(txtXMin.getText().replace(",", "."));
            Verify.xMax = Double.parseDouble(txtXMax.getText().replace(",", "."));
            Verify.n = Integer.parseInt(txtN.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ошибка ввода чисел!");
        }
    }
}