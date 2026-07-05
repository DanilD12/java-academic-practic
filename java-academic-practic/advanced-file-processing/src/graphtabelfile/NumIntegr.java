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

        for (int i = 1; i < n - 1; i++) { // от 1 до n-2 включительно (внутренние узлы)
            // Но в логике цикла по интервалам (как выше):
            // Узлы: 0, 1, ..., n-1. 
            // Формула: dx * ( (f(0)+f(n))/2 + sum(f(1)..f(n-1)) )
            // Реализуем в цикле по точкам:
             double x = xMin + dx * i;
             sum += MyFunc.getMyFunc(x);
        }
        // Добавляем края
        sum += (MyFunc.getMyFunc(xMin) + MyFunc.getMyFunc(xMax)) / 2.0;
        return sum * dx;
    }
}

// --- Метод Симпсона ---
class Integr_simp implements NumIntegr {
    public double integr(double xMin, double xMax, int n) {
        // n - количество точек. Для Симпсона количество интервалов (n-1) должно быть четным.
        // Если (n-1) нечетное, метод менее точен или требует адаптации, но реализуем классику.
        int intervals = n - 1;
        double h = (xMax - xMin) / intervals;
        double sum = MyFunc.getMyFunc(xMin) + MyFunc.getMyFunc(xMax);
        
        for (int i = 1; i < intervals; i++) {
            double x = xMin + h * i;
            if (i % 2 == 0) {
                sum += 2 * MyFunc.getMyFunc(x); // Четные индексы (x2, x4...) - коэф 2
            } else {
                sum += 4 * MyFunc.getMyFunc(x); // Нечетные индексы (x1, x3...) - коэф 4
            }
        }
        return sum * h / 3.0;
    }
}

// --- Метод 3/8 (Три восьмых) ---
class Integr_38 implements NumIntegr {
    public double integr(double xMin, double xMax, int n) {
        int intervals = n - 1;
        double h = (xMax - xMin) / intervals;
        double sum = MyFunc.getMyFunc(xMin) + MyFunc.getMyFunc(xMax);
        
        // Коэффициенты: 1, 3, 3, 2, 3, 3, 2 ... 1
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

// --- Метод Гаусса (1 точка / средняя точка) ---
class Integr_gauss1 implements NumIntegr {
    public double integr(double xMin, double xMax, int n) {
        // Составная формула Гаусса (применяем метод к каждому подотрезку)
        double dx = (xMax - xMin) / (n - 1);
        double integr = 0.;
        // Узлы: 0 (центр). Веса: 2 (но так как нормировка, по факту это метод средних)
        for (int i = 1; i < n; i++) {
            // Середина интервала
            double mid = xMin + dx * (i - 0.5); 
            integr += MyFunc.getMyFunc(mid);
        }
        // Умножаем на длину интервала (эквивалент веса 2 * dx/2)
        return integr * dx;
    }
}

// --- Метод Гаусса (2 точки) ---
class Integr_gauss2 implements NumIntegr {
    public double integr(double xMin, double xMax, int n) {
        double dx = (xMax - xMin) / (n - 1); // Длина одного подотрезка
        double integr = 0.;
        // Константа 1/sqrt(3)
        double t = 1.0 / Math.sqrt(3.0);
        
        for (int i = 1; i < n; i++) {
            double mid = xMin + dx * (i - 0.5); // Середина подотрезка
            double halfDx = dx / 2.0;
            
            // Точки внутри подотрезка
            double x1 = mid - t * halfDx;
            double x2 = mid + t * halfDx;
            
            // Веса w1=1, w2=1. Формула: (b-a)/2 * sum(w*f(x))
            // (b-a)/2 = halfDx
            integr += (MyFunc.getMyFunc(x1) + MyFunc.getMyFunc(x2));
        }
        return integr * (dx / 2.0);
    }
}

// --- Метод Гаусса (3 точки) ---
class Integr_gauss3 implements NumIntegr {
    public double integr(double xMin, double xMax, int n) {
        double dx = (xMax - xMin) / (n - 1);
        double integr = 0.;
        // Константа sqrt(3/5)
        double t = Math.sqrt(3.0 / 5.0);
        
        // Веса
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