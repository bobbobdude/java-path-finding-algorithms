package student;

import game.ExplorationState;
import game.NodeStatus;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The DFSHelper class provides utility methods to assist with the Depth-First Search (DFS) algorithm.
 *
 * @author Thomas Gardner
 */
public class DFSHelper {
    /**
     * Determines if the current state location has no unvisited neighbours.
     *
     * <p>This method checks if all the neighboring nodes of the current state have already been visited.
     * If all neighbors have been visited, it returns {@code true}, indicating that there are no unvisited
     * neighbors to explore, and therefore we need to backtrack. Otherwise, it returns {@code false}.</p>
     *
     * @param state the current exploration state containing information about the node and its neighbors
     * @param visited a set of node IDs that have already been visited
     * @return {@code true} if all neighboring nodes have been visited, {@code false} otherwise
     *
     * @author Thomas Gardner
     */
    public static boolean amIStuck(ExplorationState state, Set<Long> visited){
        return state.getNeighbours().stream().map(NodeStatus::nodeID).allMatch(visited::contains);
    }

    /**
     * Converts the neighbors of the current state to their respective node IDs, as this is how my code keeps track of visited nodes and creates the path.
     *
     * @param state the current exploration state containing information about the node and its neighbors
     * @return a collection of node IDs representing the neighbors of the current state
     *
     * @author Thomas Gardner
     */
    public static Collection<Long> convertNeighboursToLongs(ExplorationState state){
        return state.getNeighbours().stream().map(NodeStatus::nodeID).collect(Collectors.toList());
    }

}
