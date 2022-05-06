
package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.HashSet;

public class Solver {
    private final int totalmoves;
    MinPQ<SearchNode> pq = new MinPQ<>();
    private final HashSet<WorldState> path = new HashSet<>();
    private final ArrayList<WorldState> solution=new ArrayList<>();

    public Iterable<WorldState> solution() {
        return solution;
    }

    public int moves() {
        return totalmoves;
    }

    private static class SearchNode implements Comparable<SearchNode> {
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
        //int debugIndex = 1;

        while (true) {

            SearchNode formerMinNode = pq.delMin();
            if (formerMinNode.worldState.isGoal()) {
                totalmoves = formerMinNode.numMoves;
                generateSolution(formerMinNode);
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

    private void generateSolution(SearchNode goal){
        ArrayList<WorldState> reservedSolution=new ArrayList<>();
        while(goal!=null){
            reservedSolution.add(goal.worldState);
            goal=goal.prev;
        }

        int i=0;
        while(reservedSolution.size()>0){
            solution.add(reservedSolution.remove(i));
        }

    }
}
