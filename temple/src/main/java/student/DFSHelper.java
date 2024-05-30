package student;

import game.ExplorationState;
import game.NodeStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class DFSHelper {

    public static ArrayList<Long> convertNeighboursToLongs(ExplorationState state){
        List<NodeStatus> NodeStatusNeighbours = (ArrayList<NodeStatus>) state.getNeighbours();
        ArrayList<Long> LongNeighbours = new ArrayList<>();
        for (NodeStatus nodeStatus : NodeStatusNeighbours) {
            LongNeighbours.add(nodeStatus.nodeID());
        }
        return LongNeighbours;
    }
    public static boolean amIStuck(ExplorationState state, ArrayList<Long> visited){
        boolean amIStuck = true;
        ArrayList<Long> neighbours = convertNeighboursToLongs(state);
        for (Long neighbour : neighbours){
            if (!visited.contains(neighbour)) {
                amIStuck = false;
                break;
            }
        }
        return amIStuck;
    }
}
