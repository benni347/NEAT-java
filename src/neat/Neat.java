package neat;

import data_structures.RandomHashSet;
import genome.ConnectionGene;
import genome.Genome;
import genome.NodeGene;

import java.util.HashMap;
import java.util.HashSet;

public class Neat {

    public static final int MAX_NODES = (int)Math.pow(2, 20); // Around 2 Million

    private HashMap<ConnectionGene, ConnectionGene> all_connection = new HashMap<>();
    private RandomHashSet<NodeGene> all_nodes = new RandomHashSet<>();
    private int max_clients;
    private int output_size;
    private int input_size;

    public Neat( int input_size, int output_site, int clients){
        this.reset(input_size, output_site, clients);
    }


    public Genome empty_genome() {
        Genome g = new Genome(this);
        for(int i = 0; i < input_size + output_size; i++) {
            g.getNodes().add(getNode(i + 1));
        }
        return g;

    }

    public static ConnectionGene getConnection(ConnectionGene con) { // this copies the connection
        ConnectionGene c = new ConnectionGene(con.getFrom(), con.getTo());
        c.setWeight(con.getWeight());
        c.setEnabled(con.isEnabled());
        return c;
    }

    public ConnectionGene getConnection(NodeGene node1, NodeGene node2) {
        ConnectionGene connectionGene = new ConnectionGene(node1, node2);

        if(all_connection.containsKey(connectionGene)) {
            connectionGene.setInnovation_number(all_connection.get(connectionGene).getInnovation_number());
        } else {
            connectionGene.setInnovation_number(all_connection.size() + 1);
            all_connection.put(connectionGene, connectionGene);
        }

        return connectionGene;
    }

    public void reset(int input_size, int output_size, int clients){
        this.input_size = input_size;
        this.output_size = output_size;
        this.max_clients = clients;

        all_connection.clear();
        all_nodes.clear();

        for(int i = 0;i < input_size; i++) {
            NodeGene n = getNode();
            n.setX(0.1);
            n.setY((i + 1) / (double)(input_size + 1));
        }

        for(int i = 0; i < output_size; i++) {
            NodeGene n = getNode();
            n.setX(0.9);
            n.setY((i + 1) / (double)(output_size + 1));
        }
    }

    public NodeGene getNode() {
        NodeGene n = new NodeGene(all_nodes.size() + 1);
        all_nodes.add(n);
        return n;
    }

    public NodeGene getNode(int id)
    {
        if(id <= all_nodes.size()) return all_nodes.get(id -1);
        return getNode();
    }

    public static void main(String[] args) {
        Neat neat = new Neat(3, 3, 100);

        Genome g = neat.empty_genome();
        System.out.println(g.getNodes().size());

    }

}
