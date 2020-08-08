import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        TicTacToe game = new TicTacToe();

        int moveCount = 1; // distinguishes player turn. odd is X, even is O
        String move = "";


        game.start();
        // game loop here

        while (!game.validWin() || !game.setDraw()) {

            // first move
            while (!game.moveIsValid(move)) {
                move = in.next();


                game.printMove(moveCount);

                game.printBoard();

                ++moveCount;

                if (game.validWin()) {
                    break;
                } else if (game.setDraw()) {
                    break;
                }
                System.out.println("Enter the coordinates: ");

            }
        }
    }
}


class TicTacToe {

    public static int column;
    public static int row;
    public static char[][] array = new char[3][3];
    public static boolean pending = false;
    //public boolean winner = false;
    public static int winCount = 0;
    //public boolean draw = false;
    public static char[] coord = new char[1];


    public void start() {
        //System.out.println("Welcome to TicTacToe!");

        // populating the board. this was needed in a previous stage of developement.
        // is it needed still?

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                array[i][j] = ' ';

            }
        }
        printBoard();

        //System.out.println("First move is yours!");

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

    public static boolean canParseToInt(String move) {
        move = move.replaceAll(" ", "");
        coord = move.toCharArray();
        try {


            column = coord[0];
            row = coord[1]; //Integer.parseInt(move.substring(1, 2));
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

    public static boolean coordinatesAreValid(int column, int row) {
        boolean flag = true;
        if (column > 2 || row > 2) {
            System.out.println("Coordinates should be from 1 to 3!");
            flag = false;
        }
        return flag;
    }

    public static boolean cellAvailable(int column, int row) {
        boolean flag = false;
        if (array[row][column] != 32) {
            System.out.println("This cell is occupied! Choose another one!");

        } else {
            flag = true;
        }

        return flag;
    }

    public void printMove(int moveCount) {

        System.out.println("Enter the coordinates: ");
        if (moveCount % 2 != 0) {
            TicTacToe.array[row][column] = 'X';
        } else {
            TicTacToe.array[row][column] = 'O';
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
                System.out.println("xwins row" + i);
                winner = true;
                ++winCount;
            } else if (rowSum == 237) {
                System.out.println("O wins row" + i);
                winner = true;
                ++winCount;
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
                ++winCount;
            } else if (colSum == 264) {
                System.out.println(xWins);
                winner = true;
                ++winCount;
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
                System.out.println("O wins primary diagonal");
                winner = true;
                ++winCount;
            } else if (diagSum == 264) {
                System.out.println("X wins primary diagonal");
                winner = true;
                ++winCount;
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
            ++winCount;
        } else if (diagSum2 == 237) {
            System.out.println(oWins);
            winner = true;
            ++winCount;
        }
        return winner;
    }


    public boolean setDraw() {
        boolean draw = false;

        int space = 35;
        int x = 'X';
        int o = 'O';
        int countX = 0;
        int countO = 0;
        int countSpaces = 9;

        if (!validWin()) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array.length; j++) {
                    if (array[i][j] == x) {
                        ++countX;
                    } else if (array[i][j] == o) {
                        ++countO;
                    }
                    if (array[i][j] == space) {
                        pending = true;
                        --countSpaces;
                    }
                }
            }
            if (!validWin()) {
                System.out.println("Draw");
                draw = true;

            }
        }


        return draw;

    }
}

// impossible
        /*public static void notPossible() {
            if (countO > countX + 1 || countX > countO + 1 || winCount > 1) {
                System.out.println("impossible " + winCount);

            }
        }*/
