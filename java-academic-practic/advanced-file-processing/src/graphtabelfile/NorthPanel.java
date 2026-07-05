/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphtabelfile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NorthPanel extends JPanel {
    
    public NorthPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER)); // Кнопки по центру
        
        JButton btnCalc = new JButton("График + Таблица");
        JButton btnSave = new JButton("Записать в файл");
        JButton btnLoad = new JButton("Считать из файла");
        
        add(btnCalc);
        add(btnSave);
        add(btnLoad);
        
        btnCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 1. Сохраняем данные из полей ввода
                if (MainFrame.westPanel != null) {
                    MainFrame.westPanel.saveDataToVerify();
                }
                
                // 2. Проверяем ошибки
                if (Verify.getVerify() != 0) {
                    JOptionPane.showMessageDialog(null, "Ошибка! Проверьте: точек > 2 и Xmin < Xmax.");
                    return;
                }

                // 3. Обновляем ГРАФИК (Центр)
                JPanel center = MainFrame.centerPanel;
                center.removeAll();
                MakeGraph graph = new MakeGraph(Verify.xMin, Verify.xMax, Verify.n);
                center.add(graph, BorderLayout.CENTER);
                
                // 4. Обновляем ТАБЛИЦУ (Восток/Справа)
                JFrame frame = MainFrame.hFrame;
                Container contentPane = frame.getContentPane();
                
                // Удаляем старую таблицу, если она есть
                Component oldEast = ((BorderLayout)contentPane.getLayout()).getLayoutComponent(BorderLayout.EAST);
                if (oldEast != null) contentPane.remove(oldEast);
                
                // Создаем новую
                EastPanel eastPanel = new EastPanel();
                contentPane.add(eastPanel, BorderLayout.EAST);
                
                // 5. Перерисовываем окно
                frame.revalidate();
                frame.repaint();
            }
        });
    }
}