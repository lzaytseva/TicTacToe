public class MinimaxAlfaBeta {
    private static final int MAX_DEPTH = 100;
    private final char humanSign;
    private final char computerSign;

    public MinimaxAlfaBeta(char computerSign) {
        this.computerSign = computerSign;
        this.humanSign = computerSign == 'X' ? 'O' : 'X';
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
                        board.changeCellState(row, col, Signs.SIGN_EMPTY);
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
                        board.changeCellState(row, col, Signs.SIGN_EMPTY);
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
                return 10;
            } else if (board.isCompletedHorizontal(row, 0, humanSign)) {
                return -10;
            }
        }

        for (int col = 0; col < board.getBoardSize(); col++) {
            if (board.isCompletedVertical(0, col, computerSign)) {
                return 10;
            } else if (board.isCompletedVertical(0, col, humanSign)) {
                return -10;
            }
        }

        for (int row = 0; row < board.getBoardSize(); row++) {
            if (board.isCompletedDiagonal(row, 0, computerSign)) {
                return 10;
            } else if (board.isCompletedDiagonal(row, 0, humanSign)) {
                return -10;
            }
        }

        return 0;
    }

    public int[] getBestMove(Board board) {
        int[] bestMove = new int[]{-1, -1};
        int bestScore = Integer.MIN_VALUE;

        for (int row = 0; row < board.getBoardSize(); row++) {
            for (int col = 0; col < board.getBoardSize(); col++) {
                if (board.isAvailableCell(row, col)) {
                    board.changeCellState(row, col, computerSign);
                    int moveScore = miniMax(board, MAX_DEPTH, Integer.MAX_VALUE, Integer.MAX_VALUE, false);
                    board.changeCellState(row, col, Signs.SIGN_EMPTY);
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
