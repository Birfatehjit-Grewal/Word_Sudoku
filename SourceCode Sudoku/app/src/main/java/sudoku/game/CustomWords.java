package sudoku.game;

// This is a helper class for reading CSV data into arrayList
public class CustomWords {


    private String english;
    private String french;
    private String chapter;

    public String getChapter()
    {
        return chapter;
    }

    public void setChapter(String chapter) {this.chapter = chapter;}


    public String getFrench()
    {
        return french;
    }

    public void setFrench(String french)
    {
        this.french = french;
    }


    public String getEnglish()
    {
        return english;
    }

    public void setEnglish(String english)
    {
        this.english = english;
    }

    @Override
    public String toString() {
        return "CustomWords{" +
                "chapter='" + chapter + '\'' +
                ", english='" + english + '\'' +
                ", french='" + french + '\'' +
                '}';
    }
}
