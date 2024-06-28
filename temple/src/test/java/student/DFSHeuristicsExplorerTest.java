package student;

import game.ExplorationState;
import game.NodeStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class DFSHeuristicsExplorerTest {

    private ExplorationState mockState;
    private DFSHeuristicsExplorer dfsHeuristicsExplorer;
    private ParentMap mockMap;
    private NodeStatus mockNodeStatus2;
    private NodeStatus mockNodeStatus3;
    private NodeStatus mockNodeStatus4;
    private NodeStatus mockNodeStatus5;


    @BeforeEach
    void setUp() {
        mockState = Mockito.mock(ExplorationState.class);
        dfsHeuristicsExplorer = new DFSHeuristicsExplorer();
        mockMap = Mockito.mock(ParentMap.class);
        mockNodeStatus2 = Mockito.mock(NodeStatus.class);
        mockNodeStatus3 = Mockito.mock(NodeStatus.class);
        mockNodeStatus4 = Mockito.mock(NodeStatus.class);
        mockNodeStatus5 = Mockito.mock(NodeStatus.class);

    }

    @Test
    void testFindOrbWhenTargetReachedEverythingStops(){
        when(mockState.getDistanceToTarget()).thenReturn(0);
        dfsHeuristicsExplorer.findOrb(mockState);

        verify(mockState, Mockito.never()).getNeighbours(); //As we are at our goal we should never try to getNeighbours again -
        verify(mockState, Mockito.never()).moveTo(anyLong()); //Or move to another tile
    }

    @Test
    void testFindOrbWhenThereIsClearPathToTarget() {
        when(mockNodeStatus2.nodeID()).thenReturn(2L);
        when(mockNodeStatus3.nodeID()).thenReturn(3L);
        when(mockNodeStatus4.nodeID()).thenReturn(4L);
        when(mockNodeStatus5.nodeID()).thenReturn(5L);

        when(mockState.getCurrentLocation()).thenReturn(1L, 2L, 3L, 4L, 5L);
        when(mockState.getDistanceToTarget()).thenReturn(4, 3, 2, 1, 0);
        when(mockState.getNeighbours()).thenReturn(
                List.of(mockNodeStatus2),
                List.of(mockNodeStatus3),
                List.of(mockNodeStatus4),
                List.of(mockNodeStatus5),
                List.of()
        );

        dfsHeuristicsExplorer.findOrb(mockState);

        verify(mockState, times(1)).moveTo(2L);
        verify(mockState, times(1)).moveTo(3L);
        verify(mockState, times(1)).moveTo(4L);
        verify(mockState, times(1)).moveTo(5L);
    }


    @Test
    void testFindOrbGoesToOnlyNeighbour(){

        NodeStatus mockNodeStatus2 = mock(NodeStatus.class);

        when(mockNodeStatus2.nodeID()).thenReturn(2L);

        when(mockState.getCurrentLocation()).thenReturn(1L);
        when(mockState.getDistanceToTarget()).thenReturn(1,0);
        when(mockState.getNeighbours()).thenReturn(List.of(mockNodeStatus2));

        dfsHeuristicsExplorer.findOrb(mockState);

        verify(mockState, times(1)).moveTo(2L);
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(mockState, mockMap, mockNodeStatus2, mockNodeStatus3, mockNodeStatus4, mockNodeStatus5);
    }
}
