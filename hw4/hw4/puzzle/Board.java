package hw4.puzzle;

public class Board implements WorldState {
    int[][] tiles;
    int[][] goal;

    public Board(int[][] tiles) {
        this.tiles = tiles;
        goal=goalInitializer();
    }

    public int tileAt(int i, int j) {
        return tiles[i][j];
    }

    public int size() {
        int size=0;
        for(int i=0;i<this.tiles.length;i++){
            for(int j=0;j<this.tiles[0].length;j++){
                if(tiles[i][j]!=0){
                    size+=1;
                }
            }
        }
        return size;
    }

    //TODO:
    @Override
    public Iterable<WorldState> neighbors() {
        return null;
    }

    private int[][] goalInitializer(){
        int number=1;
        int[][] goal=new int[this.tiles.length][this.tiles[0].length];
        for(int i=0;i<goal.length;i++){
            for(int j=0;j<goal[0].length;j++){
                if(number<size()){
                    goal[i][j]=number;
                    number+=1;
                }
            }
        }
        return goal;
    }

    public int hamming(){
        int hammingCount=0;
        for(int i=0;i<goal.length;i++){
            for(int j=0;j<goal[0].length;j++){
                if(tiles[i][j]!=goal[i][j]){
                    hammingCount+=1;
                }
            }
        }
        return hammingCount;
    }

    private int findRowIndex(int num){
        //TODO: optimize
        for(int i=0;i<goal.length;i++){
            for(int j=0;j<goal[0].length;j++){
                if(goal[i][j]==num);
                return i;
            }
        }
        return -1;
    }

    private int findColIndex(int num){
        //TODO: optimize
        for(int i=0;i<goal.length;i++){
            for(int j=0;j<goal[0].length;j++){
                if(goal[i][j]==num);
                return j;
            }
        }
        return -1;
    }

    public int manhattan(){
        int manhattanCount=0;
        for(int i=0;i<goal.length;i++){
            for(int j=0;j<goal[0].length;j++){
                if(tiles[i][j]!=goal[i][j]){
                    int currentRowIndex=i;
                    int currentColIndex=j;
                    int correctRowIndex=findRowIndex(tiles[i][j]);
                    int correctColIndex=findColIndex(tiles[i][j]);
                    manhattanCount+=Math.abs(correctRowIndex-currentRowIndex)+
                            Math.abs(correctColIndex-currentColIndex);
                }
            }
        }
        return manhattanCount;
    }

    public boolean equal(Object y) {
        Board anotherBoard = (Board) y;

        if (this.tiles.length != anotherBoard.tiles.length
                || this.tiles[0].length != anotherBoard.tiles[0].length) {
            return false;
        }

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                if (tiles[i][j] != anotherBoard.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

    @Override
    public int estimatedDistanceToGoal() {
        return 0;
    }

}
