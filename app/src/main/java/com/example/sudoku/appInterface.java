package com.example.sudoku;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.GridLayout;

public class appInterface extends GridLayout {
    EditText[][] board;//2d array of board
    public appInterface(Context context){
        super(context);
        board = new EditText[9][9];
        for (int i = 0; i < 9; i ++){
            for(int j = 0; j < 9; j++){
                //display board in grid layout
                board[i][j] = new EditText(context);
                board[i][j].setBackgroundColor(Color.parseColor("#000000"));
                board[i][j].setTextColor(Color.BLACK);
                board[i][j].setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                board[i][j].setGravity(Gravity.CENTER);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = 160;
                params.height = 190;
                params.rowSpec = GridLayout.spec(i, 1);
                params.columnSpec = GridLayout.spec(j, 1);
                params.topMargin = params.bottomMargin = 1;
                params.leftMargin = params.rightMargin = 1;
                board[i][j].setLayoutParams(params);
                addView(board[i][j]);
            }
        }
    }
    public void drawInitialBoard(int[][] gameBoard){
        //nested loop for displaying contents of 2d int array board
        for (int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if (gameBoard[i][j]==0){
                    //set text to blank if input is 0 so user can edit it
                    board[i][j].setText("");
                    board[i][j].setBackgroundColor(Color.parseColor("#696969"));
                }
                else {
                    //else set background to green and can't be edited
                    board[i][j].setText("" + gameBoard[i][j]);
                    board[i][j].setBackgroundColor(Color.parseColor("#00FF00"));
                    board[i][j].setEnabled(false);
                }
            }
        }
    }
    //returns edit text input at x and y
    public Editable getInput(int x, int y){
       return board[x][y].getText();

    }
    //clears inputs for board at specified area
    public void clear(int x, int y){
        board[x][y].setText("");
    }
    //sets text change handler for edit text board
    public void setTextChangeHandler(TextWatcher textChangeHandler,int x, int y){
        board[x][y].addTextChangedListener(textChangeHandler);
    }
}
