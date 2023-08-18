# Graph Library
Design a graph library with basic functions

## Clarifying Questions
- What would be the data type of vertex ? String, Integer or Composite data structure ?
- Shall we choose BFS or DFS for traversal ?
  - It shows your knowledge about when BFS and DFS are applicable.
- Shall we do recursive BFS/DFS or Iterative BFS/DFS ?
  - That will show your understanding of java memory model(Stack Memory and Heap Memory).
  - How many vertices should I consider at most(1000s, 1 millions, 1 trillion or sky is the limit) ?
- Will our graph have connected components or should I assume graph with one connected components at first ?
- Shall we throw checked/unchecked exception in case of Cycle detected ?
  - That will show your understanding of how exceptional situations are treated in java ecosystem or in general.
- How about thread safety ? (Slightly advanced question)
- Is it weighted or non weighted graph ?
- If graph can't contain self loops then Set would be a great data structure.

## Observations
- I can transform my bfs and dfs into one method and just pass datastructure as an argument and this way
it will be one function serving two functionalities.
- How do I turn my code into supporting the Undirected ACyclic graph as well without changing the implementation
much.