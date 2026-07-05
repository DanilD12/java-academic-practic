/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphtabelfile;

public class Verify {
    public static double xMin = 0.0;
    public static double xMax = 5.0;
    public static int n = 10;

    // Метод для совместимости с NorthPanel
    public static int getVerify() {
        // Простая проверка: точек > 2 и min < max
        if (n <= 2) return 1; 
        if (xMin >= xMax) return 1;
        return 0; // Всё хорошо
    }
}