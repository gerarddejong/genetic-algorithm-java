package combinatorial.optimizer;

import java.util.Random;

public class Item extends Gene {
    int cost;
    int weight;

    public Item() {}

    public Item(int cost, int weight) {
        this.cost = cost;
        this.weight = weight;
    }

    public String toString() {
        return "[cost:" + this.cost + ", weight:" + this.weight + "]";
    }

    public Item generate() {
        // Assumes unlimited items available
        switch(randomInteger(0, 4)) {
            case 0:  return new Item(4, 12);
            case 1:  return new Item(10, 4);
            case 2:  return new Item(2, 2);
            case 3:  return new Item(2, 1);
            default: return new Item(1, 1);
        }
    }

    private static int randomInteger(int minimum, int maximum) {
        return new Random().nextInt(maximum - minimum) + minimum;
    }

    public void mutate() {
        Item mutation = generate();
        this.cost = mutation.cost;
        this.weight = mutation.weight;
    }
}
