/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphtabelfile;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class MakeGraph extends JPanel {

    public MakeGraph(double xMin, double xMax, int n) {

        XYSeries series1 = new XYSeries("Исходные точки");
        XYSeries series2 = new XYSeries("Аппроксимация");
        
        XYSeriesCollection xyDataset = new XYSeriesCollection();
        
        double dx = (xMax - xMin) / (n - 1);
        for (int i = 0; i < n; i++) {
            double x = xMin + dx * i;
            double y = MyFunc.getMyFunc(x);
            series1.add(x, y);
        }

        Approxim2 apr = new Approxim2();
        double[] a = apr.mnk(xMin, xMax, n);
        double r2_val = R2.r2(xMin, xMax, n);
        
        for (int i = 0; i < n - 1; i++) {
            double x_start = xMin + dx * i;
            for (int j = 0; j < 10; j++) {
                double x_smooth = x_start + j * (dx / 10.0);
                double y_smooth = MyFunc.getApproxFunc(x_smooth, a);
                series2.add(x_smooth, y_smooth);
            }
        }
        series2.add(xMax, MyFunc.getApproxFunc(xMax, a));

        xyDataset.addSeries(series1);
        xyDataset.addSeries(series2);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesPaint(0, Color.RED); // Красные точки
        
        renderer.setSeriesLinesVisible(1, true);
        renderer.setSeriesShapesVisible(1, false);
        renderer.setSeriesPaint(1, Color.BLUE); // Синяя линия
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));

        String str = String.format("Yappr=%1.4f x^2+%1.4f x+%1.4f ; R2=%1.4f", 
                                   a[2], a[1], a[0], r2_val);

        JFreeChart chart = ChartFactory.createXYLineChart(
                str,                        
                "X", "Y",                   
                xyDataset,                  
                PlotOrientation.VERTICAL,
                true, true, false
        );
        

        chart.getTitle().setFont(new Font("SansSerif", Font.PLAIN, 16));
        XYPlot plot = chart.getXYPlot();
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinePaint(Color.gray);
        plot.setDomainGridlinePaint(Color.gray);

        setLayout(new BorderLayout());
        add(new ChartPanel(chart));
    }
}