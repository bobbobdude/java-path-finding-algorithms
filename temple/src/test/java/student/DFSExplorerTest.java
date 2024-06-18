package student;

import game.ExplorationState;
import game.NodeStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.mockito.Mockito.*;

public class DFSExplorerTest {

    private ExplorationState mockState;
    private DFSExplorer dfsExplorer;
    private NodeStatus mockNodeStatus2;
    private NodeStatus mockNodeStatus3;
    private NodeStatus mockNodeStatus4;
    private NodeStatus mockNodeStatus5;

    @BeforeEach
    void setUp() {
        mockState = Mockito.mock(ExplorationState.class);
        dfsExplorer = new DFSExplorer();
        mockNodeStatus2 = Mockito.mock(NodeStatus.class);
        mockNodeStatus3 = Mockito.mock(NodeStatus.class);
        mockNodeStatus4 = Mockito.mock(NodeStatus.class);
        mockNodeStatus5 = Mockito.mock(NodeStatus.class);

    }

    @Test
    void testBeginDFSWhenTargetReachedEverythingStops(){
        when(mockState.getDistanceToTarget()).thenReturn(0);
        dfsExplorer.beginDFS(mockState);

        verify(mockState, Mockito.never()).getNeighbours(); //As we are at our goal we should never try to getNeighbours again -
        verify(mockState, Mockito.never()).moveTo(anyLong()); //Or move to another tile
    }

    /**
     * Link to maze Representation that we are testing here:
     * <a href="https://i.postimg.cc/jq84qzZY/Screenshot-2024-06-03-123229.png">...</a>
     */


    @Test
    void testBeginDFSWhenThereIsClearPathToTarget() {

        when(mockNodeStatus2.nodeID()).thenReturn(2L);
        when(mockNodeStatus3.nodeID()).thenReturn(3L);
        when(mockNodeStatus4.nodeID()).thenReturn(4L);
        when(mockNodeStatus5.nodeID()).thenReturn(5L);

        when(mockState.getCurrentLocation()).thenReturn(1L);
        when(mockState.getDistanceToTarget()).thenReturn( 4,3,2, 1, 0);
        when(mockState.getNeighbours()).thenReturn(
                List.of(mockNodeStatus2),
                List.of(mockNodeStatus3),
                List.of(mockNodeStatus4),
                List.of(mockNodeStatus5)
        );

        dfsExplorer.beginDFS(mockState);

        verify(mockState, times(1)).moveTo(5L);


    }

    @Test
    void testBeginDFSGoesToOnlyNeighbour(){

        NodeStatus mockNodeStatus2 = mock(NodeStatus.class);

        when(mockNodeStatus2.nodeID()).thenReturn(2L);

        when(mockState.getCurrentLocation()).thenReturn(1L);
        when(mockState.getDistanceToTarget()).thenReturn(1,0);
        when(mockState.getNeighbours()).thenReturn(List.of(mockNodeStatus2));

        dfsExplorer.beginDFS(mockState);

        verify(mockState, times(1)).moveTo(2L);
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(mockState, mockNodeStatus2, mockNodeStatus3, mockNodeStatus4, mockNodeStatus5);
    }
}

