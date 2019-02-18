import java.util.LinkedList;

/**
 * Node.java : Contains a String holding the name of the station associated with this Node, as well as a boolean to
 * to track if this station has been visited. Also contains a LinkedList of type Node that represents the Nodes that are
 * adjacent to this particular Node.
 *
 * @author Jesse Cox
 * @version 1.0
 */

public class Node {
    private String station;
    private boolean visited;
    private LinkedList<Node> adjacentList;

    private static String DEFAULT_STATION = "No Station Yet!";

    /**
     * Default constructor, creates an empty LinkedList, but specifies that it should
     * be of type Node. Also sets the station name to the default, and visited to false.
     */
    public Node() {
        this.station = DEFAULT_STATION;
        this.visited = false;
        this.adjacentList = new LinkedList<Node>();
    }
    /**
     * Partial constructor takes only the station name, and sets default values for the other variables.
     *
     * @param initialStation
     *          String representing the initial name for this Node's station
     */
    public Node(String initialStation) {
        this.station = initialStation;
        this.visited = false;
        this.adjacentList = new LinkedList<Node>();
    }

    /**
     * Full constructor takes an already initialized LinkedList of Nodes.
     *
     * @param initialAdjacentList
     *          a previously created LinkedList of type Node that
     *          will be used as the list of nodes adjacent to this
     *          one.
     */
    public Node(String initialStation, LinkedList<Node> initialAdjacentList) {
        this.station = initialStation;
        this.visited = false;
        this.adjacentList = initialAdjacentList;
    }

    /**
     * Mutator method uses a new LinkedList to replace the current.
     *
     * @param newAdjacentList
     *          A LinkedList of nodes that will replace this Node's current list of adjacent Nodes.
     */
    public void setAdjacentList(LinkedList<Node> newAdjacentList) {
        this.adjacentList = newAdjacentList;
    }

    /**
     * Accessor method that will return a LinkedList of this Node's adjacent Nodes.
     *
     * @return this.adjacentList.
     */
    public LinkedList<Node> getAdjacentList() {
        return this.adjacentList;
    }

    /**
     * Mutator method uses a new String to replace the current station name.
     *
     * @param newStation
     *          A String to represent the new name for the station associated with this Node.
     */
    public void setStation(String newStation) { this.station = newStation; }

    /**
     * Accessor method that will return a String representing the station associated with this Node.
     *
     * @return this.station
     */
    public String getStation() { return this.station; }

    /**
     * Mutator method to set whether this Node has been visited.
     *
     * @param newVisited
     *          boolean value representing whether or not this Node has been visited
     */
    public void setVisited(boolean newVisited) { this.visited = newVisited; }

    /**
     * Accessor method that will return whether or not this Node has been visited.
     *
     * @return this.visited
     */
    public boolean getVisited() { return this.visited; }

    /**
     * equals method checks if station and adjacent list are equal
     */
    @Override
    public boolean equals(Object anObject) {
        if (anObject == null || (this.getClass() != anObject.getClass())) {
            return false;
        } else {
            Node altNode = (Node) anObject;
            return (this.getStation().equals(altNode.getStation()));
        }
    }

    /**
     * toString will only return the station name for this Node
     */
    @Override
    public String toString() {
        return this.station;
    }


}
