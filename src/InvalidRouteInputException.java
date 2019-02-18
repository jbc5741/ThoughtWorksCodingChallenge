/**
 * NodeNotContainedException.java : Will be thrown when the programmer attempts to add an
 * invalid route to the graph
 *
 * @author Jesse Cox
 * @version 1.0
 */

public class InvalidRouteInputException extends Exception {
    public InvalidRouteInputException(String s) {
        super(s + " is not a valid input to add to this graph.");
    }
}
