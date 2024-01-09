package me.shubhamjain.graph;

import java.util.List;

public interface Graph {
    List<Integer> dfs();

    List<Integer> bfs();

    void addEdge(Integer a, Integer b);

    Integer getSourceVertices();
}
