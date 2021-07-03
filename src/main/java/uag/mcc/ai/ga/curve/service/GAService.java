package uag.mcc.ai.ga.curve.service;

import lombok.extern.slf4j.Slf4j;
import uag.mcc.ai.ga.curve.model.Chromosome;
import uag.mcc.ai.ga.curve.model.Generation;
import uag.mcc.ai.ga.curve.utils.RandomizeUtils;

@Slf4j
public class GAService {

    private static final int TOTAL_GENERATIONS = 100;
    private static final int TOTAL_CHROMOSOMES_PER_GENERATION = 100;
    private static final int TOURNAMENT_PARTICIPANTS_NUMBER = 5;

    private final ChartService chartService;
    private final Chromosome referenceChromosome;
    private Generation currentGeneration;
    private final int generationCount;

    public GAService(ChartService chartService) {
        this.chartService = chartService;
        generationCount = 1;

        referenceChromosome = new Chromosome(
                8 * Chromosome.WEIGHT,
                25 * Chromosome.WEIGHT,
                4 * Chromosome.WEIGHT,
                45 * Chromosome.WEIGHT,
                10 * Chromosome.WEIGHT,
                17 * Chromosome.WEIGHT,
                35 * Chromosome.WEIGHT
        );
    }

    public void startSimulation() {
        createInitialGeneration();
        chartService.displayChart(referenceChromosome, currentGeneration.getBestChromosome());

        sleep();
        chartService.displayChart(referenceChromosome, referenceChromosome);
    }

    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.error("error in thread sleep", e);
        }
    }

    private void createInitialGeneration() {
        Chromosome[] chromosomes = new Chromosome[TOTAL_CHROMOSOMES_PER_GENERATION];
        for (int i = 0; i < TOTAL_CHROMOSOMES_PER_GENERATION; i++) {
            // creating the chromosome with random values
            chromosomes[i] = new Chromosome(
                    RandomizeUtils.randomNumberBetweenInclusiveRange(5, 255),
                    RandomizeUtils.randomNumberBetweenInclusiveRange(5, 255),
                    RandomizeUtils.randomNumberBetweenInclusiveRange(5, 255),
                    RandomizeUtils.randomNumberBetweenInclusiveRange(5, 255),
                    RandomizeUtils.randomNumberBetweenInclusiveRange(5, 255),
                    RandomizeUtils.randomNumberBetweenInclusiveRange(5, 255),
                    RandomizeUtils.randomNumberBetweenInclusiveRange(5, 255)
            );

            // calculating aptitude value using the curve of the reference chromosome
            chromosomes[i].calculateAptitude(referenceChromosome.getCurve());

            log.info("[generation {} - chromosome {}] aptitude={}", generationCount, i, chromosomes[i].getAptitude());
        }

        // adding the chromosomes to the generation
        currentGeneration = new Generation();
        currentGeneration.setChromosomes(chromosomes);

        log.info("[generation {}] best chromosome: {}", generationCount, currentGeneration.getBestChromosome());
    }

}
