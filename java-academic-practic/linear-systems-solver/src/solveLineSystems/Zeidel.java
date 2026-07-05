/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package solveLineSystems;

import javax.swing.JOptionPane;

public class Zeidel implements SolveSystems {
    private int nMax = 1000; 
    private double eMax = 1.E-02; 
    private int ni = 0; 

    public Zeidel(int nMax, double eMax) {
        this.nMax = nMax;
        this.eMax = eMax;
    }

    public int getNi() { return ni; }
    public void setNi(int ni) { this.ni = ni; }

    @Override
    public double[] getSolveSystem(double[][] a, double[] b, int n) {
        double[] x = new double[n];
        double eps, s1, s2;
        ni = 0;

        do {
            eps = 0.;
            for (int i = 0; i < n; i++) {
                s1 = 0.;
                s2 = 0.;
                
                for (int k = 0; k < i; k++) {
                    s1 += a[i][k] * x[k];
                }
                for (int k = i + 1; k < n; k++) {
                    s2 += a[i][k] * x[k];
                }
                
                try {

                    double x_new = (b[i] - s1 - s2) / a[i][i];
                    
                    double diff = Math.abs(x_new - x[i]);
                    if (diff > eps) eps = diff;
                    
                    x[i] = x_new;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
                }
            }
            ni++;
        } while ((ni < nMax) && (eps > eMax));

        return x;
    }
}