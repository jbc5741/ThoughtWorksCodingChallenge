/**
 * Edge.java : Will represent the route between two Nodes. Holds instance variables for the Node at
 * the source and destination of the route. Also contains an integer instance variable that represents
 * the distance between the two Nodes.
 *
 * @author Jesse Cox
 * @version 1.0
 */

public class Edge {
    private Node source;
    private Node destination;
    private int weight;

    /*
     * The default value for weight will be set to 1, but can be set freely and easily here.
     */
    private static int DEFAULT_WEIGHT = 1;

    /**
     * Default constructor sets both source and destination to null and sets the weight to the
     * default weight of 1.
     */
    public Edge() {
        this.weight = DEFAULT_WEIGHT;
    }

    /**
     * Full constructor takes a Node for source, destination, and an integer for the weight
     * of the edge
     */
    public Edge(Node initialSource, Node initialDestination, int initialWeight) {
        this.source = initialSource;
        this.destination = initialDestination;
        this.weight = initialWeight;
    }

    /**
     * Mutator method for source uses a new Node to replace this Edge's current source Node
     *
     * @param newSource
     *          represents a Node that will be the new source Node for this Edge.
     */
    public void setSource(Node newSource) {
        this.source = newSource;
    }

    /**
     * Accessor method for source returns this Edge's current source Node.
     *
     * @return this.source
     */
    public Node getSource() {
        return this.source;
    }

    /**
     * Mutator method for destination uses a new Node to replace this Edge's current
     * destination Node.
     *
     * @param new
     */

    /**
     * Accessor method for destination returns this Edge's current destination Node.
     *
     * @return this.destination
     */
    public Node getDestination() {
        return this.destination;
    }

}
