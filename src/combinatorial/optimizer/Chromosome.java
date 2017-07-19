package combinatorial.optimizer;

import java.util.List;

public abstract class Chromosome {
    protected List<Gene> genes;
    protected Population population;

    abstract public Chromosome generate();
    abstract public int getFitness();
    abstract public int getOptimumFitness();
    abstract public Gene generateRandomGene();

    public Chromosome crossover(Chromosome pair, double uniformRate) {
        Chromosome crossed = this.generate();
        for (int i = 0; i < crossed.size() && i < pair.size(); i++) {
            if (Math.random() <= uniformRate) {
                crossed.setGene(i, this.getGene(i));
            } else {
                crossed.setGene(i, pair.getGene(i));
            }
        }
        return crossed;
    }

    public int size() {
        return genes.size();
    }

    public Gene getGene(int index) {
        return genes.get(index);
    }

    public void setGene(int index, Gene gene) {
        genes.set(index, gene);
    }
}
