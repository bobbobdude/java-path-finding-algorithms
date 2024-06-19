package student;

import game.ExplorationState;
import game.NodeStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

public class BFSExplorerHelperTest {

    private ExplorationState mockState;
    private ParentMap testMap = new ParentMap();
    private ParentMap testMap2 = new ParentMap();
    private NodeStatus mockNodeStatus1;
    private NodeStatus mockNodeStatus2;

    @BeforeEach
    void setUp() {
        mockNodeStatus1 = Mockito.mock(NodeStatus.class);
        mockNodeStatus2 = Mockito.mock(NodeStatus.class);

        mockState = Mockito.mock(ExplorationState.class);
        when(mockState.getNeighbours()).thenReturn(Arrays.asList(mockNodeStatus1,mockNodeStatus2));

        Mockito.when(mockNodeStatus1.nodeID()).thenReturn(98L);
        Mockito.when(mockNodeStatus1.distanceToTarget()).thenReturn(98);


        Mockito.when(mockNodeStatus2.nodeID()).thenReturn(99L);
        Mockito.when(mockNodeStatus2.distanceToTarget()).thenReturn(99);

    }

    @Test
    public void testConvertNeighboursToSetOfExploreNodes() {
        Set<ExploreNode> testExploreNodes = BFSHelper.convertNeighboursToSetOfExploreNodes(mockState, testMap);

        //Manually create correct ExploreNodes
        ExploreNode exploreNode1 = new ExploreNode(98L, 98, testMap2);
        ExploreNode exploreNode2 = new ExploreNode(99L, 99, testMap2); //An empty test map, so they are not ignored (not sure if necessary)

        assertEquals(testExploreNodes, Set.of(exploreNode1,exploreNode2));
    }

    @AfterEach
    void tearDown() {
        testMap = null;
        testMap2 = null;
        mockNodeStatus1 = null;
        mockNodeStatus2 = null;
        reset(mockState);
    }
}
