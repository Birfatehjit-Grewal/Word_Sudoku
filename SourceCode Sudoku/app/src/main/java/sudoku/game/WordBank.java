package sudoku.game;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WordBank<WordData>
{
    // Arrays that hold custom data
    public static List<String> englishData = new ArrayList<>();
    public static List<String> frenchData = new ArrayList<>();

    public static List<Files> fileData = new ArrayList<>();

    // Arrays for pre-made difficulties
    // Change to read from csv instead of hardcode later

    public static List<String> englishDataBeginner = new ArrayList<>(Arrays.asList("Sun", "Hello", "Run", "Water",
            "Walk", "Sleep", "Sit", "Hen", "Egg","Dishes","Mom","King","Horse","Goodbye","yes","no","girl","boy",
            "woman","man","love","time","day","world","reason","Can","Cat","Dog","red",
            "blue","green","yellow","left","right","top","down"));
    public static List<String> frenchDataBeginner = new ArrayList<>(Arrays.asList("Soleil", "Bonjour", "Courir",
            "Eau", "Marche", "Dormir", "Asseoir", "Poule", "Oeuf","Vaisselle", "Tigre","Roi","Cheval","Au revoir","Oui","Non",
            "Fille","Garçon","Femme","Homme","Amour","Temps","Jour","Monde","Raison","boîte","Chat","Chien",
            "rouge", "bleu", "vert", "jaune", "gauche", "droite", "haut", "bas"));

    public static List<String> englishDataIntermediate = new ArrayList<>(Arrays.asList("Can", "Man", "Kill",
            "Water", "Book", "Sleep", "Money", "Hen", "Black", "Snow", "Cheese","Singer","Depart"
            , "urban", "vacate", "verdict", "verge", "vow", "mimic", "deplete", "deposit", "designate", "campaign"
            , "capacity", "capital", "civic", "clarity", "Sky" , "Light" , "Door" , "Wind" , "Storm" , "House" , "Knife" , "Head" , "Feet" , "Doctor" , "History" ,
            "Rest" , "Grass" , "Dark" , "Shadow"));
    public static List<String> frenchDataIntermediate = new ArrayList<>(Arrays.asList("boîte", "Homme", "Tuer",
            "Eau", "Livre", "Dormir", "Argent", "Poule", "Noire", "Neiger", "Fromage", "Chanteuse","déroger", "Urbain",
            "quitter", "jugement", "bord", "vœu", "imiter", "épuiser", "verser", "désigner", "campagne",
            "capacité", "Capitale", "civique", "clarté", "Ciel" , "Lumière" , "Porte" , "Vent", "Tempête" , "Maison" , "Couteau" ,
            "Tête" , "Pieds" , "Médecin" , "Histoire" , "Reste" , "Herbe" , "Sombre" , "Ombre"));

    public static List<String> englishDataAdvanced = new ArrayList<>(Arrays.asList("stretch" ,
            "subtle" , "successful" , "summarize" , "suppress" , "surgeon" , "island" , "jealousy" ,
            "lightning" , "livestock" , "glow" , "luxury" , "tyranny" , "unanimous" ,
            "unpleasant" , "varies" , "villain","Heart", "Lungs", "Thumb", "Nose", "Helm", "Ant", "Blind",
            "Clean", "Week", "Weak", "Raven", "Monster", "Queen", "Laugh", "Cry", "Good", "Bad"));
    public static List<String> frenchDataAdvanced = new ArrayList<>(Arrays.asList("étirer" ,
            "subtil" , "réussi" , "résumer" , "réprimer" , "chirurgien" , "île" , "jalousie", "foudre" ,
            "bétail" , "lueur" , "luxe" , "tyrannie" , "unanime" , "désagréable" ,
            "varie", "méchant","Coeur", "Poumons", "Pouce", "Nez", "Barre", "Fourmi", "Aveugle", "Nettoyer",
            "Semaine", "Faible", "Corbeau", "Monstre", "Reine", "Rire", "Cri", "Bons", "Mauvaise"));

    // return the translations and the english words randomly chosen for the selected difficulty
    public String[][] getWords(String difficulty,int boardSize) {
        int Wordcount = 0;
        String[][] Words = new String[2][boardSize];
        int[] usedIndex = new int[boardSize];
        Random rn = new Random();
        while (Wordcount < boardSize) {
            if (difficulty.matches("Beginner")) {
                int size = englishDataBeginner.size();
                int size2 = frenchDataBeginner.size();
                int wordindex;
                if (size < size2) {
                    wordindex = rn.nextInt(size);
                }
                else {
                    wordindex = rn.nextInt(size2);
                }
                if(IndexUsed(usedIndex,wordindex,Wordcount) == true){
                    Words[0][Wordcount] = englishDataBeginner.get(wordindex);
                    Words[1][Wordcount] = frenchDataBeginner.get(wordindex);
                    usedIndex[Wordcount]=wordindex;
                    Wordcount++;
                }
            } else if (difficulty.matches("Intermediate")) {
                int size = englishDataIntermediate.size();
                int size2 = frenchDataIntermediate.size();
                int wordindex;
                if (size < size2) {
                    wordindex = rn.nextInt(size);
                }
                else {
                    wordindex = rn.nextInt(size2);
                }
                if(IndexUsed(usedIndex,wordindex,Wordcount) == true){
                    Words[0][Wordcount] = englishDataIntermediate.get(wordindex);
                    Words[1][Wordcount] = frenchDataIntermediate.get(wordindex);
                    usedIndex[Wordcount]=wordindex;
                    Wordcount++;
                }

            } else if (difficulty.matches("Advanced")) {
                int size = englishDataAdvanced.size();
                int size2 = frenchDataAdvanced.size();
                int wordindex;
                if (size < size2) {
                    wordindex = rn.nextInt(size);
                }
                else {
                    wordindex = rn.nextInt(size2);
                }
                if(IndexUsed(usedIndex,wordindex,Wordcount) == true){
                    Words[0][Wordcount] = englishDataAdvanced.get(wordindex);
                    Words[1][Wordcount] = frenchDataAdvanced.get(wordindex);
                    usedIndex[Wordcount]=wordindex;
                    Wordcount++;
                }

            } else {
                int size = englishData.size();
                int size2 = frenchData.size();
                int wordindex;
                if (size < size2) {
                    wordindex = rn.nextInt(size);
                }
                else {
                    wordindex = rn.nextInt(size2);
                }
                if(IndexUsed(usedIndex,wordindex,Wordcount) == true){
                    Words[0][Wordcount] = englishData.get(wordindex);
                    Words[1][Wordcount] = frenchData.get(wordindex);
                    usedIndex[Wordcount]=wordindex;
                    Wordcount++;
                }
            }
        }
        return Words;
    }
    // makes sure the same index is not randomly chosen again
    public boolean IndexUsed(int[] indexs,int index,int size){
        while(size>0){
            if(index == indexs[size-1]){
                return false;
            }
            size--;
        }
        return true;
    }
    // returns the array of english words
    public String[] getEnglishWords(String[][] words,int size){
        String[] EnglishWords = new String[size];
        for(int i = 0;i<size;i++){
            EnglishWords[i] = words[0][i];
        }
        return EnglishWords;
    }
    // returns the array of french words
    public String[] getFrenchWords(String[][] words,int size){
        String[] FrenchWords = new String[size];
        for(int i = 0;i<size;i++){
            FrenchWords[i] = words[1][i];
        }
        return FrenchWords;
    }

    public String[] getNumbers(int size){
        String[] Numbers = new String[size];
        for(int i =1;i<=size;i++){
            Numbers[i-1] = String.valueOf(i);
        }
        return Numbers;
    }
}
