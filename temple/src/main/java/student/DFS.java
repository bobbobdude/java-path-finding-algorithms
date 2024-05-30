package student;

import game.ExplorationState;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class DFS {
    public void beginDFS2(ExplorationState state) {
        Set<Long> visited = new HashSet<>(); //Changed to hashset as more efficient -> O(n) to O(1)
        visited.add(state.getCurrentLocation()); //Adds the stairs to the visited array, so we don't go back to them
        Collection<Long> neighbours;
        ArrayList<Long> path = new ArrayList<>();

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
