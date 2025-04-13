package sudoku.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class GameRulesScreen extends AppCompatActivity {
//    private Button setDifficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_rules_screen);

        ListView gameRules =  (ListView) findViewById(R.id.listView);
        gameRules.setDivider(null);
        ArrayList<String> list = new ArrayList<String>();
        list.add("Sudoku grid consists of 9x9 spaces.");
        list.add("You can use only numbers from 1 to 9.");
        list.add("Each 3x3 block can only contain numbers from 1 to 9.");
        list.add("Each vertical column can only contain numbers form 1 to 9.");
        list.add("Each horizontal row can only contain numbers from 1 to 9.");
        list.add("Each number in the 3x3 block, vertical column or horizontal row can be used only once.");
        list.add("The game is over when the whole Sudoku grid correctly filled with numbers.");
        list.add("For custom game CSV file must have 3 columns, first is chapter, second is English word, third is French translation");

        ListAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_view, list);
        gameRules.setAdapter(adapter);

    }
}