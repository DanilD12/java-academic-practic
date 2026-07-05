/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphtabelfile;

public class MyFunc {

    public static double getMyFunc(double x) {
        return 2 * x * x - 10 * x + 5 * Math.sin(x);
    }

    public static String getMyFuncStr() {
        return "2x^2-10x+5sin(x)";
    }

    public static double getApproxFunc(double x, double[] a) {
        return a[2] * x * x + a[1] * x + a[0];
    }

    public static String getApproxFuncStr() {
        return "a2x^2+a1x+a0";
    }
}