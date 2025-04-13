package sudoku.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameModeActivity extends AppCompatActivity {

    private Button readingMode;
    private Button listeningMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);

        readingMode = (Button) findViewById(R.id.reading);
        readingMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DifficultyScreen.class);
                intent.putExtra("game_mode", "reading");
                startActivity(intent);
            }
        });

        listeningMode = (Button) findViewById(R.id.listening);
        listeningMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DifficultyScreen.class);
                intent.putExtra("game_mode", "listening");
                startActivity(intent);
            }
        });
    }
}