package uag.mcc.ai.ga.curve.model;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@ToString
@Slf4j
public class Chromosome {

    public static final int WEIGHT = 5;
    public static final int TOTAL_CURVE_POINTS = 1000;

    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private final int f;
    private final int g;
    @ToString.Exclude
    private Curve curve;
    private double aptitude;

    public Chromosome(int a, int b, int c, int d, int e, int f, int g) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;

        generateCurvePoints();
    }

    public Curve getCurve() {
        return curve;
    }

    public double getAptitude() {
        return aptitude;
    }

    public void calculateAptitude(Curve referenceCurve) {
        for (int i = 0; i < TOTAL_CURVE_POINTS; i++) {
            double x1 = referenceCurve.getXValues().get(i);
            double y1 = referenceCurve.getYValues().get(i);

            double x2 = curve.getXValues().get(i);
            double y2 = curve.getYValues().get(i);

            aptitude += Math.abs(calculateDistanceBetweenPoints(x1, y1, x2, y2));
        }
    }

    private double calculateDistanceBetweenPoints(double x1, double y1, double x2, double y2) {
        double x = Math.abs(x2 - x1);
        double y = Math.abs(y2 - y1);
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public void generateCurvePoints() {
        curve = new Curve();
        for (int i = 0; i < TOTAL_CURVE_POINTS; i++) {
            curve.addPoint(calculateX(i), calculateY(i));
        }
    }

    private double calculateX(int i) {
        return i / 10.0;
    }

    private int applyWeight(int n) {
        return n / WEIGHT;
    }

    private double calculateY(int i) {
        double Xi = calculateX(i);
        int A = applyWeight(a);
        int B = applyWeight(b);
        int C = applyWeight(c);
        int D = applyWeight(d);
        int E = applyWeight(e);
        int F = applyWeight(f);
        int G = applyWeight(g);

        return A * (B * Math.sin(Xi / C) + D * Math.cos(Xi / E)) + F * Xi - G;
    }

}
