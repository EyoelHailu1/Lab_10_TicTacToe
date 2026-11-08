import java.util.Scanner;

public class TicTacToe {

    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String[][] board = new String[ROWS][COLS];

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        boolean playAgain;

        // Repeat games until the players choose to quit.
        do {
            // Clear the board and start with player X.
            clearBoard();
            String player = "X";
            int moveCount = 0;
            boolean gameOver = false;

            display();

            while (!gameOver) {
                int row;
                int col;

                // Get coordinates until the player selects an empty cell.
                while (true) {
                    System.out.println("Player " + player + "'s turn.");

                    row = SafeInput.getRangedInt(
                            console, "Enter row", 1, ROWS) - 1;

                    col = SafeInput.getRangedInt(
                            console, "Enter column", 1, COLS) - 1;

                    if (isValidMove(row, col)) {
                        break;
                    }

                    System.out.println(
                            "That cell is already occupied. Try again.");
                }

                // Record the move and display the updated board.
                board[row][col] = player;
                moveCount++;
                display();

                // Check for a win once enough moves have been played.
                if (moveCount >= ROWS + COLS - 1 && isWin(player)) {
                    System.out.println("Player " + player + " wins!");
                    gameOver = true;
                } else if (moveCount == ROWS * COLS && isTie()) {
                    System.out.println("The game is a tie!");
                    gameOver = true;
                }

                // Switch players if the game is still going.
                if (!gameOver) {
                    player = player.equals("X") ? "O" : "X";
                }
            }

            // Ask whether to start another game.
            playAgain = SafeInput.getYNConfirm(
                    console, "Would you like to play again?");

        } while (playAgain);

        System.out.println("Thanks for playing!");
    }

    private static void clearBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = " ";
            }
        }
    }

    private static void display() {
        System.out.println();

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                System.out.print(" " + board[row][col] + " ");

                if (col < COLS - 1) {
                    System.out.print("|");
                }
            }

            System.out.println();

            if (row < ROWS - 1) {
                for (int col = 0; col < COLS; col++) {
                    System.out.print("---");

                    if (col < COLS - 1) {
                        System.out.print("+");
                    }
                }

                System.out.println();
            }
        }

        System.out.println();
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < ROWS
                && col >= 0 && col < COLS
                && board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isColWin(player)
                || isRowWin(player)
                || isDiagnalWin(player);
    }

    private static boolean isColWin(String player) {
        for (int col = 0; col < COLS; col++) {
            boolean allMatch = true;

            for (int row = 0; row < ROWS; row++) {
                if (!board[row][col].equals(player)) {
                    allMatch = false;
                    break;
                }
            }

            if (allMatch) {
                return true;
            }
        }

        return false;
    }

    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROWS; row++) {
            boolean allMatch = true;

            for (int col = 0; col < COLS; col++) {
                if (!board[row][col].equals(player)) {
                    allMatch = false;
                    break;
                }
            }

            if (allMatch) {
                return true;
            }
        }

        return false;
    }

    private static boolean isDiagnalWin(String player) {
        boolean mainDiagonal = true;
        boolean otherDiagonal = true;

        for (int row = 0; row < ROWS; row++) {
            if (!board[row][row].equals(player)) {
                mainDiagonal = false;
            }

            if (!board[row][COLS - 1 - row].equals(player)) {
                otherDiagonal = false;
            }
        }

        return mainDiagonal || otherDiagonal;
    }

    private static boolean isTie() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col].equals(" ")) {
                    return false;
                }
            }
        }

        return !isWin("X") && !isWin("O");
    }
}