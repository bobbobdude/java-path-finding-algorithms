package student;

import game.ExplorationState;
import game.NodeStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class DFSExplorerHelperTest {

    private ExplorationState mockState;
    private NodeStatus mockNodeStatus1;
    private NodeStatus mockNodeStatus2;

    @BeforeEach
    void setUp() {
        mockState = Mockito.mock(ExplorationState.class);
        mockNodeStatus1 = Mockito.mock(NodeStatus.class);
        mockNodeStatus2 = Mockito.mock(NodeStatus.class);
    }

    @Test
    void testAmIStuckWhenNotStuck(){
        Mockito.when(mockNodeStatus1.nodeID()).thenReturn(1L);
        Mockito.when(mockNodeStatus2.nodeID()).thenReturn(2L);
        Mockito.when(mockState.getNeighbours()).thenReturn(Arrays.asList(mockNodeStatus1,mockNodeStatus2));

        Set<Long> visited = new HashSet<>(List.of(1L));

        boolean result = DFSHelper.amIStuck(mockState, visited);

        assertFalse(result, "The explorer should not be stuck as not all neighbors are visited yet.");

    }

    @Test
    void testAmIStuckWhenStuck(){
        Mockito.when(mockNodeStatus1.nodeID()).thenReturn(1L);
        Mockito.when(mockNodeStatus2.nodeID()).thenReturn(2L);
        Mockito.when(mockState.getNeighbours()).thenReturn(Arrays.asList(mockNodeStatus1, mockNodeStatus2));

        Set<Long> visited = new HashSet<>(List.of(1L, 2L));
        boolean result = DFSHelper.amIStuck(mockState, visited);
        assertTrue(result, "The explorer should be stuck as all neighbors are visited.");
    }

    @Test
    void testConvertNeighboursToLongs(){
        Mockito.when(mockNodeStatus1.nodeID()).thenReturn(1L);
        Mockito.when(mockNodeStatus2.nodeID()).thenReturn(2L);

        Collection<NodeStatus> nodeStatuses= Arrays.asList(mockNodeStatus1, mockNodeStatus2);

        Mockito.when(mockState.getNeighbours()).thenReturn(nodeStatuses);

        Collection<Long> result = DFSHelper.convertNeighboursToLongs(mockState);

        assertEquals(Arrays.asList(1L, 2L), result, "The nodeID list should be the same as the nodeID's that are manually added.");

    }

    //Implemented tear down after each test as per Johno's recommendation
    @AfterEach
    void tearDown() {
        Mockito.reset(mockState, mockNodeStatus1, mockNodeStatus2);
    }



}
