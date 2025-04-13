package sudoku.game;

import static android.util.TypedValue.COMPLEX_UNIT_PX;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class SudokuBoardActivity extends AppCompatActivity {
    //Variables to store Board dimensions
    int boardSize;
    int rows;
    int columns;
    int cellHeight;
    int cellWidth;
    //stores the last clicked cell
    int clickedCol = -1;
    int clickedRow = -1;
    //String array to store randomly gotten words and there translations
    String[] words;
    String[] translations;
    //String that store the given board
    String Board;
    //String that holds the answer key
    String answerKey;
    //variables for the board and word menu
    int IntGrid[][];
    boolean givenGrid[][];
    Button Grid[][];
    float textSizeGrid;
    float textSizeWords;
    WordButton[] wordButtons;

    //other variables for buttons and texts on the screen
    private TextView currentDifficulty;
    private Button reRoll;
    Context THIS = this;

    //Variable for Speech
    TextToSpeech Speech;
    Boolean OnSpeech;
    String[] Voice;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku_board);

        Intent intent = getIntent();
        String boardInfo = intent.getStringExtra("boardInfo");
        BoardComputations boardComputations = new BoardComputations(boardInfo);

        GridHelper GH = new GridHelper();
        Boards randomBoard = new Boards();
        if (savedInstanceState != null) {
            boardSize = savedInstanceState.getInt("Size");
            OnSpeech = savedInstanceState.getBoolean("OnSpeech");
            int[] boardDim = randomBoard.getBoardSize(boardSize);
            rows = boardDim[0];
            columns = boardDim[1];
            cellHeight = boardDim[2];
            cellWidth = boardDim[3];
            Board = savedInstanceState.getString("board");
            IntGrid = GH.makeIntGrid(savedInstanceState.getString("Current"),rows,columns);
            givenGrid = GH.makeGivenGrid(savedInstanceState.getString("board"),rows,columns);
            words = savedInstanceState.getStringArray("words");
            answerKey = savedInstanceState.getString("answer");
            translations = savedInstanceState.getStringArray("translations");

        }
        else{
            boardSize = boardComputations.getGridSize();
            OnSpeech = boardComputations.getGameMode();
            String[] boardPair;
            boardPair = randomBoard.getBoard(boardSize);
            int[] boardDim = randomBoard.getBoardSize(boardSize);
            rows = boardDim[0];
            columns = boardDim[1];
            cellHeight = boardDim[2];
            cellWidth = boardDim[3];
            Board = boardPair[0];
            answerKey = boardPair[1];
            WordBank WB = new WordBank();
            String[][] TmpWords = WB.getWords(boardComputations.getDifficultyMode(),boardSize);
            words = WB.getEnglishWords(TmpWords,boardSize);
            translations = WB.getFrenchWords(TmpWords,boardSize);
            IntGrid = GH.makeIntGrid(Board, rows, columns);
            givenGrid = GH.makeGivenGrid(Board,rows,columns);
        }
        Grid = makeButtonGrid(rows,columns);
        if(OnSpeech == true){

            Voice = translations;
            translations = words;
            WordBank WB = new WordBank();
            words = WB.getNumbers(boardSize);
            Speech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int i) {
                    if(i == TextToSpeech.SUCCESS){
                        int result = Speech.setLanguage(Locale.FRENCH);

                        if(result == TextToSpeech.LANG_MISSING_DATA|| result == TextToSpeech.LANG_NOT_SUPPORTED){
                            Log.e("TTS","language not supported");
                        }
                    }
                    else{
                        Log.e("TTS","language not supported");
                    }

                }
            });
        }
        wordButtons = makeWordGrid(cellHeight,cellWidth);
        lockSize(Grid);
        textSizeGrid = getTextSize(words, boardSize);
        textSizeWords = getTextSizeWords(translations, boardSize);
        ShowBoard(rows,columns);
        CheckBoard();
        Grid[0][0].post(new Runnable() {
            @Override
            public void run() {
                textSizeGrid = getTextSize(words, boardSize);
                textSizeWords = getTextSizeWords(translations, boardSize);
                SetTextWords(boardSize);
                SetTextBoard(rows,columns);
            }
        });
        setAll(Grid);

        currentDifficulty = (TextView) findViewById(R.id.difficultyMode);
        currentDifficulty.setText("Difficulty: " + boardComputations.getDifficultyMode());


        reRoll = (Button) findViewById(R.id.reRoll);

        reRoll.setOnClickListener(new View.OnClickListener()
        {
            @Override

            public void onClick(View view)
            {
                Boards randomBoard = new Boards();
                String[] boardPair;
                boardPair = randomBoard.getBoard(boardSize);
                int[] boardDim = randomBoard.getBoardSize(boardSize);
                rows = boardDim[0];
                columns = boardDim[1];
                cellHeight = boardDim[2];
                cellWidth = boardDim[3];
                Board = boardPair[0];
                answerKey = boardPair[1];
                IntGrid = GH.makeIntGrid(Board, rows, columns);
                givenGrid = GH.makeGivenGrid(Board,rows,columns);
                WordBank WB = new WordBank();
                String[][] TmpWords = WB.getWords(boardComputations.getDifficultyMode(),boardSize);
                words = WB.getEnglishWords(TmpWords,boardSize);
                translations = WB.getFrenchWords(TmpWords,boardSize);
                if(OnSpeech == true){

                    Voice = translations;
                    translations = words;
                    words = WB.getNumbers(boardSize);
                    Speech = new TextToSpeech(THIS, new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int i) {
                            if(i == TextToSpeech.SUCCESS){
                                int result = Speech.setLanguage(Locale.FRENCH);

                                if(result == TextToSpeech.LANG_MISSING_DATA|| result == TextToSpeech.LANG_NOT_SUPPORTED){
                                    Log.e("TTS","language not supported");
                                }
                            }
                            else{
                                Log.e("TTS","language not supported");
                            }

                        }
                    });
                }
                updateWordGrid();
                lockSize(Grid);
                setAll(Grid);
                ShowBoard(rows,columns);
                textSizeGrid = getTextSize(words,boardSize);
                textSizeWords = getTextSizeWords(translations, boardSize);
                SetTextBoard(rows,columns);
                SetTextWords(boardSize);
                CheckBoard();
            }
        });
    }
