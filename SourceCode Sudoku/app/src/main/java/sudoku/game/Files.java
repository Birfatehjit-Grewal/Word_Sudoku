package sudoku.game;

import java.util.ArrayList;
import java.util.List;

public class Files {
    private List<String> english;
    private List<String> french;
    private String name;

    public Files(String fileName){
        english = new ArrayList<>();
        french = new ArrayList<>();
        name = fileName;
    }

    public void addPair(String eng,String fre){
        english.add(eng);
        french.add(fre);
    }

    public void loadList() {
        for(int i =0;i<english.size();i++){
            WordBank.englishData.add(english.get(i));
            WordBank.frenchData.add(french.get(i));
        }
    }

    public List<String> getEnglish() {
        return english;
    }

    public List<String> getFrench() {
        return french;
    }

    public String getName() {
        return name;
    }
}
