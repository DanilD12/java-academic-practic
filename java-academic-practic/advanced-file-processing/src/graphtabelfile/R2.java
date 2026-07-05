/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphtabelfile;

public class R2 {
    public static double r2(double xMin, double xMax, int n) {
        double dx = (xMax - xMin) / (n - 1);
        double x, y, y_mean, SS_res, SS_tot;
        
        double sum_y = 0.0;
        for (int i = 0; i < n; i++) {
            x = xMin + dx * i;
            y = MyFunc.getMyFunc(x);
            sum_y += y;
        }
        y_mean = sum_y / n;
        
        Approxim2 apr = new Approxim2();
        double[] a = apr.mnk(xMin, xMax, n);
        
        SS_res = 0.0;
        SS_tot = 0.0; 

        for (int i = 0; i < n; i++) {
            x = xMin + dx * i;
            y = MyFunc.getMyFunc(x);
            double y_pred = MyFunc.getApproxFunc(x, a);
            
            SS_res += Math.pow(y - y_pred, 2);
            SS_tot += Math.pow(y - y_mean, 2);
        }

        // Формула R^2
        if (SS_tot == 0) return 1.0; // Если все точки лежат на одной горизонтальной прямой
        
        return 1 - (SS_res / SS_tot);
    }
}