//Sends the need information on rotate
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        if(OnSpeech == true){
            words = translations;
            translations = Voice;
        }
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("Current",getStringBoard(IntGrid));
        savedInstanceState.putStringArray("words",words);
        savedInstanceState.putStringArray("translations",translations);
        savedInstanceState.putString("board",Board);
        savedInstanceState.putString("answer",answerKey);
        savedInstanceState.putInt("Size",boardSize);
        savedInstanceState.putBoolean("OnSpeech",OnSpeech);
    }





// converts an int matrix board to a string board
    public String getStringBoard(int[][] grid){
        String currentGrid = "";
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                currentGrid = currentGrid + String.valueOf(grid[i][j]) + " ";
            }
        }
        return currentGrid;
    }
// changes the activity to HomeScreen
    public void goHomePage()
    {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }

    //stores the location of the clicked button on the grid in to the variables clickedcol and clickedrow
    public void gridButtonClicked(int row, int column)
    {
        if (givenGrid[row][column] == true)
        {
            if(OnSpeech == true) {
                Say(Voice[IntGrid[row][column] - 1]);
            }
            setBackgroundNormal(row, column,Grid);
            return;
        }
        else {
            if (clickedCol != -1 || clickedRow != -1)
            {
                setBackgroundNormal(clickedRow, clickedCol,Grid);
            }
            clickedCol = column;
            clickedRow = row;
            if(OnSpeech == true && IntGrid[clickedRow][clickedCol]!=0){
                Say(Voice[IntGrid[clickedRow][clickedCol] - 1]);
            }
            Grid[row][column].setBackgroundColor(Color.GREEN);
        }

    }

    //uses the values stored in the clickedcol and clickedrow and changes the values and text in them with
    //value and text related to the button clicked in the word menu
    public void wordButtonClicked(int row, int column, int index) {
        if (row == -1 || column == -1)
        {
            return;
        }
        else {
            IntGrid[row][column] = wordButtons[index].getValue();
            Grid[row][column].setTextSize(textSizeGrid);
            Grid[row][column].setText(words[IntGrid[row][column] - 1]);
            Grid[row][column].setTextColor(Color.BLUE);
            setBackgroundNormal(row, column,Grid);
            CheckBoard();
            String currentBoard = "";

            for (int i = 0; i < rows; i++)
            {
                for (int j = 0; j < columns; j++)
                {
                    currentBoard = currentBoard + String.valueOf(IntGrid[i][j]) + " ";
                }
            }
            BoardComputations boardComputations = new BoardComputations(rows, columns);
            boolean check = boardComputations.checkBoard(currentBoard, answerKey);
            if (check == true)
            {
                Toast.makeText(THIS, "The board is Correct!!!", Toast.LENGTH_SHORT).show();
            }
            clickedRow = -1;
            clickedCol = -1;
        }
    }

    // if adding the new backgrounds changes the buttons size this will be used to lock them before changing
    public void lockSize(Button[][] grid)
    {
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                Button bt = grid[i][j];
                int width = bt.getWidth();
                bt.setMinWidth(width);
                bt.setMaxWidth(width);
                int height = bt.getHeight();
                bt.setMinHeight(height);
                bt.setMaxHeight(height);
            }
        }
    }

    // calls the setbackgroundnormal for each box in the grid
    public void setAll(Button[][] grid)
    {
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                setBackgroundNormal(i, j, grid);
            }
        }
    }

    //sets the background for each box depending on the row and column number
    public void setBackgroundNormal(int row, int col, Button[][] grid)
    {
        if ((row) % cellHeight == 0 && (col) % cellWidth == 0 && col != 0 && row != 0)
        {
            grid[row][col].setBackground(getDrawable(R.drawable.black_top_left));
        }
        else if ((row) % cellHeight == 0 && row != 0)
        {
            grid[row][col].setBackground(getDrawable(R.drawable.black_top));
        }
        else if ((col) % cellWidth == 0 && col != 0)
        {
            grid[row][col].setBackground(getDrawable(R.drawable.black_left));
        }
        else
        {
            grid[row][col].setBackground(getDrawable(R.drawable.even_gray));
        }
    }


    // This constructs word menu at bottom of the screen
    public WordButton[] makeWordGrid(int cellHeight,int cellWidth) {
        WordButton[] wordButtons = new WordButton[cellHeight*cellWidth];
        TableLayout wordBank = (TableLayout) findViewById(R.id.word_table);
        Random rn = new Random();
        int value;
        WordBank WB = new WordBank();
        int[] ValuesUsed = new int[boardSize];
        int valueCount = 0 ;

        for (int z = 0; z < cellHeight; z++)
        {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT, 1.0f));

            for (int i = 0; i < cellWidth; i++)
            {
                final int currentCellHeight = z;
                final int currentCellWidth = i;

                Button wordButton = new Button(this);
                wordButton.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1.0f));
                wordButton.setPadding(0, 0, 0, 0);

                wordButton.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        wordButtonClicked(clickedRow, clickedCol, (cellWidth * currentCellHeight + currentCellWidth));
                    }
                });
                tableRow.addView(wordButton);
                value = rn.nextInt(boardSize);
                while(WB.IndexUsed(ValuesUsed,value,valueCount) == false){
                    value = rn.nextInt(boardSize);
                }
                ValuesUsed[valueCount] = value;
                valueCount++;
                wordButtons[cellWidth * currentCellHeight + currentCellWidth] = new WordButton(value+1, this, wordButton, translations);

            }
            wordBank.addView(tableRow);
        }
        return wordButtons;
    }

    //makes the button matrix for the board
    public Button[][] makeButtonGrid(int row, int column){
        Button[][] Grid = new Button[row][column];
        TableLayout boardView = (TableLayout) findViewById(R.id.board_table);
        for (int i = 0; i < row; i++)
        {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT, 1.0f));
            boardView.addView(tableRow);
            for (int j = 0; j < column; j++)
            {
                final int currentColumn = j;
                final int currentRow = i;
                Button wordCell = new Button(this);
                wordCell.setMaxLines(1);
                wordCell.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1.0f));
                wordCell.setPadding(0, 0, 0, 0);

                wordCell.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view) {
                        gridButtonClicked(currentRow, currentColumn);
                    }
                });

                tableRow.addView(wordCell);
                Grid[i][j] = wordCell;
            }
        }
        return Grid;
    }
    //Set the text for the button matrix buttons depending on the int matrix values
    public void ShowBoard(int row,int column){
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if(IntGrid[i][j] == 0){
                    Grid[i][j].setText("");
                }
                else {
                    Grid[i][j].setText(words[IntGrid[i][j]-1]);
                }
            }

        }
    }
    // resizes the text for the board
    public void SetTextBoard(int row,int column){
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                Grid[i][j].setTextSize(textSizeGrid);
            }

        }
    }
    // resizes the text for the word menu
    public void SetTextWords(int Size){
        for(int i=0;i<Size;i++){
                wordButtons[i].getButton().setTextSize(textSizeWords);

        }
    }
    //updates the text for the word menu
    public void updateWordGrid(){
        for(int i =0;i<boardSize;i++){
            wordButtons[i].getButton().setText(translations[wordButtons[i].getValue()-1]);
        }
    }

    //return a good size for the grid text by using the button size in the grid and the length of the longest word
    public int getTextSize(String[] words,int boardSize){
        int width = Grid[0][0].getWidth();
        int size = 40;
        double constant = 1.8;
        if (boardSize == 12) {
            constant = 1.3;
        }
        if (OnSpeech == true){
            constant = 1.0;
        }
        for(int i =0;i<boardSize;i++){
            String word = words[i];
            int stringLength = word.length();
            if (stringLength <= 5) {
                stringLength = 8;
            }
            if(width/(stringLength*constant) < size){
                size = (int)(width/(stringLength*constant));
            }
        }
            return size;
    }
    //return a good size for the word menu text by using the button size in the word menu and the length of the longest word
    public int getTextSizeWords(String[] translations,int boardSize){
        int width = wordButtons[0].getButton().getWidth();
        int size = 40;
        for(int i =0;i<boardSize;i++){
            String word = translations[i];
            int stringLength = word.length();
            if (stringLength <= 5) {
                stringLength = 8;
            }
            double constant = 2.0;
            if(width/(stringLength*constant) < size){
                size = (int)(width/(stringLength*constant));
            }
        }
        return size;
    }
    // checks if the place values are in line with normal sudoku and recolors the place value red if they don't and blue if they do
    public void CheckBoard(){
        BoardComputations boardComputations = new BoardComputations(rows, columns);
        for(int i=0;i<rows;i++) {
            for (int j = 0; j < columns; j++) {
                if(givenGrid[i][j]==true){
                    Grid[i][j].setTextColor(Color.BLACK);
                }
                int checkrow = boardComputations.checkRow(i, j, rows, IntGrid);
                if (checkrow != -1) {
                    if (givenGrid[i][j] != true) {
                        Grid[i][j].setTextColor(Color.RED);
                    }
                    if (givenGrid[i][checkrow] != true) {
                        Grid[i][checkrow].setTextColor(Color.RED);
                    }
                }
                int checkColumn = boardComputations.checkColumn(i, j, rows, IntGrid);
                if (checkColumn != -1) {
                    if (givenGrid[i][j] != true) {
                        Grid[i][j].setTextColor(Color.RED);
                    }
                    if (givenGrid[checkColumn][j] != true) {
                        Grid[checkColumn][j].setTextColor(Color.RED);
                    }
                }
                int[] checkCell = boardComputations.checkCell(i,j,cellWidth,cellHeight,rows,IntGrid);
                if (checkCell[0] != -1 && checkCell[1]!= -1) {
                    if (givenGrid[i][j] != true) {
                        Grid[i][j].setTextColor(Color.RED);
                    }
                    if (givenGrid[checkCell[0]][checkCell[1]] != true) {
                        Grid[checkCell[0]][checkCell[1]].setTextColor(Color.RED);
                    }
                }
                if(checkrow == -1 && checkColumn == -1 && checkCell[0] ==-1 && checkCell[1]==-1){
                    if (givenGrid[i][j] != true) {
                        Grid[i][j].setTextColor(Color.BLUE);
                    }
                }
            }

        }
    }
    public void Say(String Word){
        Speech.setPitch(1.0f);
        Speech.setSpeechRate(0.8f);
        Speech.speak(Word,TextToSpeech.QUEUE_FLUSH,null);
    }
}