import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Graph.java : contains a LinkedList of Nodes and a LinkedList of Edges. Implements accessor
 * and mutator methods for those LinkedLists, as well as a method to find the shortest distance
 * (smallest weight) between two Nodes
 *
 * @author Jesse Cox
 * @version 1.0
 */

public class Graph {
    private LinkedList<Node> nodes;
    private LinkedList<Edge> edges;

    /**
     * Default constructor initializes two empty LinkedList.
     */
    public Graph(){
        this.nodes = new LinkedList<Node>();
        this.edges = new LinkedList<Edge>();
    }

    /**
     * Full constructor takes LinkedLists that have already been initialized.
     *
     * @param initialNodes
     *          a previously initialized LinkedList of Nodes that will be used as the list
     *          of Nodes in this graph.
     * @param initialEdges
     *          a previously initialized LinkedList of Edges that will be used as the list
     *          of Edges in this graph.
     */
    public Graph(LinkedList<Node> initialNodes, LinkedList<Edge> initialEdges) {
        this.nodes = initialNodes;
        this.edges = initialEdges;
    }

    /**
     * Mutator method uses a new LinkedList of Nodes to replace the current array.
     *
     * @param newNodes
     *          a LinkedList of Nodes that will replace this Graph's current LinkedList of Nodes
     */
    public void setNodes(LinkedList<Node> newNodes) { this.nodes = newNodes; }

    /**
     * Accessor method that will return a LinkedList of Nodes contained by this graph.
     *
     * @return this.nodes
     */
    public LinkedList<Node> getNodes() { return this.nodes; }

    /**
     * Mutator method uses a new LinkedList of Edges to replace the current array.
     *
     * @param newEdges
     *          a LinkedList of Edges that will replace this Graphs current LinkedList of Edges.
     */
    public void setEdges(LinkedList<Edge> newEdges) { this.edges = newEdges; }

    /**
     * Accessor method that will return a LinkedList of Edges contained by this graph.
     *
     * @return this.edges
     */
    public LinkedList<Edge> getEdges() { return this.edges; }

    /**
     * getEdge method finds the Edge between two given Nodes.
     *
     * @return
     */
    public int getEdge(Node findSource, Node findDestination) throws NodeNotContainedException {
        Edge findEdge = new Edge(findSource, findDestination);
        if ((!(edges.contains(findSource))) || (!(edges.contains(findDestination)))) {
            throw new NodeNotContainedException();
        } else {
            return (edges.get(edges.indexOf(findEdge))).getWeight();
        }
    }

    /**
     * addRoute takes a String such as "AB5" and attempts to add Nodes and Edges
     * based on the format of the input String.
     *
     * For example, AB5 would attempt to add a Node "A", a Node "B" and an Edge between
     * them of weight 5.
     */
    public void addRoute(String addString) throws InvalidRouteInputException {
        if ((!(Character.isLetter(addString.charAt(0)))) ||
                (!(Character.isLetter(addString.charAt(1)))) ||
                (!(Character.isDigit(addString.charAt(2))))) {
            throw new InvalidRouteInputException(addString);
        } else {
            Node sourceNode = new Node (String.valueOf(addString.charAt(0)));
            if (!(nodes.contains()))
        }
    }

    /**
     * getSmallestWeight finds the shortest route between two Nodes.
     *
     * - checks if this Graph contains the attempted source and destination
     * - recursive base case: if the source and destination are adjacent, return the
     *   weight of the route between them.
     * - otherwise: find a
     */
    public int getSmallestWeight(Node smallSource, Node smallDestination) throws NodeNotContainedException {
        Integer smallestWeight = 0;
        if (nodes.contains(smallSource)) {
            if (nodes.contains(smallDestination)) {
                if (smallDestination.getVisited()) {
                    return 0;
                } else if (smallSource.getAdjacentList().contains(smallDestination)) {
                    return getEdge(smallSource,smallDestination);
                } else {
                    ListIterator searcher = smallSource.getAdjacentList().listIterator();
                    smallSource.setVisited(true);
                    while(searcher.hasNext()) {
                        int checkWeight = (getEdge(smallSource,(Node) searcher.next())) +
                                           getSmallestWeight((Node) searcher.next(),smallDestination);
                        if ((smallestWeight == 0) || (checkWeight < smallestWeight)) {
                            smallestWeight = checkWeight;
                        }
                    }
                }
            } else {
                throw new NodeNotContainedException(smallDestination);
            }
        } else {
            throw new NodeNotContainedException(smallSource);
        }
        for(Node n : nodes) {
            n.setVisited(false);
        }
        return smallestWeight;
    }

    /**
     *
     */
}
