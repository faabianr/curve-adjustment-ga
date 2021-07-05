package uag.mcc.ai.ga.curve.utils;

public class BitUtils {

    private BitUtils() {
    }



    public static void main(String[] args) {

        int pa1 = 160;
        int pb1 = 10;
        int pa2 = 80;
        int pb2 = 5;

        int child1 = pa1 | pb2;
        int child2 = pa2 | pb1;

        System.out.println("child1: " + child1 + " (" + Integer.toBinaryString(child1) + ")");
        System.out.println("child2: " + child2 + " (" + Integer.toBinaryString(child2) + ")");

        for (int i = 0; i < 10; i++) {
            int n = RandomizeUtils.randomNumber(256);
            String resultWithPadding = String.format("%8s", Integer.toBinaryString(n)).replaceAll(" ", "0");
            System.out.println("result with padding " + resultWithPadding);
        }


    }

}
