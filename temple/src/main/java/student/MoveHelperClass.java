package student;

import game.ExplorationState;

import java.util.List;

import static student.ParentMapHelper.convertNeighboursToSetOfExploreNodes;

public class MoveHelperClass {
    public static void moveTo(ExplorationState state, long nodeToMoveTo, ParentMap mapToModify, List<Long> pathTaken) {
        state.moveTo(nodeToMoveTo);
        mapToModify.addNeighbours(nodeToMoveTo, convertNeighboursToSetOfExploreNodes(state, mapToModify));
        pathTaken.add(nodeToMoveTo);
    }
}
