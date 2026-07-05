/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphtabelfile;

public class Approxim2 implements MNK {
    
    private double sumX, sumX2, sumX3, sumX4;
    private double sumY, sumXY, sumX2Y;
    
    private void init(double xMin, double xMax, int n) {
        double dx = (xMax - xMin) / (n - 1);
        
        sumX = sumX2 = sumX3 = sumX4 = 0;
        sumY = sumXY = sumX2Y = 0;

        for (int i = 0; i < n; i++) {
            double x = xMin + dx * i;
            double y = MyFunc.getMyFunc(x);

            sumX  += x;
            sumX2 += x * x;
            sumX3 += x * x * x;
            sumX4 += x * x * x * x;

            sumY   += y;
            sumXY  += x * y;
            sumX2Y += x * x * y;
        }
    }

    private double det3(double[][] m) {
        return m[0][0] * m[1][1] * m[2][2] +
               m[0][1] * m[1][2] * m[2][0] +
               m[0][2] * m[1][0] * m[2][1] -
               m[0][2] * m[1][1] * m[2][0] -
               m[0][1] * m[1][0] * m[2][2] -
               m[0][0] * m[1][2] * m[2][1];
    }

    @Override
    public double[] mnk(double xMin, double xMax, int n) {
        init(xMin, xMax, n);
        
        // Система нормальных уравнений для параболы (a2*x^2 + a1*x + a0):
        // a0*n     + a1*SumX   + a2*SumX2 = SumY
        // a0*SumX  + a1*SumX2  + a2*SumX3 = SumXY
        // a0*SumX2 + a1*SumX3  + a2*SumX4 = SumX2Y
        
        // Главная матрица системы (D)
        double[][] matrixD = {
            { (double)n, sumX,  sumX2 },
            { sumX,      sumX2, sumX3 },
            { sumX2,     sumX3, sumX4 }
        };
        
        double[][] matrixD0 = {
            { sumY,   sumX,  sumX2 },
            { sumXY,  sumX2, sumX3 },
            { sumX2Y, sumX3, sumX4 }
        };
        
        double[][] matrixD1 = {
            { (double)n, sumY,   sumX2 },
            { sumX,      sumXY,  sumX3 },
            { sumX2,     sumX2Y, sumX4 }
        };
        
        double[][] matrixD2 = {
            { (double)n, sumX,  sumY   },
            { sumX,      sumX2, sumXY  },
            { sumX2,     sumX3, sumX2Y }
        };
        
        // Вычисляем определители
        double D  = det3(matrixD);
        double D0 = det3(matrixD0);
        double D1 = det3(matrixD1);
        double D2 = det3(matrixD2);
        
        // Находим коэффициенты по Крамеру
        double a0 = D0 / D;
        double a1 = D1 / D;
        double a2 = D2 / D;
        
        return new double[] { a0, a1, a2 };
    }
}