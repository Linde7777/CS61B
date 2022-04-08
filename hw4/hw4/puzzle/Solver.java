package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import org.junit.Test;

public class Solver {
    /*
    Keep a priority queue of “move sequences”.

    Each SearchNode represents one “move sequence”
    as defined in the conceptual description of Best-First Search.
    */
    private MinPQ<String> pq;
    private int moves;

    public Solver(WorldState initial) {
        pq = new MinPQ<>();
        moves = 0;
    }

    public Iterable<WorldState> solution() {
        return null;
    }

    public int moves() {
        return moves;
    }

}

class SearchNode {
    State state;
    //the number of moves made to reach this world state from the initial state.
    private int numMoves = 0;
    private SearchNode prev;

    public SearchNode(String word, String goal) {
        numMoves += prev.numMoves;
        state = this.new State(word, goal);
    }

    class State implements WorldState {
        private String word;
        private String goal;
        private int distance;

        public State(String word, String goal) {
            distance = estimatedDistanceToGoal();
            this.word = word;
            this.goal = goal;
        }


        @Override
        public int estimatedDistanceToGoal() {
            final char visited = '0';
            char[] wordArray = new char[word.length()];
            char[] goalArray = new char[goal.length()];
            for (int i = 0; i < wordArray.length; i++) {
                wordArray[i] = word.charAt(i);
            }
            for (int i = 0; i < goalArray.length; i++) {
                goalArray[i] = goal.charAt(i);
            }

            int sameLetter = 0;
            for (int i = 0; i < wordArray.length; i++) {
                for (int j = 0; j < goalArray.length; j++) {
                    if (wordArray[i] == goalArray[j]) {
                        wordArray[i] = visited;
                        goalArray[j] = visited;
                        sameLetter += 1;
                        break;
                    }
                }
            }
            distance = Math.max(wordArray.length-sameLetter,
                    goalArray.length-sameLetter);

            return distance;
        }



        @Override
        public Iterable<WorldState> neighbors() {
            return null;
        }

        @Override
        public boolean isGoal() {
            return word.equals(goal);
        }
    }
}