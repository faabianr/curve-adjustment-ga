package uag.mcc.ai.ga.curve.model;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Generation {

    private Chromosome[] chromosomes;
    private Chromosome bestChromosome;

    public void setChromosomes(Chromosome[] chromosomes) {
        this.chromosomes = chromosomes;
    }

    public Chromosome[] getChromosomes() {
        return chromosomes;
    }

    public void setBestChromosome(Chromosome bestChromosome) {
        this.bestChromosome = bestChromosome;
    }

    public Chromosome getBestChromosome() {
        return bestChromosome;
    }

}
