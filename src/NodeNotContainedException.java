/**
 * NodeNotContainedException.java : Will be thrown when the user tries to find the route between one or
 * more Nodes that are not contained by the given Graph.
 *
 * @author Jesse Cox
 * @version 1.0
 */

public class NodeNotContainedException extends Exception {
    public NodeNotContainedException(Node n) {
        super(n +
                " is not a valid option!");
    }

    public NodeNotContainedException() {
        super("That is not a valid Node!");
    }
}
