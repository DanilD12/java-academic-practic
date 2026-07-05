/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package solveLineSystems;

public class LU implements SolveSystems {

    @Override
    public double[] getSolveSystem(double[][] a, double[] b, int n) {
        double[][] U = new double[n][n];
        double[][] L = new double[n][n];
        double[] Y = new double[n];
        double[] X = new double[n];
        
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                double s = 0;
                for (int k = 0; k < i; k++) {
                    s += L[j][k] * U[k][i];
                }
                L[j][i] = a[j][i] - s;
            }
            
            for (int j = i + 1; j < n; j++) {
                double s = 0;
                for(int k = 0; k < i; k++) {
                    s += L[i][k] * U[k][j];
                }
                U[i][j] = (a[i][j] - s) / L[i][i];
            }
            U[i][i] = 1;
        }

        for (int i = 0; i < n; i++) {
            double s = 0;
            for (int k = 0; k < i; k++) {
                s += L[i][k] * Y[k];
            }
            Y[i] = (b[i] - s) / L[i][i];
        }

        for (int i = n - 1; i >= 0; i--) {
            double s = 0;
            for (int k = i + 1; k < n; k++) {
                s += U[i][k] * X[k];
            }
            X[i] = Y[i] - s;
        }

        return X;
    }
}