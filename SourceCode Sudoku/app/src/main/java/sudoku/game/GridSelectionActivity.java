package sudoku.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GridSelectionActivity extends AppCompatActivity {

    private Button GridNine;
    private Button GridFour;
    private Button GridSix;
    private Button GridTwelve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_selection);

        Intent intent = getIntent();
        String boardInfo = intent.getStringExtra("board_info");
        String[] split = boardInfo.split("/");

        GridNine = (Button) findViewById(R.id.nine);
        GridNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (split[0].equalsIgnoreCase("Custom") && (WordBank.englishData.size() < 9 && WordBank.frenchData.size() < 9)) {
                    Context context = getApplicationContext();
                    CharSequence text = "Selected File must at least include 9 words";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), SudokuBoardActivity.class);
                    intent.putExtra("boardInfo", boardInfo + "/" + "9");
                    startActivity(intent);
                }
            }
        });

        GridFour = (Button) findViewById(R.id.four);
        GridFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (split[0].equalsIgnoreCase("Custom") && (WordBank.englishData.size() < 4 && WordBank.frenchData.size() < 4)) {
                    Context context = getApplicationContext();
                    CharSequence text = "Selected File must at least include 4 words";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), SudokuBoardActivity.class);
                    intent.putExtra("boardInfo", boardInfo + "/" + "4");
                    startActivity(intent);
                }
            }
        });

        GridSix = (Button) findViewById(R.id.six);
        GridSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (split[0].equalsIgnoreCase("Custom") && (WordBank.englishData.size() < 6 && WordBank.frenchData.size() < 6)) {
                    Context context = getApplicationContext();
                    CharSequence text = "Selected File must at least include 6 words";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), SudokuBoardActivity.class);
                    intent.putExtra("boardInfo", boardInfo + "/" + "6");
                    startActivity(intent);
                }
            }
        });

        GridTwelve = (Button) findViewById(R.id.twelve);
        GridTwelve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (split[0].equalsIgnoreCase("Custom") && (WordBank.englishData.size() < 12 && WordBank.frenchData.size() < 12)) {
                    Context context = getApplicationContext();
                    CharSequence text = "Selected File must at least include 12 words";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), SudokuBoardActivity.class);
                    intent.putExtra("boardInfo", boardInfo + "/" + "12");
                    startActivity(intent);
                }
            }
        });
    }


}