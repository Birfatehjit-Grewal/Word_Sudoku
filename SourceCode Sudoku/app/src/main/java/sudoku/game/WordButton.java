package sudoku.game;

import static androidx.appcompat.content.res.AppCompatResources.getDrawable;

import android.content.Context;
import android.widget.Button;

public class WordButton {
    private int value;
    private Button button;

    public WordButton(int itValue, Context THIS, Button bt, String[] translations)
    {
        value = itValue;
        button = bt;
        button.setBackground(getDrawable(THIS, R.drawable.square));
        button.setText(translations[value - 1]);
    }
    
    public int getValue() {
        return value;
    }
    public Button getButton() {
        return button;
    }
    public void setButton(Button button) {
        this.button = button;
    }

}
