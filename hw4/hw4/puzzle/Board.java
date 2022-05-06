package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {
    private int[][] tiles;
    private final int BLANK = 0;
    private int estimatedDist;

    public Board(int[][] aTiles) {
        this.tiles = new int[aTiles.length][aTiles[0].length];
        for (int i = 0; i < aTiles.length; i++) {
            int[] tempArr1 = aTiles[i];
            int[] tempArr2 = this.tiles[i];
            System.arraycopy(tempArr1, 0, tempArr2, 0, aTiles[i].length);
        }
        estimatedDist = -1;
    }

    public int tileAt(int i, int j) {
        int len = tiles.length;
        if (i < 0 || j < 0 || i > len - 1 || j > len - 1) {
            throw new IndexOutOfBoundsException("row or col is out of bound");
        }
        return tiles[i][j];
    }

    public int size() {
        return tiles.length;
    }

    //copy from http://joshh.ug/neighbors.html
    @Override
    public Iterable<WorldState> neighbors() {
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

    /*
        1 2 3   00 01 02
        4 5 6   10 11 12
        7 8 0   20 21 22

        col*i+j
        3*i+j
    */
    public int hamming() {
        int hammingCount = 0;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                //Do not compare the bottom right element
                //because it is BLANK
                if (i == size() - 1 && j == size() - 1) {
                    break;
                }
                if (tiles[i][j] != i * size() + j + 1) {
                    hammingCount += 1;
                }
            }
        }
        return hammingCount;
    }

    public int manhattan() {
        int manhattanCount = 0;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {

                if (tiles[i][j] != BLANK && tiles[i][j] != i * size() + j + 1) {
                    int correctRowIndex = (tiles[i][j] - 1) / size();
                    int correctColIndex = (tiles[i][j] - 1) % size();
                    manhattanCount += Math.abs(correctRowIndex - i)
                            + Math.abs(correctColIndex - j);
                }
            }
        }
        return manhattanCount;
    }

    @Override
    public int estimatedDistanceToGoal() {
        if (estimatedDist == -1) {
            estimatedDist = manhattan();
        }
        return estimatedDist;
    }

    @Override
    public boolean equals(Object y) {
        if (y == this) {
            return true;
        }
        if (y == null) {
            return false;
        }
        if (y.getClass() != this.getClass()) {
            return false;
        }

        Board anotherBoard = (Board) y;
        if (this.size() != anotherBoard.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (this.tiles[i][j] != anotherBoard.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
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


}
