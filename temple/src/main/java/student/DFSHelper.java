package student;

import game.ExplorationState;
import game.NodeStatus;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;


public class DFSHelper {

    public static Collection<Long> convertNeighboursToLongs(ExplorationState state){
        return state.getNeighbours().stream().map(NodeStatus::nodeID).collect(Collectors.toList());
    }
    public static boolean amIStuck(ExplorationState state, Set<Long> visited){
        return state.getNeighbours().stream().map(NodeStatus::nodeID).allMatch(visited::contains);
    }
}
