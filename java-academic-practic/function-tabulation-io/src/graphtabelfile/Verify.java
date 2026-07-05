/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphtabelfile;

import javax.swing.JOptionPane;

public class Verify {
    public static Double xMin = 0.0;
    public static Double xMax = 10.0;
    public static Integer n = 100;

    public static int getVerify() {
        String txt1 = WestPanel.getText1();
        String txt2 = WestPanel.getText2();
        String txt3 = WestPanel.getText3();
        String str = "";

        try {
            xMin = Double.valueOf(txt1);
        } catch (NumberFormatException e) {
            str = "X мин не является числом";
            JOptionPane.showMessageDialog(null, str);
            return -1;
        }

        try {
            xMax = Double.valueOf(txt2);
        } catch (NumberFormatException e) {
            str = "X макс не является числом";
            JOptionPane.showMessageDialog(null, str);
            return -1;
        }

        try {
            n = Integer.valueOf(txt3);
        } catch (NumberFormatException e) {
            str = "n не является числом";
            JOptionPane.showMessageDialog(null, str);
            return -1;
        }

        if (n < 2 || n > 1000) {
            str = "допустимые значения n от 2 до 1000";
            JOptionPane.showMessageDialog(null, str);
            return -1;
        }

        if ((xMax - xMin) <= 0.) {
            str = "Не допустимые значения: Xmax-Xmin <= 0";
            JOptionPane.showMessageDialog(null, str);
            return -1;
        }

        return 0;
    }
}