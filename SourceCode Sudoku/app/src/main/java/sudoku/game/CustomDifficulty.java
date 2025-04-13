package sudoku.game;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

public class CustomDifficulty extends AppCompatActivity
{

    private String currentDifficulty;
    private Button csvButton;
    private Button customPlayButton;

    private int STORAGE_PERMISSION_CODE = 1;
    private boolean allowPlayAccess = false;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_difficulty);

        Intent intent = getIntent();
        String gameMode = intent.getStringExtra("board_info");
        currentDifficulty = "Custom";


        // need to implement uploading csv file to app.
        csvButton = (Button) findViewById(R.id.csvUpload);
        csvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (ContextCompat.checkSelfPermission(CustomDifficulty.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(CustomDifficulty.this, "You have already granted this permission!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    requestStoragePermission();
                }
                performFileSearch();


            }
        });
        if (savedInstanceState != null) {
            allowPlayAccess = savedInstanceState.getBoolean("allowPlayAccess");
        }
        enablePlayButton(allowPlayAccess);
        customPlayButton = (Button) findViewById(R.id.customPlay);
        customPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FileSelection.class);
                // Change to Custom when BG implements custom option
                intent.putExtra("board_info",gameMode);
                startActivity(intent);

            }
        });


    }

    // Reworked to read text into custom word bank array
    // Does not work for API 33. Works on API 26
    private void readText(String input) {
        File file = new File(input);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {

                String[] tokens = line.split(",");

                // Read the data
                CustomWords sample = new CustomWords();

                sample.setChapter(tokens[0]);
                sample.setEnglish(tokens[1]);
                sample.setFrench(tokens[2]);

                // add into custom array
                int z = 0;
                int i = 0;
                while(i<WordBank.fileData.size() && z == 0){
                    Log.e("Add words to chapter","Now " +sample.getChapter());
                    if(sample.getChapter().matches(WordBank.fileData.get(i).getName())){
                        WordBank.fileData.get(i).addPair(sample.getEnglish(),sample.getFrench());
                        z = 1;
                    }
                    i++;
                }
                if(z == 0){
                    Files FL = new Files(sample.getChapter());
                    FL.addPair(sample.getEnglish(),sample.getFrench());
                    WordBank.fileData.add(FL);
                    Log.e("Add chapter","Help " + sample.getChapter());
                }

            }
            allowPlayAccess = true;
            br.close();
        } catch (IOException | IndexOutOfBoundsException e) {
            CharSequence text = "CSV format must include 3 columns!";
            int duration = Toast.LENGTH_LONG;
            allowPlayAccess = false;
            enablePlayButton(false);
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        }

    }

    private void enablePlayButton(boolean enable) {
        customPlayButton = (Button) findViewById(R.id.customPlay);
        customPlayButton.setEnabled(allowPlayAccess);
        if (!enable) {
            customPlayButton.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        } else {
            customPlayButton.getBackground().setColorFilter(null);
        }
    }


    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {

            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because of this and that")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(CustomDifficulty.this,
                                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //select file from storage
    private void performFileSearch()
    {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/comma-separated-values");
        //Instead of startActivityForResult use this one
        someActivityResultLauncher.launch(intent);

    }

    //Instead of onActivityResult() method use this one
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK) {
                        // Here, no request code
                        Intent data = result.getData();
                        Uri uri = data.getData();
                        String path = uri.getPath();
                        path = path.substring(path.indexOf(":") + 1);
                        readText(path);
                        if (allowPlayAccess) {
                            enablePlayButton(true);
                            Context context = getApplicationContext();
                            CharSequence text = "File Read Successfully";
                            int duration = Toast.LENGTH_LONG;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }

                    }
                }
            });

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean("allowPlayAccess",allowPlayAccess);
    }
}