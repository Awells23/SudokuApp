package com.example.sudoku;

public class game {
    int[][] board;

    public game(){
        Sudoku sk = new Sudoku();
        board = sk.generate();//initialize board and generate from sudoku class
    }
    public void set(int value, int x, int y)
    {//sets value inputted to a place on the board at x and y
        board[x][y] = value;
    }
    public boolean check(int value, int x, int y){
        int a, b, i, j;
        //checks rows and columns of 3 x 3 area to see if input can be placed at x and y on board
        for (j = 0; j < 9; j++)
            if (value == board[x][j])
                return false;

        for (i = 0; i < 9; i++)
            if (value == board[i][y])
                return false;

        a = (x/3)*3; b = (y/3)*3;
        for (i = 0; i < 3; i++)
            for (j = 0; j < 3; j++)
                if  ((a + i != x) && (b + j != y) && value == board[a+i][b+j])
                    return false;

        return true;
    }

    public int[][] getBoard() {
        return board;
    }//returns board for appinterface
}

