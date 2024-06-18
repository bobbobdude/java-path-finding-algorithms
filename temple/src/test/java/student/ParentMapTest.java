package student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ParentMapTest {

    private ParentMap testMap = new ParentMap();
    private ExploreNode nodeOneForTest;
    private ExploreNode nodeTwoForTest;
    private ExploreNode nodeToAdd;



    @BeforeEach
    public void setUp() {

        nodeOneForTest = new ExploreNode(0L, 99, testMap);
        nodeTwoForTest = new ExploreNode(2L, 99, testMap);
    }

    @Test
    void testIfGetAllKeysInMapReturnsAllKeys(){
        testMap.addNeighbours(1L, Set.of(nodeOneForTest, nodeTwoForTest));
        assertEquals(testMap.getAllKeysInMap(), Set.of(1L));
        testMap.addNeighbours(2L, Set.of(nodeOneForTest, nodeTwoForTest));
        assertEquals(testMap.getAllKeysInMap(), Set.of(1L, 2L));
    }

    @Test
    void testIfGetAllNodesInMapReturnsAllNeighbours(){
        testMap.addNeighbours(1L, Set.of(nodeOneForTest));
        assertEquals(testMap.getAllNodesInMap(), Set.of(nodeOneForTest));

        testMap.addNeighbours(2L, Set.of(nodeTwoForTest));
        assertEquals(testMap.getAllNodesInMap(), Set.of(nodeOneForTest, nodeTwoForTest));

    }

    @Test
    void testIfAddingEntryWhereNoKeyExistsUsingAddNeighbours(){
        testMap.addNeighbours(1L, Set.of(nodeOneForTest, nodeTwoForTest));

        for(long singleKeyInMap : testMap.getAllKeysInMap()) {
            assertEquals(1L, singleKeyInMap);
        }
    }

    @Test
    void testIfAddingEntryWhereKeyDoesExistUsingAddNeighbours(){
        testMap.addNeighbours(1L, Set.of(nodeOneForTest));

        nodeToAdd = new ExploreNode(99L, 99, testMap);

        testMap.addNeighbours(1L, Set.of(nodeOneForTest, nodeToAdd));

        for(ExploreNode singleNode: testMap.getNeighboursFromParentMap(1L)){
            assertEquals(singleNode.getLongID(), 0L);
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
