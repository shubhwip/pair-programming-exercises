package me.shubhamjain.graph;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class DirectedAcyclicGraphImpl extends DirectedAcyclicGraph {

    private final Map<Integer, List<Integer>> graph;

    public DirectedAcyclicGraphImpl() {
        graph = new HashMap<>();
    }

    @Override
    public List<Integer> dfs() {
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        return graph.keySet()
                .stream()
                .filter(k -> !visited.contains(k))
                .map(key-> {
                    stack.push(key);
                    while(!stack.isEmpty()) {
                        Integer element = stack.pop();
                        if(!visited.contains(element)) {
                            visited.add(element);
                            if (graph.get(element) != null)
                                graph.get(element).stream().forEach(a -> stack.push(a));
                        }
                    }
                     return visited;
                })
                .flatMap(a -> a.stream())
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> bfs() {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        return graph.keySet()
                .stream()
                .filter(k -> !visited.contains(k))
                .map(key-> {
                    queue.add(key);
                    while(!queue.isEmpty()) {
                        Integer element = queue.remove();
                        if(!visited.contains(element)) {
                            visited.add(element);
                            if (graph.get(element) != null)
                                graph.get(element).stream().forEach(a -> queue.add(a));
                        }
                    }
                    return visited;
                })
                .flatMap(a -> a.stream())
                .distinct()
                .collect(Collectors.toList());
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
    public Integer getSourceVertices() {
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
