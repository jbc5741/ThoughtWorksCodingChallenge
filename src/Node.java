import java.util.LinkedList;

/**
 * Node.java : Contains a LinkedList of type Node that represents the Nodes that are
 * adjacent.
 *
 * @author Jesse Cox
 * @version 1.0
 */

public class Node {
    private LinkedList<Node> adjacentList;


    /**
     * Default constructor, creates an empty LinkedList, but specifies that it should
     * be of type Node.
     */
    public Node() {
        this.adjacentList = new LinkedList<Node>();
    }

    /**
     * Full constructor takes an already established
     *
     * @param initialAdjacentList
     *          a previously created LinkedList of type Node that
     *          will be used as the list of nodes adjacent to this
     *          one.
     */
    public Node(LinkedList<Node> initialAdjacentList) {
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


}
