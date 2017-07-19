package combinatorial.optimizer;

public class Algorithm {
    private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;

    public static Population evolvePopulation(Population population) {
        Population newPopulation = new Population(population.size(), population.species);

        // Keep our best individual
        if (elitism) {
            newPopulation.saveIndividual(0, population.getFittest());
        }

        // Crossover population
        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }

        // Loop over the population size and create new individuals for crossover
        for (int i = elitismOffset; i < population.size(); i++) {
            Chromosome newChromosome = crossOverFromTournamentSelection(population);
            newPopulation.saveIndividual(i, newChromosome);
        }

        // Mutate population
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            mutate(newPopulation.getChromosome(i));
        }

        return newPopulation;
    }

    private static Chromosome crossOverFromTournamentSelection(Population population) {
        Chromosome mother = tournamentSelection(population);
        Chromosome father = tournamentSelection(population);
        return mother.crossover(father, uniformRate);
    }

    private static void mutate(Chromosome chromosome) {
        for (int geneIndex = 0; geneIndex < chromosome.size(); ++geneIndex) {
            if (Math.random() <= mutationRate) {
                // Create random gene
                Gene mutatedGene = chromosome.generateRandomGene();
                chromosome.setGene(geneIndex, mutatedGene);
            }
        }
    }

    // Select individuals for crossover
    private static Chromosome tournamentSelection(Population population) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, population.species);
        // For each place in the tournament get a random individual
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * population.size());
            tournament.saveIndividual(i, population.getChromosome(randomId));
        }
        // Get the fittest
        Chromosome fittest = tournament.getFittest();
        return fittest;
    }
}
