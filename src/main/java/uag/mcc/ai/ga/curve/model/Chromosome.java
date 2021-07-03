package uag.mcc.ai.ga.curve.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Data
public class Chromosome {

    public static final int WEIGHT = 5;

    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;

    public double calculateX(int i) {
        return i / 10.0;
    }

    private int applyWeight(int n) {
        return n / WEIGHT;
    }

    public double calculateY(int i) {
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
