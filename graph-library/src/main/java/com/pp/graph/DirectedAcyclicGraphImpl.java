package com.pp.graph;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class DirectedAcyclicGraphImpl extends DirectedAcyclicGraph {

    // If graph can't contain self loops then Set would be a great data structure.
    // Is it weighted or non weighted graph
    Map<Integer, List<Integer>> graph;

    public DirectedAcyclicGraphImpl() {
        graph = new HashMap<>();
    }

    @Override
    public List<Integer> dfs() {
        Stack<Integer> stack = new Stack<>();
        List<Integer> dfsTravesal = new ArrayList<>();
        Map.Entry<Integer, List<Integer>> startNode = graph.entrySet().iterator().next();
        stack.push(startNode.getKey());
        while(!stack.isEmpty()) {
            Integer element = stack.pop();
            dfsTravesal.add(element);
            if(graph.get(element) != null)
                graph.get(element).stream().forEach(a -> stack.push(a));
        }
        return dfsTravesal;
    }

    @Override
    public List<Integer> bfs() {
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> bfsTravesal = new ArrayList<>();
        Map.Entry<Integer, List<Integer>> startNode = graph.entrySet().iterator().next();
        queue.add(startNode.getKey());
        while(!queue.isEmpty()) {
            Integer element = queue.remove();
            bfsTravesal.add(element);
            if(graph.get(element) != null)
                graph.get(element).stream().forEach(a -> queue.add(a));
        }
        return bfsTravesal;
    }

    @Override
    public void addEdge(Integer from, Integer to) {
        List<Integer> edges;
        try {
            if (!hasCycle(to, from)) {
                if (graph.containsKey(from)) {
                    edges = graph.get(from);
                    edges.add(to);
                } else {
                    edges = new ArrayList<>();
                    edges.add(to);
                    graph.put(from, edges);
                }
            }
        }catch (GraphCycleDetectedException e) {
            log.error("Graph cycle has been detected with the addition of new edge");
        }
    }

    @Override
    public Integer getVertices() {
        return graph.size();
    }

    @Override
    public boolean hasCycle(Integer from, Integer to) throws GraphCycleDetectedException {
        if(!graph.containsKey(from))
            return false;
        Stack<Integer> stack = new Stack<>();
        stack.push(from);
        while (!stack.isEmpty()) {
            int element = stack.pop();
            if(element == to)
                throw new GraphCycleDetectedException("Graph Cycle Detected for edge starting from " + from + " to " + to);
            if(graph.get(element) != null)
                graph.get(element).stream().forEach(a -> stack.add(a));
        }
        return false;
    }

}
