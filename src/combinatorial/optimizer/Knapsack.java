package combinatorial.optimizer;

import java.util.ArrayList;

public class Knapsack extends Chromosome {
    private int capacity;
    private static int defaultCapacity = 15;

    public Knapsack() {
        this.capacity = defaultCapacity;
        intialise();
    }

    public Knapsack(int capacity) {
        this.capacity = capacity;
        intialise();
    }

    private void intialise() {
        genes = new ArrayList<Gene>();
        while(this.weight() <= this.capacity) {
            genes.add((new Item()).generate());
        }
    }

    public String toString() {
        return "KnapSack:[items:" + genes.size() + ", cost:" + this.cost() + ", weight:" + this.weight() + ", capacity:" + this.capacity + " items:" + this.genes.toString() + "]";
    }

    public int getFitness() {
        int weightFactor = this.capacity - (Math.abs(this.capacity - this.weight()) * 10);

        //return weightFactor;

        int costFactor = lowestPossibleCost(this.population) - this.cost();

        //System.out.println("weightFactor: " + weightFactor + " costFactor:" + costFactor);

        return weightFactor - costFactor;
    }

    private int lowestPossibleCost(Population population) {
        int lowestPossibleCost = ((Item)genes.get(0)).cost;
        for(int i = 1; i < this.genes.size(); ++i) {
            if (lowestPossibleCost >= ((Item)genes.get(i)).cost) {
                lowestPossibleCost = ((Item)genes.get(i)).cost;
            }
        }
        return lowestPossibleCost;
    }

    public int getOptimumFitness() {
        return this.capacity + lowestPossibleCost(this.population) + 1;
    }

    public Knapsack generate() {
        while (this.weight() < this.capacity)
            this.insert(new Item().generate());
        return this;
    }

    public Gene generateRandomGene() {
        return new Item().generate();
    }

    public void insert(Item item) {
        this.genes.add(item);
    }

    public int weight() {
        int weight = 0;
        for(Gene gene : genes) {
            weight += ((Item)gene).weight;
        }
        return weight;
    }

    public int cost() {
        int cost = 0;
        for (Gene gene : genes) {
            cost += ((Item) gene).cost;
        }
        return cost;
    }
}
