
package student;

import game.ExplorationState;

import java.util.*;

import static student.BFSHelper.convertNeighboursToSetOfExploreNodes;

public class BFSExplorer {

    // Method to find the orb using Breadth-First Search (BFS)
    public void findOrb(ExplorationState state) {
        ParentMap map = new ParentMap();

        int x = 0;

        while (state.getDistanceToTarget() > 0){
            map.addNeighbours(state.getCurrentLocation(), convertNeighboursToSetOfExploreNodes(state, map));
            Set<ExploreNode> neighboursToVisit;

            long nodeToMoveBackTo = state.getCurrentLocation();
            neighboursToVisit = convertNeighboursToSetOfExploreNodes(state, map);

            System.out.println("Times through while loop " + x + " : " + neighboursToVisit);

            //Goes to all neighbours and then returns to the original spot
            for (ExploreNode neighbour : neighboursToVisit) {
                //Don't need to return to original spot if there is only one neighbour - just keep swimming (will fix this later) Maybe also sorts out backtracking?
                if (neighboursToVisit.size() == 1 || neighboursToVisit.stream().allMatch(ExploreNode::isVisited)){
                    moveTo(state, neighbour.getLongID(), map);
                    break;
                }
                else if (!neighbour.isVisited()){
                    moveTo(state, neighbour.getLongID(), map);
                    moveTo(state, nodeToMoveBackTo, map);
                }
            }
            x++;

        }
    }

    public void moveTo(ExplorationState state, long nodeToMoveTo, ParentMap mapToModify) {
        state.moveTo(nodeToMoveTo);
        mapToModify.addNeighbours(state.getCurrentLocation(), convertNeighboursToSetOfExploreNodes(state, mapToModify));
    }

}
