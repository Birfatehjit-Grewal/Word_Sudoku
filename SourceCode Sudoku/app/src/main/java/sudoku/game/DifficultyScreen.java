package sudoku.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class DifficultyScreen extends AppCompatActivity {

    private Button beginnerMode;
    private Button intermediateMode;
    private Button advancedMode;
    private Button customMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_screen);

        Intent intent = getIntent();
        String gameMode = intent.getStringExtra("game_mode");



        beginnerMode = (Button) findViewById(R.id.beginner);
        beginnerMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GridSelectionActivity.class);
                intent.putExtra("board_info", beginnerMode.getText().toString() + "/" + gameMode);
                startActivity(intent);
            }
        });

        intermediateMode = (Button) findViewById(R.id.intermediate);
        intermediateMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GridSelectionActivity.class);
                intent.putExtra("board_info", intermediateMode.getText().toString() + "/" + gameMode);
                startActivity(intent);
            }
        });

        advancedMode = (Button) findViewById(R.id.advanced);
        advancedMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GridSelectionActivity.class);
                intent.putExtra("board_info", advancedMode.getText().toString() + "/" + gameMode);
                startActivity(intent);
            }
        });

        customMode = (Button) findViewById(R.id.custom);
        customMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CustomDifficulty.class);
                intent.putExtra("board_info", customMode.getText().toString() + "/" + gameMode);
                startActivity(intent);
            }
        });

    }


}
