/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphtabelfile;

interface NumIntegr {
    double integr(double xMin, double xMax, int n);
}

class Integr_cr implements NumIntegr {
    public double integr(double xMin, double xMax, int n) {
        double dx = (xMax - xMin) / (n - 1);
        double x, integr = 0.;
        for (int i = 1; i < n; i++) {
            x = xMin + dx * (i - 0.5);
            integr += MyFunc.getMyFunc(x);
        }
        return integr * dx;
    }
}

class Integr_lt implements NumIntegr {
    public double integr(double xMin, double xMax, int n) {
        double dx = (xMax - xMin) / (n - 1);
        double x, integr = 0.;
        for (int i = 1; i < n; i++) {
            
            x = xMin + dx * (i - 1);
            integr += MyFunc.getMyFunc(x);
        }
        return integr * dx;
    }
}

class Integr_rt implements NumIntegr {
    public double integr(double xMin, double xMax, int n) {
        double dx = (xMax - xMin) / (n - 1);
        double x, integr = 0.;
        for (int i = 1; i < n; i++) {
            x = xMin + dx * i;
            integr += MyFunc.getMyFunc(x);
        }
        return integr * dx;
    }
}

class Integr_tr implements NumIntegr {
    public double integr(double xMin, double xMax, int n) {
        double dx = (xMax - xMin) / (n - 1);
        double sum = 0.;

        for (int i = 1; i < n - 1; i++) {
            
             double x = xMin + dx * i;
             sum += MyFunc.getMyFunc(x);
        }
        sum += (MyFunc.getMyFunc(xMin) + MyFunc.getMyFunc(xMax)) / 2.0;
        return sum * dx;
    }
}


class Integr_simp implements NumIntegr {
    public double integr(double xMin, double xMax, int n) {
       
        int intervals = n - 1;
        double h = (xMax - xMin) / intervals;
        double sum = MyFunc.getMyFunc(xMin) + MyFunc.getMyFunc(xMax);
        
        for (int i = 1; i < intervals; i++) {
            double x = xMin + h * i;
            if (i % 2 == 0) {
                sum += 2 * MyFunc.getMyFunc(x); 
            } else {
                sum += 4 * MyFunc.getMyFunc(x);
            }
        }
        return sum * h / 3.0;
    }
}

class Integr_38 implements NumIntegr {
    public double integr(double xMin, double xMax, int n) {
        int intervals = n - 1;
        double h = (xMax - xMin) / intervals;
        double sum = MyFunc.getMyFunc(xMin) + MyFunc.getMyFunc(xMax);
        
        for (int i = 1; i < intervals; i++) {
            double x = xMin + h * i;
            if (i % 3 == 0) {
                sum += 2 * MyFunc.getMyFunc(x); // Кратные 3
            } else {
                sum += 3 * MyFunc.getMyFunc(x); // Остальные
            }
        }
        return sum * 3.0 * h / 8.0;
    }
}

class Integr_gauss1 implements NumIntegr {
    public double integr(double xMin, double xMax, int n) {
       
        double dx = (xMax - xMin) / (n - 1);
        double integr = 0.;
        
        for (int i = 1; i < n; i++) {
            // Середина интервала
            double mid = xMin + dx * (i - 0.5); 
            integr += MyFunc.getMyFunc(mid);
        }
        
        return integr * dx;
    }
}

class Integr_gauss2 implements NumIntegr {
    public double integr(double xMin, double xMax, int n) {
        double dx = (xMax - xMin) / (n - 1); // Длина одного подотрезка
        double integr = 0.;
        // Константа 1/sqrt(3)
        double t = 1.0 / Math.sqrt(3.0);
        
        for (int i = 1; i < n; i++) {
            double mid = xMin + dx * (i - 0.5); // Середина подотрезка
            double halfDx = dx / 2.0;
            
            double x1 = mid - t * halfDx;
            double x2 = mid + t * halfDx;
            
            integr += (MyFunc.getMyFunc(x1) + MyFunc.getMyFunc(x2));
        }
        return integr * (dx / 2.0);
    }
}

class Integr_gauss3 implements NumIntegr {
    public double integr(double xMin, double xMax, int n) {
        double dx = (xMax - xMin) / (n - 1);
        double integr = 0.;
        // Константа sqrt(3/5)
        double t = Math.sqrt(3.0 / 5.0);
        
        double w1 = 5.0 / 9.0;
        double w2 = 8.0 / 9.0;
        double w3 = 5.0 / 9.0;
        
        for (int i = 1; i < n; i++) {
            double mid = xMin + dx * (i - 0.5);
            double halfDx = dx / 2.0;
            
            double x1 = mid - t * halfDx;
            double x2 = mid; // t=0
            double x3 = mid + t * halfDx;
            
            integr += w1 * MyFunc.getMyFunc(x1) + 
                      w2 * MyFunc.getMyFunc(x2) + 
                      w3 * MyFunc.getMyFunc(x3);
        }
        return integr * (dx / 2.0);
    }
}