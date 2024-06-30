package student;

import java.util.*;

/**
 * This class which I want to call a data structure class (simply because it sounds cool and smart)
 * is responsible for maintaining and correctly manipulating the parentMap variable.
 *
 * <p>This stores every visited tile, using the Long ID as a key and the ExploreNodes as the value.
 * This allows me to track every node that is visited as well as the nodes we are yet to visit.
 *
 * @author Thomas Gardner
 */

public class ParentMap{
    Map<Long, Set<ExploreNode>> parentMap = new HashMap<>();


    public void addNeighbours(Long nodeIDKey, Set<ExploreNode> neighbours) throws NoSuchElementException {
        if (!parentMap.containsKey(nodeIDKey)){
            parentMap.put(nodeIDKey, neighbours); //If the current Node is not contained within the keys create it and add the neighbours

            //This makes sure all previous nodes that were marked as unvisited are now marked as visited if we add them as a node id key (meaning we are on them)
            for (ExploreNode nodeAlreadyInMap : getAllNodesInMap()){
                if(nodeAlreadyInMap.getLongID() == nodeIDKey){
                    nodeAlreadyInMap.setVisited(true);
                }
            }


        }
    }

    public Set<ExploreNode> getNeighboursFromParentMap(Long nodeIDKey){
        return parentMap.get(nodeIDKey);
    }

    public void removeNeighbours(Long nodeIDKey, long neighbour) throws NoSuchElementException { //Could use this if you want but not sure if it is actually needed
        if (parentMap.containsKey(nodeIDKey)) {
            parentMap.get(nodeIDKey).removeIf(node -> node.getLongID() == neighbour);
        }
        else{
            throw new NoSuchElementException();
        }
    }



    public Set<Long> getAllKeysInMap(){
        return parentMap.keySet();
    }

    public Set<ExploreNode> getAllNodesInMap(){
        Set<ExploreNode> allNodes = new HashSet<>();
        for (Set<ExploreNode> nodes : parentMap.values()){
            allNodes.addAll(nodes);
        }
        return allNodes;
    }

    @Override
    public String toString() {
        return parentMap.toString();
    }

}


