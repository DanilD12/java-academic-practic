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
        // Устанавливаем менеджер компоновки
        setLayout(new BorderLayout());

        // Получаем данные (предполагается, что класс Verify существует и поля заполнены)
        double xMin = Verify.xMin;
        double xMax = Verify.xMax;
        int n = Verify.n;

        JTable tbl = null;

        try {
            
            MakeTable makeTable = new MakeTable();
            // Получаем готовую JTable с данными
            tbl = makeTable.getTable(xMin, xMax, n);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ошибка при построении таблицы: " + ex.getMessage());
        }

        if (tbl != null) {
            // Получаем модель колонок для настройки ширины
            TableColumnModel cm = tbl.getColumnModel();

            // --- Настройка ширины колонок согласно заданию (стр. 4 PDF) ---
            
            // Столбец 0 ("№") - делаем узким
            cm.getColumn(0).setMaxWidth(30);
            cm.getColumn(0).setPreferredWidth(30);
            
            // Столбец 1 ("Метод") - даем больше места под название
            cm.getColumn(1).setPreferredWidth(160);
            
            // Столбец 2 ("Значение") - место под числа
            cm.getColumn(2).setPreferredWidth(100);

            // Создаем панель прокрутки, помещаем туда таблицу
            JScrollPane jsp = new JScrollPane(tbl);
            
            // Можно задать предпочтительный размер, если панель сжимается
            // jsp.setPreferredSize(new Dimension(350, 400)); 

            // Добавляем скролл-панель на саму панель EastPanel
            add(jsp, BorderLayout.CENTER);
        }
    }
}