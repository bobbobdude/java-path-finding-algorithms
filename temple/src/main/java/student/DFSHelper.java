package student;

import game.ExplorationState;
import game.NodeStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class DFSHelper {

    public static Collection<Long> convertNeighboursToLongs(ExplorationState state){
        Collection<NodeStatus> NodeStatusNeighbours = state.getNeighbours();
        return NodeStatusNeighbours.stream().map(NodeStatus::nodeID).collect(Collectors.toList());
    }
    public static boolean amIStuck(ExplorationState state, Set<Long> visited){
        boolean amIStuck = true;
        Collection<Long> neighbours = convertNeighboursToLongs(state);
        for (Long neighbour : neighbours){
            if (!visited.contains(neighbour)) {
                amIStuck = false;
                break;
            }
        }
        return amIStuck;
    }
}
