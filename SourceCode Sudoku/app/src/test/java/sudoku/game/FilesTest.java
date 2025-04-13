package sudoku.game;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class FilesTest {

    @Test
    public void getEnglish_isCorrect() {
        Files files = new Files("FileTest");
        for (int i = 0; i < 4; i++) {
            files.addPair("eng", "fre");
        }
        List<String> english = Arrays.asList("eng", "eng", "eng", "eng");
        assertArrayEquals(english.toArray(), files.getEnglish().toArray());
    }

    @Test
    public void getFrench_isCorrect() {
        Files files = new Files("FileTest");
        for (int i = 0; i < 4; i++) {
            files.addPair("eng", "fre");
        }
        List<String> french = Arrays.asList("fre", "fre", "fre", "fre");
        assertArrayEquals(french.toArray(), files.getFrench().toArray());
    }

    @Test
    public void loadList_isCorrect() {
        Files files = new Files("FileTest");
        for (int i = 0; i < 4; i++) {
            files.addPair("eng", "fre");
        }
        files.loadList();
        String[] english = {"eng", "eng", "eng", "eng"};
        assertArrayEquals(english, WordBank.englishData.toArray());
    }

    @Test
    public void getName_isCorrect() {
        Files files = new Files("FileTest");
        assertEquals("FileTest", files.getName());
    }
}