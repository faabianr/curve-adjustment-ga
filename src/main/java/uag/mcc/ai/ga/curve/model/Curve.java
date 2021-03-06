package uag.mcc.ai.ga.curve.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Curve {

    private List<Double> xValues;
    private List<Double> yValues;

    public Curve() {
        xValues = new ArrayList<>();
        yValues = new ArrayList<>();
    }

    public void addPoint(double x, double y) {
        xValues.add(x);
        yValues.add(y);
    }

}
