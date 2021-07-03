package uag.mcc.ai.ga.curve.service;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.lines.SeriesLines;
import uag.mcc.ai.ga.curve.model.Chromosome;

import java.awt.*;

public class ChartService {

    private static final String CHART_SERIES_NAME_FOR_REFERENCE = "reference";
    private static final String CHART_SERIES_NAME_FOR_APPROXIMATION = "approximation";

    private SwingWrapper<XYChart> swingWrapper;

    public void displayChart(Chromosome referenceChromosome, Chromosome approximationChromosome) {

        if (swingWrapper == null) {
            XYChart chart = new XYChartBuilder().theme(Styler.ChartTheme.Matlab)
                    .width(1200).height(600).title("Curve Adjustment").xAxisTitle("x").yAxisTitle("y").build();

            chart.addSeries(CHART_SERIES_NAME_FOR_REFERENCE, referenceChromosome.getCurve().getXValues(), referenceChromosome.getCurve().getYValues(), null);
            chart.addSeries(CHART_SERIES_NAME_FOR_APPROXIMATION, approximationChromosome.getCurve().getXValues(), approximationChromosome.getCurve().getYValues(), null);

            chart.getStyler().setChartTitleFont(new Font("Verdana", Font.PLAIN, 12));
            chart.getStyler().setSeriesLines(new BasicStroke[]{SeriesLines.SOLID});
            chart.getStyler().setPlotBorderVisible(false);

            swingWrapper = new SwingWrapper<>(chart);
            swingWrapper.displayChart();
        } else {
            swingWrapper.getXChartPanel().getChart().updateXYSeries(CHART_SERIES_NAME_FOR_APPROXIMATION, approximationChromosome.getCurve().getXValues(), approximationChromosome.getCurve().getYValues(), null);
            swingWrapper.getXChartPanel().revalidate();
            swingWrapper.getXChartPanel().repaint();
        }

    }

}
