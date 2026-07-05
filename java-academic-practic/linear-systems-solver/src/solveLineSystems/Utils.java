/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package solveLineSystems;

public class Utils {

    private static double getkoeff(int n) {
        return Math.pow(n, 3); 
    }
    public static double[][] getMatrixA(int n) {
        double[][] a = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = 100. * Math.random() + 10.;
                
                if (i == j) {
                    a[i][j] = a[i][j] + 100. * n;
                }
            }
        }
        return a;
    }

    public static double[] getMatrixB(int n) {
        double[] b = new double[n];
        for (int i = 0; i < n; i++) {
            b[i] = 100. * Math.random() + 10.;
        }
        return b;
    }

    public static double[][] getMatrixAc(double[][] a, int n) {
       
        return Matrix.getSimmetrMatrix(a, n);
    }

    public static double[] getMatrixBc(double[][] a, double[] b, int n) {
        double[] bc = new double[n];

        for (int i = 0; i < n; i++) {
            double s = 0;
            for (int j = 0; j < n; j++) {
                s += a[j][i] * b[j]; 
            }
            bc[i] = s;
        }
        return bc;
    }

    public static double[] getMatrixNevjazka(double[][] a, double[] b, double[] x, int n) {
        double[] nev = new double[n];
        for (int i = 0; i < n; i++) {
            double s = 0;
            for (int j = 0; j < n; j++) {
                s += a[i][j] * x[j];
            }
            nev[i] = Math.abs(s - b[i]);
        }
        return nev;
    }
}