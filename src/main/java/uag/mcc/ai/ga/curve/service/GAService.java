package uag.mcc.ai.ga.curve.service;

import lombok.extern.slf4j.Slf4j;
import uag.mcc.ai.ga.curve.model.Chromosome;
import uag.mcc.ai.ga.curve.model.Generation;
import uag.mcc.ai.ga.curve.utils.RandomizeUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class GAService {

    private static final int TOTAL_CHROMOSOMES_PER_GENERATION = 100;
    private static final int TOTAL_GENERATIONS = 100;
    private static final int TOTAL_PARTICIPANTS_PER_TOURNAMENT = 5;

    private final ChartService chartService;
    private final Chromosome referenceChromosome;
    private Generation currentGeneration;
    private int generationCount;

    public GAService(ChartService chartService) {
        this.chartService = chartService;

        referenceChromosome = new Chromosome(
                8 * Chromosome.WEIGHT,
                25 * Chromosome.WEIGHT,
                4 * Chromosome.WEIGHT,
                45 * Chromosome.WEIGHT,
                10 * Chromosome.WEIGHT,
                17 * Chromosome.WEIGHT,
                35 * Chromosome.WEIGHT
        );

        createInitialGeneration();
    }

    public void startSimulation() {

        for (int i = 0; i < TOTAL_GENERATIONS; i++) {
            generationCount = i;
            startTournamentsForCurrentGeneration();

            sleep();
            chartService.displayCharts(referenceChromosome, currentGeneration.getBestChromosome(), generationCount);
        }

        chartService.updateCurveChartWithBestOfGenerations();

    }

    private void startTournamentsForCurrentGeneration() {
        Chromosome[] newGenerationChromosomes = new Chromosome[TOTAL_CHROMOSOMES_PER_GENERATION];

        int childrenIndex = 0;

        for (int i = 0; i < TOTAL_CHROMOSOMES_PER_GENERATION / 2; i++) {
            log.debug("Picking {} participants for tournament #{}", TOTAL_PARTICIPANTS_PER_TOURNAMENT, i);

            // tournaments
            Chromosome parent1 = executeTournamentOnCurrentGeneration();
            Chromosome parent2 = executeTournamentOnCurrentGeneration();

            // reproduction
            Chromosome[] children = applyReproduction(parent1, parent2);
            newGenerationChromosomes[childrenIndex] = children[0];
            childrenIndex++;
            newGenerationChromosomes[childrenIndex] = children[1];
            childrenIndex++;
        }

        log.debug("replacing parent generation with children");
        currentGeneration.setChromosomes(newGenerationChromosomes);
        setBestOfGeneration(currentGeneration);
    }

    private Chromosome[] applyReproduction(Chromosome parent1, Chromosome parent2) {
        // TODO implement
        return new Chromosome[2];
    }

    private Chromosome executeTournamentOnCurrentGeneration() {
        Set<Chromosome> participants = pickRandomParticipants(currentGeneration.getChromosomes());
        return findBestParticipant(participants);
    }

    private void setBestOfGeneration(Generation generation) {
        Set<Chromosome> generationChromosomes = new HashSet<>();
        Collections.addAll(generationChromosomes, generation.getChromosomes());
        Chromosome bestOfGeneration = findBestParticipant(generationChromosomes);
        currentGeneration.setBestChromosome(bestOfGeneration);
    }

    private Chromosome findBestParticipant(Set<Chromosome> chromosomes) {
        return chromosomes.stream()
                .min(Comparator.comparing(Chromosome::getAptitude)).orElse(null);
    }

    private Set<Chromosome> pickRandomParticipants(Chromosome[] chromosomes) {
        Set<Chromosome> participants = new HashSet<>();

        while (participants.size() < TOTAL_PARTICIPANTS_PER_TOURNAMENT) {
            Chromosome participant = chromosomes[RandomizeUtils.randomNumber(TOTAL_CHROMOSOMES_PER_GENERATION)];
            participants.add(participant);
        }

        return participants;
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
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
