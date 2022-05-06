
package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.HashSet;

public class Solver {
    private MinPQ<SearchNode> pq = new MinPQ<>();
    private int totalmoves=0;
    private HashSet<WorldState> path = new HashSet<>();
    int debugIndex = 1;

    public Iterable<WorldState> solution() {
        return path;
    }

    public int moves() {
        return totalmoves;
    }

    private class SearchNode implements Comparable<SearchNode> {
        WorldState worldState;
        int numMoves;
        SearchNode prev;
        int priority;

        SearchNode(WorldState worldState, int numMoves, SearchNode prev) {
            this.worldState = worldState;
            this.prev = prev;
            this.numMoves = numMoves;
            priority = this.numMoves + this.worldState.estimatedDistanceToGoal();
        }

        @Override
        public int compareTo(SearchNode searchNode) {
            return this.priority - searchNode.priority;
        }

    }

    public Solver(WorldState initial) {

        pq.insert(new SearchNode(initial, 0, null));

        while (true) {

            SearchNode formerMinNode = pq.delMin();
            if (formerMinNode.worldState.isGoal()) {
                totalmoves = formerMinNode.numMoves;
                return;
            }

            for (WorldState neighborState : formerMinNode.worldState.neighbors()) {
                if (formerMinNode.prev == null
                        || !neighborState.equals(formerMinNode.prev.worldState)) {
                    pq.insert(new SearchNode(neighborState,
                            formerMinNode.numMoves + 1, formerMinNode));
                }
            }

            path.add(formerMinNode.worldState);
            //System.out.println(debugIndex + " th " + formerMinNode.worldState.toString());
            //debugIndex += 1;

        }
    }

}
