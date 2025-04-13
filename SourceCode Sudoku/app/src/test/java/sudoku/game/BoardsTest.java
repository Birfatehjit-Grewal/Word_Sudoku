package sudoku.game;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;

public class BoardsTest {

    @Test
    public void getBoard_isCorrect() {
        int boardSizeNine = 9;
        Boards boards = new Boards();
        assertNotNull(boards.getBoard(boardSizeNine));

        int boardSizeFour = 4;
        assertNotNull(boards.getBoard(boardSizeFour));

        int boardSizeSix = 6;
        assertNotNull(boards.getBoard(boardSizeSix));

        int boardSizeTwelve = 12;
        assertNotNull(boards.getBoard(boardSizeTwelve));
    }

    @Test
    public void getBoardSize_isCorrect() {
        int boardSizeNine = 9;
        int boardSizeFour = 4;
        int boardSizeSix = 6;
        int boardSizeTwelve = 12;
        Boards boards = new Boards();
        int[] resultNine = boards.getBoardSize(boardSizeNine);
        assertEquals(9, resultNine[0]);
        assertEquals(9, resultNine[1]);
        assertEquals(3, resultNine[2]);
        assertEquals(3, resultNine[3]);

        int[] resultSix = boards.getBoardSize(boardSizeSix);
        assertEquals(6, resultSix[0]);
        assertEquals(6, resultSix[1]);
        assertEquals(2, resultSix[2]);
        assertEquals(3, resultSix[3]);

        int[] resultFour = boards.getBoardSize(boardSizeFour);
        assertEquals(4, resultFour[0]);
        assertEquals(4, resultFour[1]);
        assertEquals(2, resultFour[2]);
        assertEquals(2, resultFour[3]);

        int[] resultTwelve = boards.getBoardSize(boardSizeTwelve);
        assertEquals(12, resultTwelve[0]);
        assertEquals(12, resultTwelve[1]);
        assertEquals(3, resultTwelve[2]);
        assertEquals(4, resultTwelve[3]);
    }
}