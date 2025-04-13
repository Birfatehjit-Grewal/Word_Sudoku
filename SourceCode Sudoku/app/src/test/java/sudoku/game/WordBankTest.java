package sudoku.game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class WordBankTest {
    private String difficultyBeginner;
    private String difficultyIntermediate;
    private String difficultyAdvanced;
    private String difficultyCustom;

    private int boardSize;
    private WordBank wordBank;

    private String[][] wordsBeginner;
    private String[][] wordsIntermediate;
    private String[][] wordsAdvanced;
    private String[][] wordsCustom;

    @Before
    public void initialSetup() {
        difficultyBeginner = "Beginner";
        difficultyIntermediate = "Intermediate";
        difficultyAdvanced = "Advanced";
        difficultyCustom = "Custom";
        boardSize = 9;
        wordBank = new WordBank();

        wordsBeginner = wordBank.getWords(difficultyBeginner, boardSize);
        wordsIntermediate = wordBank.getWords(difficultyIntermediate, boardSize);
        wordsAdvanced = wordBank.getWords(difficultyAdvanced, boardSize);

        for (int i = 0; i <boardSize; i++) {
            WordBank.englishData.add("test");
            WordBank.frenchData.add("test");
        }

        wordsCustom = wordBank.getWords(difficultyCustom, boardSize);
    }

    @Test
    public void getWords_isCorrect() {

        String[][] wordsBeginner = wordBank.getWords(difficultyBeginner, boardSize);
        String[][] wordsIntermediate = wordBank.getWords(difficultyIntermediate, boardSize);
        String[][] wordsAdvanced = wordBank.getWords(difficultyAdvanced, boardSize);

        for (int i = 0; i <boardSize; i++) {
            WordBank.englishData.add("test");
            WordBank.frenchData.add("test");
        }
        String[][] wordsCustom = wordBank.getWords(difficultyCustom, boardSize);
        assertEquals(2, wordsBeginner.length);
        assertEquals(2, wordsIntermediate.length);
        assertEquals(2, wordsAdvanced.length);
        assertEquals(2, wordsCustom.length);

    }

    @Test
    public void getEnglishWords_isCorrect() {
        assertEquals(9, wordBank.getEnglishWords(wordsBeginner, boardSize).length);
        assertEquals(9, wordBank.getEnglishWords(wordsIntermediate, boardSize).length);
        assertEquals(9, wordBank.getEnglishWords(wordsAdvanced, boardSize).length);
        assertEquals(9, wordBank.getEnglishWords(wordsCustom, boardSize).length);
    }

    @Test
    public void getFrenchWords_isCorrect() {
        assertEquals(9, wordBank.getFrenchWords(wordsBeginner, boardSize).length);
        assertEquals(9, wordBank.getFrenchWords(wordsIntermediate, boardSize).length);
        assertEquals(9, wordBank.getFrenchWords(wordsAdvanced, boardSize).length);
        assertEquals(9, wordBank.getFrenchWords(wordsCustom, boardSize).length);
    }

    @Test
    public void getNumbers_isCorrect() {
        WordBank wordBank = new WordBank();
        String[] result = wordBank.getNumbers(9);
        String[] expected = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        assertArrayEquals(expected, result);
    }

}