package lab11.graphs;


import edu.princeton.cs.algs4.Queue;

/**
 * @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
    }

    /**
     * Conducts a breadth first search of the maze starting at the source.
     */
    private void bfs(int s) {
        if (s == t) {
            targetFound = true;
            return;
        }

        Queue<Integer> q = new Queue<>();
        distTo[s] = 0;
        marked[s] = true;
        q.enqueue(s);
        announce();

        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : maze.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                    announce();
                }
            }
        }

    }


    @Override
    public void solve() {
        bfs(s);
    }
}

