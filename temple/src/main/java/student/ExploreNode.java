package student;

import lombok.Getter;

import java.util.Objects;

@Getter
public class ExploreNode {
    private final long longID;

    private final boolean visited;

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
            //System.out.println("Checking node " + node.getLongID() + " against " + longID);
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
