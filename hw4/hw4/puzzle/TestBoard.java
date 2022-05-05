package hw4.puzzle;

import org.junit.Test;

import java.awt.image.PackedColorModel;

import static org.junit.Assert.*;

public class TestBoard {
    @Test
    public void testTileAt() {
        int[][] tiles = new int[3][3];
        tiles[0][0] = 8;
        tiles[0][1] = 1;
        tiles[0][2] = 3;
        tiles[1][0] = 4;
        tiles[1][1] = 0;
        tiles[1][2] = 2;
        tiles[2][0] = 7;
        tiles[2][1] = 6;
        tiles[2][2] = 5;
        /*
        8 1 3
        4   2
        7 6 5
        */
        Board board = new Board(tiles);
        int expected = 8;
        int actual = board.tileAt(0, 0);
        assertEquals(expected, actual);

        int expected1 = 6;
        int actual1 = board.tileAt(2, 1);
        assertEquals(expected1, actual1);

        int expected2 = 5;
        int actual2 = board.tileAt(2, 2);
        assertEquals(expected2, actual2);
    }

    @Test
    public void testHamming() {
        int[][] tiles = new int[3][3];
        tiles[0][0] = 8;
        tiles[0][1] = 1;
        tiles[0][2] = 3;
        tiles[1][0] = 4;
        tiles[1][1] = 0;
        tiles[1][2] = 2;
        tiles[2][0] = 7;
        tiles[2][1] = 6;
        tiles[2][2] = 5;
        /*
        8 1 3
        4   2
        7 6 5
        */
        Board board = new Board(tiles);
        int except = 5;
        int actual = board.hamming();
        assertEquals(except, actual);
    }

    @Test
    public void testManhattan() {
        int[][] tiles = new int[3][3];
        tiles[0][0] = 8;
        tiles[0][1] = 1;
        tiles[0][2] = 3;
        tiles[1][0] = 4;
        tiles[1][1] = 0;
        tiles[1][2] = 2;
        tiles[2][0] = 7;
        tiles[2][1] = 6;
        tiles[2][2] = 5;
        /*
        8 1 3
        4   2
        7 6 5
        */

        Board board = new Board(tiles);
        int expect = 10;
        int actual = board.manhattan();
        assertEquals(expect, actual);
    }

    @Test
    public void testEqual() {
        int[][] tiles = new int[3][3];
        tiles[0][0] = 8;
        tiles[0][1] = 1;
        tiles[0][2] = 3;
        tiles[1][0] = 4;
        tiles[1][1] = 0;
        tiles[1][2] = 2;
        tiles[2][0] = 7;
        tiles[2][1] = 6;
        tiles[2][2] = 5;
        /*
        8 1 3
        4   2
        7 6 5
        */
        Board board1 = new Board(tiles);
        Board board2 = new Board(tiles);
        boolean expect = true;
        boolean actual = board1.equal(board2);
        assertEquals(expect, actual);

        int[][] tiles1 = new int[3][3];
        tiles1[0][0] = 1;
        tiles1[0][1] = 8;
        tiles1[0][2] = 4;
        tiles1[1][0] = 3;
        tiles1[1][1] = 0;
        tiles1[1][2] = 2;
        tiles1[2][0] = 7;
        tiles1[2][1] = 6;
        tiles1[2][2] = 5;
        Board board3 = new Board(tiles1);
        boolean expected1 = false;
        boolean actual1 = board3.equal(board1);
        assertEquals(expected1, actual1);
    }

    @Test
    public void verifyImmutability() {
        int r = 2;
        int c = 2;
        int[][] x = new int[r][c];
        int cnt = 0;
        for (int i = 0; i < r; i += 1) {
            for (int j = 0; j < c; j += 1) {
                x[i][j] = cnt;
                cnt += 1;
            }
        }
        Board b = new Board(x);
        assertEquals("Your Board class is not being initialized with the right values.", 0, b.tileAt(0, 0));
        assertEquals("Your Board class is not being initialized with the right values.", 1, b.tileAt(0, 1));
        assertEquals("Your Board class is not being initialized with the right values.", 2, b.tileAt(1, 0));
        assertEquals("Your Board class is not being initialized with the right values.", 3, b.tileAt(1, 1));

        x[1][1] = 1000;
        assertEquals("Your Board class is mutable and you should be making a copy of the values in the passed tiles array. Please see the FAQ!", 3, b.tileAt(1, 1));
    }

    @Test
    public void testNeighbors() {
        int[][] tiles = new int[3][3];
        tiles[0][0] = 8;
        tiles[0][1] = 1;
        tiles[0][2] = 3;
        tiles[1][0] = 4;
        tiles[1][1] = 0;
        tiles[1][2] = 2;
        tiles[2][0] = 7;
        tiles[2][1] = 6;
        tiles[2][2] = 5;
        /*
        8 1 3
        4   2
        7 6 5
        */
        Board board = new Board(tiles);
        var res = board.neighbors();
        System.out.println(res.toString());
    }
}
