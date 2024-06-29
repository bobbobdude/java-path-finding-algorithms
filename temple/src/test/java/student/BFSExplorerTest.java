package student;

import game.ExplorationState;
import game.NodeStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class BFSExplorerTest {

    private ExplorationState mockState;
    private BFSExplorer bfsExplorer;
    private ParentMap mockMap;
    private NodeStatus mockNodeStatus2;
    private NodeStatus mockNodeStatus3;
    private NodeStatus mockNodeStatus4;
    private NodeStatus mockNodeStatus5;



    @BeforeEach
    void setUp() {
        mockState = Mockito.mock(ExplorationState.class);
        bfsExplorer = new BFSExplorer();
        mockMap = Mockito.mock(ParentMap.class);
        mockNodeStatus2 = Mockito.mock(NodeStatus.class);
        mockNodeStatus3 = Mockito.mock(NodeStatus.class);
        mockNodeStatus4 = Mockito.mock(NodeStatus.class);
        mockNodeStatus5 = Mockito.mock(NodeStatus.class);

    }

    @Test
    void testFindOrbWhenTargetReachedEverythingStops(){
        Mockito.when(mockState.getDistanceToTarget()).thenReturn(0);
        bfsExplorer.findOrb(mockState);

        Mockito.verify(mockState, Mockito.never()).getNeighbours(); //As we are at our goal we should never try to getNeighbours again -
        Mockito.verify(mockState, Mockito.never()).moveTo(Mockito.anyLong()); //Or move to another tile
    }


    @Test
    void testFindOrbGoesToOnlyNeighbour(){

        NodeStatus mockNodeStatus2 = Mockito.mock(NodeStatus.class);

        Mockito.when(mockNodeStatus2.nodeID()).thenReturn(2L);

        Mockito.when(mockState.getCurrentLocation()).thenReturn(1L);
        Mockito.when(mockState.getDistanceToTarget()).thenReturn(1,0);
        Mockito.when(mockState.getNeighbours()).thenReturn(List.of(mockNodeStatus2));

        bfsExplorer.findOrb(mockState);

        Mockito.verify(mockState, Mockito.times(1)).moveTo(2L);
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(mockState, mockMap, mockNodeStatus2, mockNodeStatus3, mockNodeStatus4, mockNodeStatus5);
    }
}
