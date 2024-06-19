
package student;

import game.ExplorationState;

import java.util.*;
import java.util.stream.Collectors;

import static student.BFSHelper.convertNeighboursToSetOfExploreNodes;

public class BFSExplorer {

    // Method to find the orb using Breadth-First Search (BFS)
    public void findOrb(ExplorationState state) {
        ParentMap map = new ParentMap();//Map of all traversed nodes with their  neighbours in no particular order
        ArrayList<Long> pathTaken = new ArrayList<>();

        while(state.getDistanceToTarget() > 0){
            map.addNeighbours(state.getCurrentLocation(), convertNeighboursToSetOfExploreNodes(state,map));

            Set<ExploreNode> currentNeighbours = map.getNeighboursFromParentMap(state.getCurrentLocation());

            for(ExploreNode neighbour : currentNeighbours){

                if(currentNeighbours.size() == 1){

                    moveTo(state, neighbour.getLongID(), map, pathTaken); //Just keep going if there is only one neighbour, as we have no other option!

                } else if (currentNeighbours.stream().filter(node -> !node.isVisited()).count() == 1) {



                    ExploreNode unvisitedNeighbourToMoveTo = currentNeighbours.stream().filter(node -> !node.isVisited()).findFirst().get();
                    moveTo(state, unvisitedNeighbourToMoveTo.getLongID(), map, pathTaken); //No need to move back if there is only one that isn't visited - just stay there
                    break;

                } else if (currentNeighbours.stream().filter(node -> !node.isVisited()).count() > 1){ //If we have more than one unvisited neighbour navigate to them and then back to the root (or the tile we came from)

                    long longToMoveBackTo = state.getCurrentLocation();

                    Set<ExploreNode> unexplored = currentNeighbours.stream().filter(node -> !node.isVisited()).collect(Collectors.toSet());

                    for(ExploreNode unexploredNode : unexplored){
                        moveTo(state, unexploredNode.getLongID(), map, pathTaken);
                        if(state.getDistanceToTarget() == 0){
                            break;
                        }
                        moveTo(state, longToMoveBackTo, map, pathTaken);
                    }
                    if(state.getDistanceToTarget() == 0){
                        break;
                    }
                    ExploreNode closestToOrb = unexplored.stream().min(Comparator.comparingInt(ExploreNode::getDistanceToOrb)).get();
                    moveTo(state, closestToOrb.getLongID(), map, pathTaken); //Finally move to neighbour that is closest to orb as why not - also probs more efficient.
                    break;

                } else if (currentNeighbours.stream().allMatch(ExploreNode::isVisited)){

                    Long previousLocation = pathTaken.get(pathTaken.size()-2);
                    state.moveTo(previousLocation);
                    pathTaken.remove(pathTaken.size()-1);


                    Set<ExploreNode> neighboursNow = map.getNeighboursFromParentMap(state.getCurrentLocation());




                    if(neighboursNow.stream().anyMatch(node -> !node.isVisited())){
                        break;
                    }



                }
            }

        }

    }



    public void moveTo(ExplorationState state, long nodeToMoveTo, ParentMap mapToModify, ArrayList<Long> pathTaken) {
        state.moveTo(nodeToMoveTo);
        mapToModify.addNeighbours(nodeToMoveTo, convertNeighboursToSetOfExploreNodes(state, mapToModify));
        pathTaken.add(nodeToMoveTo);
    }

}
