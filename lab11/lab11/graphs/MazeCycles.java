package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    public MazeCycles(Maze m) {
        super(m);
    }

    @Override
    public void solve() {
        // TODO: Your code here!
    }

    private void dfs(int v){
        distTo[v]=0;
        edgeTo[v]=0;
        dfsHelper(v);
    }

    private void dfsHelper(int v){
        marked[v]=true;
        announce();

        for(int w:maze.adj(v)){
            //TODO: detect circle
            if(!marked[w]){
                distTo[w]=distTo[v]+1;
                edgeTo[w]=v;
                marked[w]=true;
                announce();
                dfsHelper(w);
            }
        }
    }
}

