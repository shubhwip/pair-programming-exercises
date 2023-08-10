package com.pp;

import com.pp.graph.DirectedAcyclicGraphImpl;
import com.pp.graph.Graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class GraphTests {

    // Always assume client is dumb
    // In terms of initializing things data structures etc.
    // Build something which exposes little to client and be useful to them directly
    Graph graph;

    @BeforeEach
    void setUp() {
        graph = new DirectedAcyclicGraphImpl();
    }

    // Graph Creation
    @Test
    void givenAnEdge_whenAddEdgeIsCalled_thenEdgeShouldBeAdded() {
        graph.addEdge(1, 2);
        Assertions.assertEquals(1, (int)graph.getVertices());
    }

    @Test
    void givenAnEdge_whenAddEdgeIsCalled_thenEdgeShouldNotBeAdded() {
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        Assertions.assertEquals(2, (int)graph.getVertices());
    }

    // Traversal
    @Test
    void givenAGraph_whenDFSTraversalIsCalled_thenReturnsDFSTraversal() {
        graph.addEdge(1, 2);
        graph.addEdge(2, 4);
        graph.addEdge(1, 3);
        Assertions.assertEquals(Arrays.asList(1,3,2,4), graph.dfs());
    }

    // Traversal
    @Test
    void givenAGraph_whenBFSTraversalIsCalled_thenReturnsBFSTraversal() {
        graph.addEdge(1, 2);
        graph.addEdge(2, 4);
        graph.addEdge(1, 3);
        Assertions.assertEquals(Arrays.asList(1,2,3,4), graph.bfs());
    }

}
