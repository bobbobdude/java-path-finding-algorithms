package student;

import game.ExplorationState;
import game.NodeStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;


public class DFSHelperTest {

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
        //TODO
    }

    @Test
    void testConvertNeighboursToLongs(){
        //TODO
    }



}
