package student;

import game.Node;
import lombok.Getter;

import java.util.*;

public class AStar {

    private record NodeWrapper(@Getter Node node, double fScore) implements Comparable<NodeWrapper> {

        @Override
            public int compareTo(NodeWrapper other) {
                return Double.compare(this.fScore, other.fScore);
            }
        }

    public AStar() {}

    /**
     * Given a starting point and a target to reach, calculate the optimal path
     * Uses A* algorithm
     *
     * @param start The node to start traversal at
     * @param target The node that needs to be reached
     * @return List containing the nodes that make up the optimal path
     */
    public List<Node> findPath(Node start, Node target) {
        // Nodes to visit
        PriorityQueue<NodeWrapper> open = new PriorityQueue<>();
        // Nodes that have already been visited
        Set<Node> closed = new HashSet<>();

        // Map to track parent relationship to nodes as this characteristic is unavailable
        Map<Node, Node> parents = new HashMap<>();

        // Costs of the shortest path from start to current node
        Map<Node, Double> gScores = new HashMap<>();
        // Costs of estimated cost of shortest path from start to target that contains current node in path
        Map<Node, Double> fScores = new HashMap<>();

        // Prepare for traversal
        gScores.put(start, 0.0);
        fScores.put(start, heuristic(start, target));
        open.add(new NodeWrapper(start, fScores.get(start)));

        // Start traversal
        while (!open.isEmpty()) {
            NodeWrapper curWrapper = open.poll();
            Node cur = curWrapper.node();

            // Target found, time to get path travelled and exit
            if (cur.equals(target)) {
                return constructPath(start, cur, parents);
            }
            closed.add(cur);

            for (Node neighbour : cur.getNeighbours()) {
                if (!closed.contains(neighbour)) { // Only evaluate neighbours that have not been visited
                    // Check if g score for neighbour already exists, meaning it is already visited
                    double currentGScore = gScores.getOrDefault(neighbour, Double.POSITIVE_INFINITY);
                    // Calculate new g score from distance between current node and neighbour
                    double newGScore = gScores.getOrDefault(cur, Double.POSITIVE_INFINITY) + heuristic(cur, neighbour);
                    /*
                     If new g score is lower, path through current node is better than any
                      previously discovered path to neighbour, therefore we want to carry on traversing this path
                     */
                    if (newGScore < currentGScore) {
                        gScores.put(neighbour, newGScore);
                        fScores.put(neighbour, newGScore);
                        open.add(new NodeWrapper(neighbour, newGScore));
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
    private static List<Node> constructPath(Node start, Node node, Map<Node, Node> parents) {
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
    private double heuristic(Node a, Node b) {
        // Calculate the Manhattan distance between two nodes as movement is limited to 4 directions
        return Math.abs(a.getTile().getRow() - b.getTile().getRow()) +
                Math.abs(a.getTile().getColumn() - b.getTile().getColumn());
    }
}
