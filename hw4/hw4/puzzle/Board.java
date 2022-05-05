package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {
    private int[][] tiles;
    private int[][] goal;
    private int size;

    public Board(int[][] aTiles) {
        this.tiles=new int[aTiles.length][aTiles[0].length];
        for(int i=0;i<aTiles.length;i++){
            int[] tempArr1=aTiles[i];
            int[] tempArr2=this.tiles[i];
            System.arraycopy(tempArr1,0,tempArr2,0,aTiles[i].length);
        }
        goal = goalInitializer();
        size = size();
    }

    public int tileAt(int i, int j) {
        int len = size / 2;
        if (i < 0 || j < 0 || i > len - 1 || j > len - 1) {
            throw new IndexOutOfBoundsException("row or col is out of bound");
        }
        return tiles[i][j];
    }

    public int size() {
        int size = 0;
        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles[0].length; j++) {
                if (tiles[i][j] != 0) {
                    size += 1;
                }
            }
        }
        return size;
    }

    //copy from http://joshh.ug/neighbors.html
    @Override
    public Iterable<WorldState> neighbors() {
        final int BLANK=0;
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    private int[][] goalInitializer() {
        int number = 1;
        int[][] goal = new int[this.tiles.length][this.tiles[0].length];
        for (int i = 0; i < goal.length; i++) {
            for (int j = 0; j < goal[0].length; j++) {
                if (number <= size) {
                    goal[i][j] = number;
                    number += 1;
                }
            }
        }
        return goal;
    }

    public int hamming() {
        int hammingCount = 0;
        for (int i = 0; i < goal.length; i++) {
            for (int j = 0; j < goal[0].length; j++) {
                if (tiles[i][j] != 0 && tiles[i][j] != goal[i][j]) {
                    hammingCount += 1;
                }
            }
        }
        return hammingCount;
    }

    private int getCorrectRowIndex(int num) {
        return (num - 1) / this.tiles[0].length;
    }

    private int getCorrectColIndex(int num) {
        return (num - 1) % this.tiles[0].length;
    }

    public int manhattan() {
        int manhattanCount = 0;
        for (int i = 0; i < goal.length; i++) {
            for (int j = 0; j < goal[0].length; j++) {
                if (tiles[i][j] != 0 && tiles[i][j] != goal[i][j]) {
                    int currentRowIndex = i;
                    int currentColIndex = j;
                    int correctRowIndex = getCorrectRowIndex(tiles[i][j]);
                    int correctColIndex = getCorrectColIndex(tiles[i][j]);
                    manhattanCount += Math.abs(correctRowIndex - currentRowIndex) +
                            Math.abs(correctColIndex - currentColIndex);
                }
            }
        }
        return manhattanCount;
    }

    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
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
        int N = size;
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


}
