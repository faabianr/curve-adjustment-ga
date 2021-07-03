package uag.mcc.ai.ga.curve;

import uag.mcc.ai.ga.curve.model.Chromosome;
import uag.mcc.ai.ga.curve.service.ChartService;

public class Application {

    public static void main(String[] args) {

        ChartService chartService = new ChartService();

        Chromosome c = new Chromosome(
                8 * Chromosome.WEIGHT,
                25 * Chromosome.WEIGHT,
                4 * Chromosome.WEIGHT,
                45 * Chromosome.WEIGHT,
                10 * Chromosome.WEIGHT,
                17 * Chromosome.WEIGHT,
                35 * Chromosome.WEIGHT
        );

        chartService.displayChart(c);


    }

}
