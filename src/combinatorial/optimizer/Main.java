// From: http://www.theprojectspot.com/tutorial-post/creating-a-genetic-algorithm-for-beginners/3

package combinatorial.optimizer;

public class Main {

    public static void main(String[] args) {
        // Create an initial population
        //Population population = new Population(50, BitString.class);
        Population population = new Population(50, Knapsack.class);

        // Evolve our population until we reach an optimum solution
        int generationCount = 0;
        while (!population.hasReachedTerminationState()) {
            generationCount++;
            System.out.println("Generation: " + String.format("%03d", generationCount) + " Fittest: " + population.getFittest().getFitness() + " " + population.getFittest());
            population = Algorithm.evolvePopulation(population);
        }

        System.out.println("Solution found!");
        System.out.println("Generation: " + generationCount);
        System.out.println("Genes:");
        System.out.println(population.getFittest());
    }
}
