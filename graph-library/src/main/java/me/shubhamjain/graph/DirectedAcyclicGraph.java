package me.shubhamjain.graph;

public abstract class DirectedAcyclicGraph implements Graph {
    abstract boolean hasCycle(Integer from, Integer to) throws GraphCycleDetectedException;
}
