package student;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExploreNodeTest {

    private ParentMap testMap = new ParentMap();
    private ExploreNode nodeOneForTest;
    private ExploreNode nodeTwoForTest;
    private ExploreNode nodeToAdd;

    @BeforeEach
    public void setUp(){

        nodeOneForTest = new ExploreNode(0L, 99, testMap);
        nodeTwoForTest = new ExploreNode(2L, 99, testMap);


        testMap.addNeighbours(1L, Set.of(nodeOneForTest, nodeTwoForTest));

    }

    @Test
    void testIfAddingAlreadyPresentNeighbourResultsInVisitedBeingTrue(){

       nodeToAdd = new ExploreNode(0L, 99, testMap); //Already in the neighbours BUT not a key

        testMap.addNeighbours(3L, Set.of(nodeToAdd));

        for (ExploreNode singleNode : testMap.getNeighboursFromParentMap(3L)){
            assertTrue(singleNode.isVisited());
        }
    }

    @Test
    void testIfAddingNotPresentNeighbourResultsInVisitedBeingFalse(){
        nodeToAdd = new ExploreNode(99L, 99, testMap); //Neighbour has not been added to the map in any form (as a key or neighbour)

        testMap.addNeighbours(3L, Set.of(nodeToAdd));

        for (ExploreNode singleNode : testMap.getNeighboursFromParentMap(3L)){
            assertFalse(singleNode.isVisited());
        }
    }

    @Test
    void testIfAddingPresentKeyResultsInVisitedBeingTrue(){
        nodeToAdd = new ExploreNode(1L, 99, testMap); //All that matters is if the longID that is added is the same as any of the keys in the map (which atm is only 1L, so 1L is "the present key")

        testMap.addNeighbours(3L, Set.of(nodeToAdd));

        for(ExploreNode singleNode : testMap.getNeighboursFromParentMap(3L)){
            assertTrue(singleNode.isVisited());
        }
    }

    @Test
    void testIfAddingNotPresentKeyAndNotPresentNeighbourResultsInVisitedBeingFalse(){
        nodeToAdd = new ExploreNode(99L, 99, testMap); //This node is not present

        testMap.addNeighbours(98L, Set.of(nodeToAdd)); //This nodeIDKey is also not present

        for(ExploreNode singleNode : testMap.getNeighboursFromParentMap(98L)){
            assertFalse(singleNode.isVisited());
        }

    }



    @AfterEach
    public void tearDown(){
        testMap = null;
        nodeOneForTest = null;
        nodeTwoForTest = null;
        nodeToAdd = null;
    }


}
