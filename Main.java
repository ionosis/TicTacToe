import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        TicTacToe game = new TicTacToe();

        int moveCount = 1; // distinguishes player turn. odd is X, even is O

        game.start(); // this just prints a blank board
        // game loop here
        while (!game.validWin() || !game.setDraw(moveCount)) { // initialize game loop
            // first move, additional moves.
            String move = in.nextLine(); // take user input

            if (game.moveIsValid(move)) { // if move isnt valid, error msg and user input expected

                game.printMove(moveCount, move); // w valid input mark the board
                game.printBoard(); // print updated board
                ++moveCount; // increase count for player token
            }

            if (game.validWin()) {
                break; // exit program
            } else if (game.setDraw(moveCount)) {
                break; // exit program
            }

        }
    }
}


class TicTacToe {

    public int column;
    public int row;
    public char[][] array = new char[3][3];

    public void start() {
        // populating the board. this was needed in a previous stage of developement.
        // is it needed still?

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                array[i][j] = ' ';
            }
        }
        printBoard();
    }

    public void printBoard() {
        // This can be optimized by including the pipe and hyphen characters in the loop

        String h = "---------";
        char pipe = '|';

        System.out.println(h);
        for (int i = 0; i < array.length; i++) { // for-each maybe?
            System.out.print(pipe + " ");
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.print(pipe);
            System.out.println();
        }
        System.out.println(h);
    }

    public boolean moveIsValid(String move) {
        boolean flag = true;
        if (!canParseToInt(move)) {
            flag = false;
        } else if (!coordinatesAreValid(column, row)) {
            flag = false;
        } else if (!cellAvailable(column, row)) {
            flag = false;
        }
        return flag;
    }

    public boolean canParseToInt(String move) {
        try {
            move = move.replaceAll(" ", "");
            column = Integer.parseInt(move.substring(0, 1));
            row = Integer.parseInt(move.substring(1, 2));
            if (row == 1) {
                row = 3;
            } else if (row == 3) {
                row = 1;
            }
            --row;
            --column;
            return true;

        } catch (NumberFormatException numberFormatException) {
            System.out.println("You should enter numbers");
            return false;
        }
    }

    public boolean coordinatesAreValid(int column, int row) {
        boolean flag = true;
        if (column > 2 || row > 2) {
            System.out.println("Coordinates should be from 1 to 3!");
            System.out.println(column + " " + row);
            flag = false;
        }
        return flag;
    }

    public boolean cellAvailable(int column, int row) {
        boolean flag = false;
        if (array[row][column] != 32) {
            System.out.println("This cell is occupied! Choose another one!");

        } else {
            flag = true;
        }
        return flag;
    }

    public void printMove(int moveCount, String move) {

        System.out.println("Enter the coordinates: " + move);
        if (moveCount % 2 != 0) {
            array[row][column] = 'X';
        } else {
            array[row][column] = 'O';
        }
    }

    public boolean validWin() {
        boolean winner = false;
        String oWins = "O wins";
        String xWins = "X wins";

        //Win by row
        for (int i = 0; i < array.length; i++) {
            int rowSum = 0;
            for (int j = 0; j < array[i].length; j++) {
                rowSum += array[i][j];
            }
            if (rowSum == 264) {
                System.out.println(xWins);
                winner = true;

            } else if (rowSum == 237) {
                System.out.println(oWins);
                winner = true;
            }
        }
        // winByColumn
        for (int i = 0; i < array.length; i++) {
            int colSum = 0;
            for (int j = 0; j < array.length; j++) {
                colSum += array[j][i];

            }
            if (colSum == 237) {
                System.out.println(oWins);
                winner = true;

            } else if (colSum == 264) {
                System.out.println(xWins);
                winner = true;
            }
        }
        // winByPrimaryDiagonal
        int diagSum = 0;
        for (int i = 0; i < array.length; ++i) {

            for (int j = 0; j < array[i].length; j++) {
                if (i == j) {
                    diagSum += array[i][j];
                }
            }
            if (diagSum == 237) {
                System.out.println(oWins);
                winner = true;

            } else if (diagSum == 264) {
                System.out.println(xWins);
                winner = true;
            }
        }
        // winBySecondaryDiagonal
        int diagSum2 = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (i + j == array[i].length - 1) {
                    diagSum2 += array[i][j];
                }
            }
        }
        if (diagSum2 == 264) {
            System.out.println(xWins);
            winner = true;

        } else if (diagSum2 == 237) {
            System.out.println(oWins);
            winner = true;
        }
        return winner;
    }

    public boolean setDraw(int moveCount) {
        boolean draw = false;

        if (!validWin() && moveCount == 10) {
            System.out.println("Draw");
            draw = true;
        }
        return draw;
    }
}


