import java.util.Scanner;

class Main {

    public static void main(String[] args) {


        Scanner in = new Scanner(System.in);

        String board = in.nextLine();

        TicTacToe.start(board);

        String nextMove = in.nextLine(); // take user input

        while (!TicTacToe.moveIsValid(nextMove)) {
            nextMove = in.nextLine();
        }

        TicTacToe.printMove();
        TicTacToe.printBoard(board);
    }
}


class TicTacToe {
    //public static String board;
    //public static String nextMove;
    public static int column;
    public static int row;
    public static char[][] array = new char[3][3];


    public static void start(String board) {
        System.out.println("Enter cells: " + board);
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                array[i][j] = board.charAt(index);
                ++index;
            }
        }
        printBoard(board);
    }

    public static void printBoard(String board) {
        String h = "---------";
        char pipe = '|';

        System.out.println(h);
        for (int i = 0; i < array.length; i++) {
            System.out.print(pipe + " ");
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.print(pipe);
            System.out.println();
        }
        System.out.println(h);
    }

    public static boolean moveIsValid(String nextMove) {
        boolean flag = true;
        if (!TicTacToe.canParseToInt(nextMove)) {
            flag = false;
        } else if (!TicTacToe.coordinatesAreValid(TicTacToe.column, TicTacToe.row)) {
            flag = false;
        } else if (!TicTacToe.cellAvailable(TicTacToe.column, TicTacToe.row)) {
            flag = false;
        }
        return flag;
    }

    public static boolean canParseToInt(String nextMove) {

        try {
            nextMove = nextMove.replaceAll(" ", "");
            column = Integer.parseInt(nextMove.substring(0, 1));
            row = Integer.parseInt(nextMove.substring(1, 2));
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
        } else if (array[row][column] != 95) {
            System.out.println("This cell is occupied! Choose another one!");
            flag = false;
        }
        return flag;
    }

    public static boolean cellAvailable(int column, int row) {
        boolean flag = true;
        if (array[row][column] != 95) {
            System.out.println("This cell is occupied! Choose another one!");
            flag = false;
        }

        return flag;
    }

    public static void printMove() {

        System.out.println("Enter the coordinates: " + column + " " + row);
        TicTacToe.array[row][column] = 'X';


    }


   /* public static void checkGameState() {
        // winner
        boolean winner = false;
        int winCount = 0;
// winByRow


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
                System.out.println("O wins col" + i);
                winner = true;
                ++winCount;
            } else if (colSum == 264) {
                System.out.println("X wins col" + i);
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
            System.out.println("X wins secondary diagonal");
            winner = true;
            ++winCount;
        } else if (diagSum2 == 237) {
            System.out.println("O wins secondary diagonal");
            winner = true;
            ++winCount;
        }

        // draw
        int space = '_'; //95
        int x = 'X';
        int o = 'O';
        int countX = 0;
        int countO = 0;
        //int countSpaces = 0;
        boolean pending = false;
        if (!winner) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array.length; j++) {
                    if (array[i][j] == x) {
                        ++countX;
                    } else if (array[i][j] == o) {
                        ++countO;
                    }
                    if (array[i][j] == space) {
                        pending = true;
                        ++countSpaces;
                    }
                }
            }
            if (!pending) {
                System.out.println("draw");
            } else {
                System.out.println("pending");
            }
        }
        // impossible
        if (countO > countX + 1 || countX > countO + 1 || winCount > 1) {
            System.out.println("impossible " + winCount);

        }
    } */
}

