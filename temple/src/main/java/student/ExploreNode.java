package student;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * This class takes every element provided by node status and stores it/"wraps it" within an
 * ExploreNode object.
 * The reason for this is that it allows us to additionally store a boolean value which marks
 * whether it has been visited or not.
 *
 * <p>To determine this we pass the
 * ParentMap to the constructor which allows the internal logic to determine
 * whether a node should be set to true or not.
 * This is a vital part of my DFS solution as it forms the backbone of the ParentMap class which allows us
 * to build a more complete picture of the cavern, based on where we have discovered.
 *
 * @author Thomas Gardner
 */

@Getter
public class ExploreNode {
    private final long longID;

    @Setter
    private boolean visited;

    private final int distanceToOrb;

    ExploreNode(long longID, int distanceToOrb, ParentMap mapToCheck) {
        this.longID = longID;
        this.distanceToOrb = distanceToOrb;

        boolean partOfKeys = false;
        boolean oneOfNeighbours = false;

        for(Long key: mapToCheck.getAllKeysInMap()){ //We've visited the node as we have been there before and checked its neighbours
            //System.out.println("Checking key " + key + " against " + longID);
            if (longID == key) {
                partOfKeys = true;
                break;
            }
        }

        for (ExploreNode node : mapToCheck.getAllNodesInMap()){//We've visited the node as it is already included in one of the sets of neighbours
            if (longID == node.longID) {
                oneOfNeighbours = true;
                break;
            }
        }

        if (partOfKeys || oneOfNeighbours){
            visited = true;
        }
        else{
            visited = false;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ExploreNode)) {
            return false;
        }
        ExploreNode toCompareTo = (ExploreNode) obj;
        return longID == toCompareTo.longID && visited == toCompareTo.visited && distanceToOrb == toCompareTo.distanceToOrb;
    }

    @Override
    public int hashCode() {
        return Objects.hash(longID, visited, distanceToOrb);
    }


    @Override
    public String toString() {
        return "ExploreNode [longID=" + longID + ", visited=" + visited + ", distanceToOrb=" + distanceToOrb + "]";
    }

}
