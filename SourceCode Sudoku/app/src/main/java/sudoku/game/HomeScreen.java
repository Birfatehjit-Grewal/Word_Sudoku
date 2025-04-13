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

public class HomeScreen extends AppCompatActivity {
    private Button setGameModeButton;
    private Button gameRulesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);


        setGameModeButton = (Button) findViewById(R.id.play);
        setGameModeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openGameModeTab();
            }
        });

        gameRulesButton = (Button) findViewById(R.id.rules);
        gameRulesButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                openGameRulesTab();
            }
        });
    }

    public void openGameModeTab()
    {
        Intent intent = new Intent(this, GameModeActivity.class);
        startActivity(intent);
    }

    public void openGameRulesTab()
    {
        Intent intent = new Intent(this, GameRulesScreen.class);
        startActivity(intent);
    }


}