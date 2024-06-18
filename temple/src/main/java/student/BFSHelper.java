package student;

import game.ExplorationState;
import game.NodeStatus;

import java.util.HashSet;
import java.util.Set;

/**
 * The BFSHelper class provides utility methods to assist with the Breadth-First Search (BFS) algorithm.
 *
 * <p>This class includes methods that facilitate the BFS algorithm by converting neighbors of the current state
 * to their respective node IDs, allowing for efficient tracking of visited nodes and path creation.</p>
 *
 * <p>Author: Thomas Gardner</p>
 */
public class BFSHelper {

    /**
     * Converts the neighbors of the current state to their respective node IDs.
     *
     * <p>This method takes the current exploration state, retrieves the neighbors, and converts them to a
     * collection of ExploreNodes. This is useful for tracking which nodes have been visited and for constructing
     * the path during the BFS traversal.</p>
     *
     * @param state the current exploration state containing information about the node and its neighbors
     * @return a collection of node IDs representing the neighbors of the current state
     */
    public static Set<ExploreNode> convertNeighboursToSetOfExploreNodes(ExplorationState state, ParentMap map) {
         Set<NodeStatus> nodeStatuses = new HashSet<>(state.getNeighbours());
         Set<ExploreNode> exploreNodes = new HashSet<>(nodeStatuses.size());
         for (NodeStatus nodeStatus : nodeStatuses) {
             exploreNodes.add(new ExploreNode(nodeStatus.nodeID(), nodeStatus.distanceToTarget(), map));
         }
         return exploreNodes;

    }
}
