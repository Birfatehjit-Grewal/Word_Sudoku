package sudoku.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class CustomWordsTest {

    @Test
    public void getFrench_isCorrect() {
        CustomWords customWords = new CustomWords();
        customWords.setFrench("bonjour");
        assertEquals("bonjour", customWords.getFrench());
    }

    @Test
    public void getEnglish_isCorrect() {
        CustomWords customWords = new CustomWords();
        customWords.setEnglish("Hello");
        assertEquals("Hello", customWords.getEnglish());
    }

    @Test
    public void toString_isCorrect() {
        CustomWords customWords = new CustomWords();
        customWords.setFrench("bonjour");
        customWords.setEnglish("Hello");
        customWords.setChapter("chapter1");
        assertEquals("CustomWords{chapter='chapter1', english='Hello', french='bonjour'}", customWords.toString());
    }

    @Test
    public void getChapter_isCorrect() {
        CustomWords customWords = new CustomWords();
        customWords.setChapter("Chapter 1");
        assertEquals("Chapter 1", customWords.getChapter());

    }
}