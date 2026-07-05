/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package solveLineSystems;

public class Matrix {

    public static double[][] getTranspMatrix(double[][] a, int n) {
        double[][] at = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                at[j][i] = a[i][j];
            }
        }
        return at;
    }

    public static double[][] getMultMatrix(double[][] a, double[][] b, int n) {
        double[][] ab = new double[n][n];
        double t;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                t = 0;
                for (int j = 0; j < n; j++) {
                    t += a[i][j] * b[j][k];
                }
                ab[i][k] = t;
            }
        }
        return ab;
    }

    public static double[][] getSimmetrMatrix(double[][] a, int n) {
        double[][] at = getTranspMatrix(a, n);

        double[][] sim = getMultMatrix(at, a, n); 
        return sim;
    }
}
