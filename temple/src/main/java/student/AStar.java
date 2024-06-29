package student;

import game.EscapeState;
import game.Node;
import lombok.Getter;

import java.util.*;

/**
 * This class implements an escape from the cavern to maximise gold collection with the A* path finding algorithm
 *
 * @author Daisy Riley
 */
public class AStar {

    // Hash map of all nodes that have gold, along with the amount of gold
    @Getter
    private final Map<Node, Integer> goldLocations = new HashMap<>();

    // The NodeWrapper class allows use of a PriorityQueue, with sorting based on f scores
    @Getter
    public static class NodeWrapper implements Comparable<NodeWrapper> {
        private final Node node;
        private final Integer fScore;

        /**
         * Constructor for the class
         *
         * @param node
         * @param fScore
         */
        public NodeWrapper(Node node, Integer fScore) {
            this.node = node;
            this.fScore = fScore;
        }

        /**
         * Override default compareTo with integer comparison of 2 f scores
         *
         * @param other the object to be compared.
         * @return the value 0 if this = other; a value less than 0 if this < other;
         * and a value greater than 0 if this > other
         */
        @Override
        public int compareTo(NodeWrapper other) {
            return Integer.compare(this.fScore, other.fScore);
        }
    }

    /**
     * Constructor for AStar class
     *
     * @param vertices A representation of all nodes in the grid that path finding will be performed on
     */
    public AStar(Collection<Node> vertices) {
        for (Node node : vertices) {
            if (node.getTile().getGold() > 0) {
                goldLocations.put(node, node.getTile().getGold());
            }
        }
    }

    /**
     * Move around the given cavern based on the optimal path
     *
     * @param state The current game state during escape
     */
    public void traverse(EscapeState state) throws Exception {
        List<Node> path;
        while (state.getCurrentNode() != state.getExit()) { // Must end on exit node
            Node target = state.getExit();

            for (Node goldNode : goldLocations.keySet()) { // Try to find paths containing gold
                // Check there is enough time to reach the gold and exit before proceeding
                if (state.getTimeRemaining() >=
                        pathTraversalDuration(state.getCurrentNode(), findPath(state.getCurrentNode(), goldNode))
                        + pathTraversalDuration(goldNode, findPath(goldNode, state.getExit()))) {
                    target = goldNode;
                    break;
                }
            }
            path = findPath(state.getCurrentNode(), target);
            if (path.isEmpty()) {
                throw new Exception("There are no possible paths");
            } else {
                for (Node node : path) { // Move through path and pick up gold
                    state.moveTo(node);
                    if (goldLocations.containsKey(node)) {
                        state.pickUpGold();
                        goldLocations.remove(node); // Delete from gold list after pickup to avoid looping over needlessly
                    }
                }
            }
        }
    }

    /**
     * Given a starting point and a target to reach, calculate the optimal path
     * Uses A* algorithm
     *
     * @param start The node to start traversal at
     * @param target The node that needs to be reached
     * @return List containing the nodes that make up the optimal path
     */
    private List<Node> findPath(Node start, Node target) {
        // Nodes to visit
        PriorityQueue<NodeWrapper> open = new PriorityQueue<>();
        // Nodes that have already been visited
        Set<Node> closed = new HashSet<>();
        // Map to track parent relationship to nodes as this characteristic is unavailable
        Map<Node, Node> parents = new HashMap<>();

        // Costs of the shortest path from start to current node
        Map<Node, Integer> gScores = new HashMap<>();
        // Costs of estimated cost of shortest path from start to target that contains current node in path
        Map<Node, Integer> fScores = new HashMap<>();

        // Prepare for traversal
        gScores.put(start, 0);
        fScores.put(start, heuristic(start, target));
        open.add(new NodeWrapper(start, fScores.get(start)));

        // Start traversal
        while (!open.isEmpty()) {
            NodeWrapper curWrapper = open.poll();
            Node cur = curWrapper.getNode();

            // Target found, time to get path travelled and exit
            if (cur.equals(target)) {
                return constructPath(start, cur, parents);
            }
            closed.add(cur);

            for (Node neighbour : cur.getNeighbours()) {
                if (!closed.contains(neighbour)) { // Only evaluate neighbours that have not been visited
                    // Check if g score for neighbour already exists, meaning it is already visited
                    Integer currentGScore = gScores.getOrDefault(neighbour, Integer.MAX_VALUE);
                    /*
                     Calculate new g score from distance between current node and neighbour, taking into account gold
                     This is not a true g score as a true g score is based solely on distance, but this yields
                     better results for gold collection
                    */
                    Integer newGScore = gScores.getOrDefault(cur, Integer.MAX_VALUE) + heuristic(cur, neighbour);
                    /*
                     If new g score is lower, path through current node is better than any
                      previously discovered path to neighbour, therefore we want to carry on traversing this path
                     */
                    if (newGScore < currentGScore) {
                        gScores.put(neighbour, newGScore);
                        Integer newFScore = newGScore + heuristic(neighbour, target);
                        fScores.put(neighbour, newFScore);
                        open.add(new NodeWrapper(neighbour, newFScore));
                        parents.put(neighbour, cur);
                    }
                }
            }
        }
        // No path found
        return Collections.emptyList();
    }

    /**
     * Given a node, retrace previously visited nodes until the entire path from start to the given node is found
     *
     * @param node Node to traverse upwards from
     * @return List containing the path from a starting point (root) to the given node
     */
    private List<Node> constructPath(Node start, Node node,Map<Node, Node> parents) {
        List<Node> path = new ArrayList<>();
        // Traverse up until root, aka start point
        while (node != null && !node.equals(start)) {
            path.add(node);
            node = parents.get(node);
        }
        // Reverse to get path starting from root
        Collections.reverse(path);
        return path;
    }

    /**
     * Calculate the estimated cost to move from one node to another
     * This value is often called h or heuristic in the case of the A* algorithm
     *
     * @param a Starting node
     * @param b Node moving to
     * @return Distance between the 2 nodes calculated by Manhattan distance
     */
    private Integer heuristic(Node a, Node b) {
        // Calculate the Manhattan distance between two nodes as movement is limited to 4 directions
        int distance = Math.abs(a.getTile().getRow() - b.getTile().getRow())
                + Math.abs(a.getTile().getColumn() - b.getTile().getColumn());

        Integer containsGold = goldLocations.getOrDefault(b, 0); // Amount of gold on tile

        // Calculate final value to maximize gold
        return distance - (containsGold / 10);
    }

    /**
     * Calculates the cost of moving from across a path from a starting point
     *
     * @param path The path to traverse over
     * @param cur The node to begin traversal at
     * @return Integer representing time taken to travel the path
     */
    private Integer pathTraversalDuration(Node cur, List<Node> path) {
        int duration = 0;
        for (Node next : path) {
            duration += cur.getEdge(next).length; // The length of the edge we travel against
            cur = next; // Reset cursor for next loop iteration
        }
        return duration;
    }
}
