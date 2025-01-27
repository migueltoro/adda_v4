package us.lsi.graphs.examples.caballo;

public class KnightTourO {
    private Chessboard chessboard;

    public KnightTourO() {
        chessboard = new Chessboard();
    }

    public boolean solveTour() {
        chessboard.setMove(0, 0, 0);
        if (solveTourUtil(0, 0, 1)) {
            chessboard.printBoard();
            return true;
        } else {
            System.out.println("No solution found.");
            return false;
        }
    }

    private boolean solveTourUtil(int x, int y, int moveNumber) {
        if (moveNumber == 64) {
            return true;
        }

        for (int i = 0; i < 8; i++) {
            int nextX = x + Chessboard.knightMovesX[i];
            int nextY = y + Chessboard.knightMovesY[i];
            if (chessboard.isValidMove(nextX, nextY)) {
                chessboard.setMove(nextX, nextY, moveNumber);
                if (solveTourUtil(nextX, nextY, moveNumber + 1)) {
                    return true;
                } else {
                    chessboard.removeMove(nextX, nextY);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        KnightTourO knightTour = new KnightTourO();
        knightTour.solveTour();
    }
}