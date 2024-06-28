package student;

import game.ExplorationState;

import java.util.*;
import java.util.stream.Collectors;

import static student.BFSHelper.convertNeighboursToSetOfExploreNodes;

/**
 * This class is responsible for providing a findOrb method that will
 * always return at the orb after searching through the maze in a "heuristic depth first search "
 * manner, backtracking when necessary to find an unvisited tile.
 *
 * @author Thomas Gardner
 */
public class DFSHeuristicsExplorer {

    /**
     * Begins the Depth-First Search (DFS) from the current location until the target is reached.
     *
     * @param state the current exploration state containing information about the node, its neighbors, and the target
     *
     * @author Robin Martin & Thomas Gardner
     */
    public void findOrb(ExplorationState state) {
        ParentMap map = new ParentMap(); // Map of all traversed nodes with their neighbours in no particular order
        ArrayList<Long> pathTaken = new ArrayList<>();
        Set<Long> visited = new HashSet<>();
        Deque<Long> backtrackStack = new ArrayDeque<>();

        while (state.getDistanceToTarget() > 0) {
            long currentLocation = state.getCurrentLocation();
            map.addNeighbours(currentLocation, convertNeighboursToSetOfExploreNodes(state, map));
            visited.add(currentLocation);

            Set<ExploreNode> currentNeighbours = map.getNeighboursFromParentMap(currentLocation);
            List<ExploreNode> unvisitedNeighbours = currentNeighbours.stream()
                    .filter(node -> !visited.contains(node.getLongID()))
                    .toList();

            if (unvisitedNeighbours.isEmpty()) {
                // Backtrack if there are no unvisited neighbors
                if (!backtrackStack.isEmpty()) {
                    long previousLocation = backtrackStack.pop();
                    state.moveTo(previousLocation);
                    pathTaken.remove(pathTaken.size() - 1); // Remove current location from path
                }
            } else {
                ExploreNode nextNode = unvisitedNeighbours.stream()
                        .min(Comparator.comparingInt(ExploreNode::getDistanceToOrb))
                        .orElseThrow(() -> new NoSuchElementException("No unvisited neighbours found"));

                moveTo(state, nextNode.getLongID(), map, pathTaken);
                backtrackStack.push(currentLocation);
            }
        }
    }

    public void moveTo(ExplorationState state, long nodeToMoveTo, ParentMap mapToModify, ArrayList<Long> pathTaken) {
        state.moveTo(nodeToMoveTo);
        mapToModify.addNeighbours(nodeToMoveTo, convertNeighboursToSetOfExploreNodes(state, mapToModify));
        pathTaken.add(nodeToMoveTo);
    }
}