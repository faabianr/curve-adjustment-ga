package uag.mcc.ai.ga.curve.model;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Comparator;

@Slf4j
public class Generation {

    private Chromosome[] chromosomes;

    public void setChromosomes(Chromosome[] chromosomes) {
        this.chromosomes = chromosomes;
    }

    public Chromosome getBestChromosome() {
        return Arrays.stream(chromosomes)
                .min(Comparator.comparing(Chromosome::getAptitude)).orElse(null);
    }

}
