package com.example.sudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

public class MainActivity extends AppCompatActivity {
    game game;
    appInterface appInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initialize game and appinterface
        game = new game();
        appInterface = new appInterface(this);
        setContentView(appInterface);

        //draw board
        appInterface.drawInitialBoard(game.getBoard());

        //attach text change handlers for each edit text
        for (int i = 0; i < 9; i ++){
            for (int j = 0; j < 9;j++){
                TextChangeHandler temp = new TextChangeHandler(i, j);
                appInterface.setTextChangeHandler(temp, i, j);
            }
        }

    }
    private class TextChangeHandler implements TextWatcher{
        private int x,y;

        public TextChangeHandler(int x, int y) {
            this.x = x;
            this.y = y;
        }
            public void beforeTextChanged (CharSequence s,int start, int count, int after){

            }

            @Override
            public void onTextChanged (CharSequence s,int start, int before, int count){

            }

            @Override
            public void afterTextChanged (Editable s){
                String input = appInterface.getInput(x, y).toString();//get input from edit text and turn it to string
                if (input.equals("")) {
                    game.set(0, x, y);//if empty place 0 at x and y
                } else if (input.equals("0")) {// if zero place 0 at x and y
                    game.set(0, x, y);
                    appInterface.clear(x, y);
                } else if (input.length() > 1) {//cannot enter integer greater than 9
                    game.set(0, x, y);
                    appInterface.clear(x, y);
                } else {
                     int numInput = Integer.parseInt(input);//convert to integer input
                    if (game.check(numInput, x, y)) {
                        game.set(numInput, x, y);//if input can be placed there set input at x and y
                    } else {
                        game.set(0, x, y);
                        appInterface.clear(x, y);
                    }
                }
            }
    }
}