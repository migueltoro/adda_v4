package us.lsi.graphs.examples.caballo;

public class Chessboard {
    private static final int SIZE = 8;
    private int[][] board;
    public static final int[] knightMovesX = {-2, -1, 1, 2, 2, 1, -1, -2};
    public static final int[] knightMovesY = {1, 2, 2, 1, -1, -2, -2, -1};

    public Chessboard() {
        board = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = -1;
            }
        }
    }

    public boolean isValidMove(int x, int y) {
        return x >= 0 && y >= 0 && x < SIZE && y < SIZE && board[x][y] == -1;
    }

    public void setMove(int x, int y, int moveNumber) {
        board[x][y] = moveNumber;
    }

    public void removeMove(int x, int y) {
        board[x][y] = -1;
    }

    public void printBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.printf("%2d ", board[i][j]);
            }
            System.out.println();
        }
    }
}
