package genome;

import data_structures.RandomHashSet;
import neat.Neat;

public class Genome  {

    private RandomHashSet<ConnectionGene> connections = new RandomHashSet<>();
    private RandomHashSet<NodeGene> nodes = new RandomHashSet<>();

    private Neat neat;

    public Genome(Neat neat) {
        this.neat = neat;
    }

    public double distance(Genome g2) {
        return 0;
    }

    public static Genome crossOver(Genome g1, Genome g2) { // G1 should be better
        return null;
    }

    public void mutate(){

    }


    public RandomHashSet<ConnectionGene> getConnections() {
        return connections;
    }

    public RandomHashSet<NodeGene> getNodes() {
        return nodes;
    }

    public Neat getNeat() {
        return neat;
    }


}
