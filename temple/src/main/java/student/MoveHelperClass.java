package student;

import game.ExplorationState;

import java.util.List;

import static student.ParentMapHelper.convertNeighboursToSetOfExploreNodes;
/**
 * This class holds the "wrapper" moveTo method that ensures the parent map is updated every time a different tile is moved to, this is placed in a utility class as it is
 * used by both DFSHeuristicsExplorer and the BFSExplorer classes.
 *
 * @author Thomas Gardner
 */
public class MoveHelperClass {
    public static void moveTo(ExplorationState state, long nodeToMoveTo, ParentMap mapToModify, List<Long> pathTaken) {
        state.moveTo(nodeToMoveTo);
        mapToModify.addNeighbours(nodeToMoveTo, convertNeighboursToSetOfExploreNodes(state, mapToModify));
        pathTaken.add(nodeToMoveTo);
    }
}
