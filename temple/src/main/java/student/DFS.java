package student;

import game.ExplorationState;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is responsible for providing a beginDFS method that will
 * always return at the orb after searching through the maze in a "depthward"
 * manner, backtracking when necessary to find an unvisited tile
 *
 * @author Thomas Gardner
 */
public class DFS {
    /**
     * Begins the Depth-First Search (DFS) from the current location until the target is reached.
     *
     * @param state the current exploration state containing information about the node, its neighbors, and the target
     *
     * @author Thomas Gardner
     */
    public void beginDFS(ExplorationState state) {
        Collection<Long> neighbours;
        ArrayList<Long> path = new ArrayList<>();
        Set<Long> visited = new HashSet<>(); //Changed to hashset as more efficient and don't need to maintain order of visited -> O(n) to O(1)
        visited.add(state.getCurrentLocation()); //Adds the stairs to the visited array, so we don't go back to them

        while (state.getDistanceToTarget() > 0) {
            neighbours = DFSHelper.convertNeighboursToLongs(state);
            for (Long neighbour : neighbours) {
                if (!visited.contains(neighbour)) {
                    path.add(neighbour);
                    visited.add(neighbour);
                    state.moveTo(neighbour);
                    break;
                }
            }
            if (DFSHelper.amIStuck(state, visited) && state.getDistanceToTarget() > 0){
                Long previousLocation = path.get(path.size() - 2);
                state.moveTo(previousLocation);
                path.remove(path.size() - 1);
            }

        }
    }
}
