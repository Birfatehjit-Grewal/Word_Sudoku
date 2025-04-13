package sudoku.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class FileSelection extends AppCompatActivity {

    CheckBox[] CBs;

    Button Next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_selection);

        Intent intent = getIntent();
        String boardInfo = intent.getStringExtra("board_info");
        String[] split = boardInfo.split("/");

        CBs = new CheckBox[WordBank.fileData.size()];
        TableLayout boardView = (TableLayout) findViewById(R.id.fileBoard);
        for(int i =0;i<WordBank.fileData.size();i++){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT, 1.0f));
            boardView.addView(tableRow);
            CheckBox CB = new CheckBox(this);
            CBs[i] = CB;
            Log.e("chapter name","Name :" + String.valueOf(i) +" "+WordBank.fileData.get(i).getName());
            CB.setText(WordBank.fileData.get(i).getName());
            tableRow.addView(CB);
        }

        Next = (Button)findViewById(R.id.customNext);
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextButton();
                if(WordBank.englishData.size()>=4){
                    Intent intent = new Intent(getApplicationContext(), GridSelectionActivity.class);
                    intent.putExtra("board_info", boardInfo);
                    System.out.println(WordBank.englishData.toString());
                    System.out.println(WordBank.frenchData.toString());
                    startActivity(intent);
                }
                else{
                    Context context = getApplicationContext();
                    CharSequence text = "Must Select File With At Least 4 Words";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
    }

    public void nextButton(){
        WordBank.englishData.clear();
        WordBank.frenchData.clear();
        for(int i =0;i<WordBank.fileData.size();i++){
            if(CBs[i].isChecked()){
                WordBank.fileData.get(i).loadList();
            }
        }
    }
}