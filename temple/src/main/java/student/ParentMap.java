package student;

import java.util.*;

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
/*        else{
            parentMap.get(nodeIDKey).addAll(neighbours);
        }*/

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


