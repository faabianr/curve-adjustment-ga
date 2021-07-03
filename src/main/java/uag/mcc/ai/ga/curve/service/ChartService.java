package uag.mcc.ai.ga.curve.service;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.lines.SeriesLines;
import uag.mcc.ai.ga.curve.model.Chromosome;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChartService {

    private SwingWrapper<XYChart> swingWrapper;

    public void displayChart(Chromosome c) {

        List<Double> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            xValues.add(c.calculateX(i));
            yValues.add(c.calculateY(i));
        }

        if (swingWrapper == null) {
            XYChart chart = new XYChartBuilder().theme(Styler.ChartTheme.Matlab)
                    .width(1200).height(600).title("Curve Adjustment").xAxisTitle("x").yAxisTitle("y").build();

            chart.addSeries("curve", xValues, yValues, null);

            chart.getStyler().setChartTitleFont(new Font("Verdana", Font.PLAIN, 12));
            chart.getStyler().setSeriesLines(new BasicStroke[]{SeriesLines.SOLID});
            chart.getStyler().setPlotBorderVisible(false);

            swingWrapper = new SwingWrapper<>(chart);
            swingWrapper.displayChart();
        } else {
            swingWrapper.getXChartPanel().getChart().updateXYSeries("curve", xValues, yValues, null);
            swingWrapper.getXChartPanel().revalidate();
            swingWrapper.getXChartPanel().repaint();
        }

    }

}
