/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphtabelfile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class NorthPanel extends JPanel {
    JButton btn1 = new JButton("График + Таблица");
    JButton btn2 = new JButton("Записать в файл"); 
    JButton btn3 = new JButton("Считать из файла"); 

    public NorthPanel() {

        add(btn1);
        add(btn2);
        add(btn3);
        
        btn1.addActionListener(new ActionBtn1());
        btn2.addActionListener(new ActionBtn2());
        btn3.addActionListener(new ActionBtn3());
    }

    class ActionBtn1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (Verify.getVerify() != 0) return;
            updateInterface();
        }
    }

    class ActionBtn2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (Verify.getVerify() != 0) return;
            
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Сохранить данные графика");
            int userSelection = fileChooser.showSaveDialog(NorthPanel.this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();

                if (!fileToSave.getAbsolutePath().endsWith(".txt")) {
                    fileToSave = new File(fileToSave.getAbsolutePath() + ".txt");
                }

                try (PrintWriter writer = new PrintWriter(fileToSave)) {

                    double xMin = Verify.xMin;
                    double xMax = Verify.xMax;
                    int n = Verify.n;
                    double dx = (xMax - xMin) / (n - 1);

                    writer.println(xMin + ";" + xMax + ";" + n);
                    
                    for (int i = 0; i < n; i++) {
                        double x = xMin + dx * i;
                        double y = MyFunc.getMyFunc(x);
                        writer.println(x + ";" + y);
                    }
                    
                    JOptionPane.showMessageDialog(null, "Данные успешно сохранены в файл:\n" + fileToSave.getName());
                    
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Ошибка при записи файла: " + ex.getMessage());
                }
            }
        }
    }

    class ActionBtn3 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Открыть файл с данными");
            int userSelection = fileChooser.showOpenDialog(NorthPanel.this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToRead = fileChooser.getSelectedFile();
                
                try (Scanner scanner = new Scanner(fileToRead)) {
                    if (scanner.hasNextLine()) {
                       
                        String line = scanner.nextLine();
                        String[] parts = line.split(";");
                        
                        if (parts.length >= 3) {
                            WestPanel.setText1(parts[0]);
                            WestPanel.setText2(parts[1]);
                            WestPanel.setText3(parts[2]);

                            if (Verify.getVerify() == 0) {
                                updateInterface();
                                JOptionPane.showMessageDialog(null, "Параметры загружены и график построен.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Неверный формат файла (ожидалось: min;max;n)");
                        }
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Ошибка при чтении файла: " + ex.getMessage());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ошибка данных: " + ex.getMessage());
                }
            }
        }
    }
    
    private void updateInterface() {
        Container c = MainFrame.getC(); 
        c.removeAll(); 
        
        c.add(new NorthPanel(), BorderLayout.NORTH);
        c.add(new SouthPanel(), BorderLayout.SOUTH);
        c.add(new WestPanel(), BorderLayout.WEST);
        c.add(new EastPanel(), BorderLayout.EAST);
        c.add(new CenterPanel(), BorderLayout.CENTER);
        
        c.revalidate(); 
        c.repaint();
    }
}