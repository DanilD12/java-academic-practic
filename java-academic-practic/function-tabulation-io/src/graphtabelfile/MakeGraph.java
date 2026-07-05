/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphtabelfile;

import javax.swing.*;
import java.awt.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xy.XYDataset;

public class MakeGraph extends JPanel {
    public MakeGraph(double xMin, double xMax, int n) {
        XYSeries series1;
        XYDataset xyDataset1;
        String str = MyFunc.getMyFuncStr();
        
        series1 = new XYSeries(str);
        
        double dx = (xMax - xMin) / (n - 1);
        double x, y;
        
        for (int i = 0; i < n; i++) {
            x = xMin + dx * i;
            y = MyFunc.getMyFunc(x);
            series1.add(x, y);
        }
        
        xyDataset1 = new XYSeriesCollection(series1);
        
        JFreeChart chart = ChartFactory.createXYLineChart(
                str, "X", "Y",
                xyDataset1,
                PlotOrientation.VERTICAL,
                true, true, false
        );
        
        setLayout(new BorderLayout());
        add(new ChartPanel(chart));
    }
}