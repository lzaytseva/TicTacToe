public class MinimaxAlfaBeta {
    private static final int MAX_DEPTH = 100;
    private static final int WIN = 10;
    private static final int DEFEAT = -10;
    private static final int NO_WINNER = 0;
    private final char humanSign;
    private final char computerSign;

    public MinimaxAlfaBeta(char computerSign) {
        this.computerSign = computerSign;
        this.humanSign = computerSign == Signs.X ? Signs.O : Signs.X;
    }

    private int miniMax(Board board, int depth, int alpha, int beta,  boolean isAiPlayer) {
        int score = getScore(board);

        if (Math.abs(score) == 10 || depth == 0 || board.isFull()) {
            return score;
        }

        if (isAiPlayer) {
            int bestScore = Integer.MIN_VALUE;

            for (int row = 0; row < board.getBoardSize(); row++) {
                for (int col = 0; col < board.getBoardSize(); col++) {
                    if (board.isAvailableCell(row, col)) {
                        board.changeCellState(row, col, computerSign);
                        bestScore = Math.max(bestScore, miniMax(board, depth - 1, alpha, beta,false));
                        board.changeCellState(row, col, Signs.EMPTY);
                        alpha = Math.max(alpha, bestScore);
                        if (alpha >= beta) {
                            return bestScore;
                        }
                    }
                }
            }

            return bestScore;
        } else {
            int lowestScore = Integer.MAX_VALUE;

            for (int row = 0; row < board.getBoardSize(); row++) {
                for (int col = 0; col < board.getBoardSize(); col++) {
                    if (board.isAvailableCell(row, col)) {
                        board.changeCellState(row, col, humanSign);
                        lowestScore = Math.min(lowestScore, miniMax(board, depth - 1, alpha, beta, true));
                        board.changeCellState(row, col, Signs.EMPTY);
                        beta = Math.min(beta, lowestScore);
                        if (beta <= alpha) {
                            return lowestScore;
                        }
                    }
                }
            }

            return lowestScore;
        }
    }

    private int getScore(Board board) {
        for (int row = 0; row < board.getBoardSize(); row++) {
            if (board.isCompletedHorizontal(row, 0, computerSign)) {
                return WIN;
            } else if (board.isCompletedHorizontal(row, 0, humanSign)) {
                return DEFEAT;
            }
        }

        for (int col = 0; col < board.getBoardSize(); col++) {
            if (board.isCompletedVertical(0, col, computerSign)) {
                return WIN;
            } else if (board.isCompletedVertical(0, col, humanSign)) {
                return DEFEAT;
            }
        }

        for (int row = 0; row < board.getBoardSize(); row++) {
            if (board.isCompletedDiagonal(row, 0, computerSign)) {
                return WIN;
            } else if (board.isCompletedDiagonal(row, 0, humanSign)) {
                return DEFEAT;
            }
        }

        return NO_WINNER;
    }

    public int[] getBestMove(Board board) {
        int[] bestMove = new int[]{-1, -1};
        int bestScore = Integer.MIN_VALUE;

        for (int row = 0; row < board.getBoardSize(); row++) {
            for (int col = 0; col < board.getBoardSize(); col++) {
                if (board.isAvailableCell(row, col)) {
                    board.changeCellState(row, col, computerSign);
                    int moveScore = miniMax(board, MAX_DEPTH, Integer.MAX_VALUE, Integer.MAX_VALUE, false);
                    board.changeCellState(row, col, Signs.EMPTY);
                    if (moveScore > bestScore) {
                        bestMove[0] = row;
                        bestMove[1] = col;
                        bestScore = moveScore;
                    }
                }
            }
        }

        return bestMove;
    }
}
