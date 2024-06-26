package student;

import game.Edge;
import game.EscapeState;
import game.Node;
import game.Tile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.mockito.Mockito.*;

public class AStarTest {

    private EscapeState mockState;
    private AStar aStar;

    private Node mockNode1;
    private Node mockNode2;
    private Node mockNode3;
    private Node mockNodeExit;

    private Tile mockTile1;
    private Tile mockTile2;
    private Tile mockTile3;
    private Tile mockTileExit;

    private Node currentNode;

    @BeforeEach
    void setUp() {
        mockState = mock(EscapeState.class);
        mockNode1 = mock(Node.class);
        mockNode2 = mock(Node.class);
        mockNode3 = mock(Node.class);
        mockNodeExit = mock(Node.class);

        mockTile1 = mock(Tile.class);
        mockTile2 = mock(Tile.class);
        mockTile3 = mock(Tile.class);
        mockTileExit = mock(Tile.class);

        Edge mockEdge1To2 = mock(Edge.class);
        Edge mockEdge2ToExit = mock(Edge.class);
        Edge mockEdge3ToExit = mock(Edge.class);
        Edge mockEdge1To3 = mock(Edge.class);

        when(mockNode1.getTile()).thenReturn(mockTile1);
        when(mockNode2.getTile()).thenReturn(mockTile2);
        when(mockNode3.getTile()).thenReturn(mockTile3);
        when(mockNodeExit.getTile()).thenReturn(mockTileExit);

        when(mockState.getExit()).thenReturn(mockNodeExit);

        when(mockEdge1To2.length()).thenReturn(5);
        when(mockEdge2ToExit.length()).thenReturn(5);
        when(mockEdge3ToExit.length()).thenReturn(5);
        when(mockEdge1To3.length()).thenReturn(10);

        when(mockNode1.getEdge(mockNode2)).thenReturn(mockEdge1To2);
        when(mockNode2.getEdge(mockNodeExit)).thenReturn(mockEdge2ToExit);
        when(mockNode3.getEdge(mockNodeExit)).thenReturn(mockEdge3ToExit);
        when(mockNode1.getEdge(mockNode3)).thenReturn(mockEdge1To3);

        Collection<Node> nodes = new HashSet<>(Arrays.asList(mockNode1, mockNode2, mockNode3, mockNodeExit));
        aStar = new AStar(nodes);

        /*
         * Track the current node manually
         * Implementation inspired from https://www.baeldung.com/mockito-void-methods
         */
        currentNode = mockNode1;
        when(mockState.getCurrentNode()).thenAnswer(invocation -> currentNode);
        doAnswer(invocation -> {
            currentNode = invocation.getArgument(0);
            return null;
        }).when(mockState).moveTo(any(Node.class));
    }

    @Test
    void testTraversePathWithGold() {
    }

    @Test
    void testTraverseNoGold() {
        when(mockNode1.getNeighbours()).thenReturn(Set.of(mockNode2));
        when(mockNode2.getNeighbours()).thenReturn(Set.of(mockNode1, mockNodeExit));
        when(mockNodeExit.getNeighbours()).thenReturn(Set.of(mockNode2));

        when(mockState.getTimeRemaining()).thenReturn(10);

        aStar.traverse(mockState);

        verify(mockState).moveTo(mockNodeExit);
        verify(mockState, never()).pickUpGold();
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(mockState, mockNode1, mockNode2, mockNode3, mockNodeExit, mockTile1, mockTile2, mockTile3, mockTileExit);
    }
}
