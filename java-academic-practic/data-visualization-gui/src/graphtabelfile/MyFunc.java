/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphtabelfile;

public class MyFunc {

    public static double getMyFunc(double x) {
        return Math.sin(x);
    }

    public static String getMyFuncStr() {
        return "sin(x)";
    }

    public static double getMyIntegr(double xMin, double xMax) {
        double y1 = -Math.cos(xMin);
        double y2 = -Math.cos(xMax);
        return y2 - y1;
    }

    public static String[] getMethodsIntegr(int i, int n, double xMin, double xMax) {
        String[] method = new String[2];
        switch (i) {
            case 0:
                method[0] = "Аналитика";
                method[1] = Double.toString(getMyIntegr(xMin, xMax));
                break;
            case 1:
                method[0] = "Сред.т.прямоуг.";
                method[1] = Double.toString(new Integr_cr().integr(xMin, xMax, n));
                break;
            case 2:
                method[0] = "Лев.т.прямоуг.";
                method[1] = Double.toString(new Integr_lt().integr(xMin, xMax, n));
                break;
            case 3:
                method[0] = "Прав.т.прямоуг.";
                method[1] = Double.toString(new Integr_rt().integr(xMin, xMax, n));
                break;
            case 4:
                method[0] = "Трапеций";
                method[1] = Double.toString(new Integr_tr().integr(xMin, xMax, n));
                break;
            case 5:
                method[0] = "Симпсона";
                method[1] = Double.toString(new Integr_simp().integr(xMin, xMax, n));
                break;
            case 6:
                method[0] = "3/8-трех восьмых";
                method[1] = Double.toString(new Integr_38().integr(xMin, xMax, n));
                break;
            case 7:
                method[0] = "Гаусса, одна точка";
                method[1] = Double.toString(new Integr_gauss1().integr(xMin, xMax, n));
                break;
            case 8:
                method[0] = "Гаусса, две точки";
                method[1] = Double.toString(new Integr_gauss2().integr(xMin, xMax, n));
                break;
            case 9:
                method[0] = "Гаусса, три точки";
                method[1] = Double.toString(new Integr_gauss3().integr(xMin, xMax, n));
                break;
            default:
                method[0] = "Метод не задан";
                method[1] = "0";
        }
        return method;
    }
}