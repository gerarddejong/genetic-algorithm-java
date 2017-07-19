// From: http://www.theprojectspot.com/tutorial-post/creating-a-genetic-algorithm-for-beginners/3

package combinatorial.optimizer;

import java.lang.reflect.Constructor;

public class Population {
    private Chromosome[] individuals;
    public Class<? extends Chromosome> species;
    private int bestPreviousFitness = 0;
    private int interationsWithoutImprovement = 0;
    private int tolleratedInterationsWithoutImprovement = 10;

    public Population(int populationSize, Class<? extends Chromosome> species) {
        this.individuals = new Chromosome[populationSize];
        this.species = species;

        for (int i = 0; i < size(); i++) {
            try {
                Constructor<Chromosome> constructor = (Constructor<Chromosome>) this.species.getConstructor();
                Chromosome chromosome = constructor.newInstance();
                chromosome.population = this; // create a reference in the Chromosome back to it's population
                saveIndividual(i, chromosome);
            }
            catch (Exception e) {
                System.err.println("Unknown Chromosome object type: " + e);
            }
        }
    }

    public Chromosome getChromosome(int index) {
        return individuals[index];
    }

    public Chromosome getFittest() {
        Chromosome fittest = individuals[0];
        for (int i = 0; i < size(); i++) {
            if (fittest.getFitness() <= (getChromosome(i)).getFitness()) {
                fittest = getChromosome(i);
            }
        }
        return fittest;
    }

    public boolean hasReachedTerminationState() {
        int fittest =  getFittest().getFitness();
        int optimumFitness = getFittest().getOptimumFitness();

        if (this.bestPreviousFitness <= fittest) {
            this.bestPreviousFitness = fittest;
            interationsWithoutImprovement = 0;
        }
        else {
            interationsWithoutImprovement++;
            System.out.println("interationsWithoutImprovement: " + interationsWithoutImprovement);
        }

        if(fittest == optimumFitness) {
            return true;
        }
        else {
            return interationsWithoutImprovement > tolleratedInterationsWithoutImprovement;
        }
    }

    public int size() {
        return individuals.length;
    }

    public void saveIndividual(int index, Chromosome indiv) {
        individuals[index] = indiv;
    }
}
