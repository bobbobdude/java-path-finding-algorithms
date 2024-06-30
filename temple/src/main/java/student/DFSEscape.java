package student;

import game.EscapeState;
import game.Node;

import java.util.*;

public class DFSEscape {

    public void dfsEscape(EscapeState state) {
        Collection<Node> neighbours;
        ArrayList<Node> path = new ArrayList<>();
        Set<Node> visited = new HashSet<>();
        DFSHelper.pickUpGoldIfExistsAndHandleErrorIfNot(state);

        while (!state.getCurrentNode().equals(state.getExit())) {
            neighbours = state.getCurrentNode().getNeighbours();
            for (Node n : neighbours) {
                if (!visited.contains(n)) {
                    path.add(n);
                    visited.add(n);
                    state.moveTo(n);
                    DFSHelper.pickUpGoldIfExistsAndHandleErrorIfNot(state);
                    break;
                }
            }

            if (DFSHelper.amIStuck(state, visited) && !DFSHelper.amIAtExit(state)) {
                Node previousLocation = path.get(path.size() - 2);
                state.moveTo(previousLocation);
                path.remove(path.size() - 1);
            }

        }
    }

}
