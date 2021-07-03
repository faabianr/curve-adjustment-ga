package uag.mcc.ai.ga.curve.model;

import lombok.ToString;

@ToString
public class Chromosome {

    public static final int WEIGHT = 5;

    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private final int f;
    private final int g;
    private Curve curve;

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

    public void generateCurvePoints() {
        curve = new Curve();
        for (int i = 0; i < 1000; i++) {
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
