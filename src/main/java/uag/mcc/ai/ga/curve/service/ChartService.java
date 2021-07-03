package uag.mcc.ai.ga.curve.service;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.lines.SeriesLines;
import uag.mcc.ai.ga.curve.model.Chromosome;

import java.awt.*;

public class ChartService {

    private SwingWrapper<XYChart> swingWrapper;

    public void displayChart(Chromosome c) {

        if (swingWrapper == null) {
            XYChart chart = new XYChartBuilder().theme(Styler.ChartTheme.Matlab)
                    .width(1200).height(600).title("Curve Adjustment").xAxisTitle("x").yAxisTitle("y").build();

            chart.addSeries("curve", c.getCurve().getXValues(), c.getCurve().getYValues(), null);

            chart.getStyler().setChartTitleFont(new Font("Verdana", Font.PLAIN, 12));
            chart.getStyler().setSeriesLines(new BasicStroke[]{SeriesLines.SOLID});
            chart.getStyler().setPlotBorderVisible(false);

            swingWrapper = new SwingWrapper<>(chart);
            swingWrapper.displayChart();
        } else {
            swingWrapper.getXChartPanel().getChart().updateXYSeries("curve", c.getCurve().getXValues(), c.getCurve().getYValues(), null);
            swingWrapper.getXChartPanel().revalidate();
            swingWrapper.getXChartPanel().repaint();
        }

    }

}
