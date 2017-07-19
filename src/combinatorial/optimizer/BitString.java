package combinatorial.optimizer;

import java.util.ArrayList;

public class BitString extends Chromosome {
    private static int defaultGeneLength = 64;

    public BitString() {
        intialise();

    }

    public BitString(int length) {
        defaultGeneLength = length;
        intialise();
    }

    private void intialise() {
        genes = new ArrayList<Gene>(defaultGeneLength);
        for (int i = 0; i < defaultGeneLength; ++i)
            genes.add(new Bit());
    }

    public Chromosome generate() {
        return new BitString();
    }

    public Gene generateRandomGene() {
        return new Bit();
    }

    public String toString() {
        String geneString = "";
        for (int i = 0; i < size(); i++) {
            geneString += ((Bit)getGene(i)).bitValue;
        }
        return geneString;
    }

    public int getFitness() {
        int firstBitsMustBeSet = 6;
        int lastBitsMustBeSet = 6;

        int fitness = 0;
        for (int i = 0; i < this.size(); i++) {

            if((i < firstBitsMustBeSet || i >= this.size() - lastBitsMustBeSet) && ((Bit)this.getGene(i)).bitValue == 1) {
                fitness++;
            }

            if(!(i < firstBitsMustBeSet || i >= this.size() - lastBitsMustBeSet) && ((Bit)this.getGene(i)).bitValue == 0) {
                fitness++;
            }
        }

        return fitness;
    }

    // Get optimum fitness
    public int getOptimumFitness() {
        return defaultGeneLength;
    }

    private static Chromosome solution;

    // To make it easier we can use this method to set our candidate solution with string of 0s and 1s
    private Chromosome getSolution() {
        if (solution != null) {
            return solution;
        }

        //String newSolution = "1111110000000000000000000000000000000000000000000000000000000000";
        String newSolution = "1111110000000000000000000000000000000000000000000000000000111111";

        solution = new BitString(BitString.defaultGeneLength);
        // Loop through each character of our string and save it in our byte array
        for (int i = 0; i < newSolution.length(); i++) {
            String character = newSolution.substring(i, i + 1);
            if (character.contains("0") || character.contains("1")) {
                solution.setGene(i, new Bit(character));
            } else {
                solution.setGene(i, new Bit("0"));
            }
        }

        return solution;
    }
}
