package sudoku.game;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;

public class GridHelperTest {

    @Test
    public void makeIntGrid_isCorrect() {
        GridHelper gridHelper = new GridHelper();
        String board = "8 4 ? ? ? ? ? ? ? ? ? 1 "+
                "? 11 ? 1 4 ? 9 8 5 ? 7 ? "+
                "? ? 3 10 5 ? 6 1 ? 12 9 ? "+
                "? ? ? ? ? 4 3 ? 9 ? ? ? "+
                "4 ? 7 3 1 ? ? 12 ? 11 2 ? "+
                "11 ? 8 9 ? 5 ? 6 ? 3 ? 7 "+
                "? ? ? ? ? 7 10 4 ? 2 ? 3 "+
                "1 ? ? 8 ? 6 ? ? ? ? 4 5 "+
                "? 2 6 4 ? ? ? ? 11 8 10 ? "+
                "? ? ? ? 6 12 ? 3 ? 4 5 9 "+
                "? 12 ? ? ? ? 4 11 10 1 ? ? "+
                "3 6 ? 2 ? ? ? ? 12 ? 8 ? ";
        int row = 12;
        int column = 12;
        int[][] expected = {
                {8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {0, 11, 0, 1, 4, 0, 9, 8, 5, 0, 7, 0},
                {0, 0, 3, 10, 5, 0, 6, 1, 0, 12, 9, 0},
                {0, 0, 0, 0, 0, 4, 3, 0, 9, 0, 0, 0},
                {4, 0, 7, 3, 1, 0, 0, 12, 0, 11, 2, 0},
                {11, 0, 8, 9, 0, 5, 0, 6, 0, 3, 0, 7},
                {0, 0, 0, 0, 0, 7, 10, 4, 0, 2, 0, 3},
                {1, 0, 0, 8, 0, 6, 0, 0, 0, 0, 4, 5},
                {0, 2, 6, 4, 0, 0, 0, 0, 11, 8, 10, 0},
                {0, 0, 0, 0, 6, 12, 0, 3, 0, 4, 5, 9},
                {0, 12, 0, 0, 0, 0, 4, 11, 10, 1, 0, 0},
                {3, 6, 0, 2, 0, 0, 0, 0, 12, 0, 8, 0}
                };
        int[][] result = gridHelper.makeIntGrid(board, row, column);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                assertEquals(expected[i][j], result[i][j]);
            }
        }
    }

    @Test
    public void makeGivenGrid_isCorrect() {
        String board = "? 4 ? ? "+
                "? 1 ? ? "+
                "? ? 2 ? "+
                "? ? 1 ? ";
        int row = 4;
        int column = 4;
        GridHelper gridHelper = new GridHelper();
        boolean[][] expected = {{false, true, false, false},
                {false, true, false, false},
                {false, false, true, false},
                {false, false, true, false}};
        boolean[][] result = gridHelper.makeGivenGrid(board, row, column);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                assertEquals(expected[i][j], result[i][j]);
            }
        }

    }

}