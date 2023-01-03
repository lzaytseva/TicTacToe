import java.util.Arrays;

public class Board {

    final static int MAX_SEQUENCE = 5;
    private final int winningSequenceLength;
    char[][] board;

    Board(int size) {
        board = new char[size][size];
        for (char[] row: board) {
            Arrays.fill(row, Signs.SIGN_EMPTY);
        }
        winningSequenceLength = Math.min(size, MAX_SEQUENCE);
    }

    int getBoardSize() {
        return board.length;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (char[] row: board) {
            for (char ch: row) {
                builder.append(ch).append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    boolean isFull() {
        for (char[] row: board) {
            for (char ch: row) {
                if (ch == Signs.SIGN_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
    boolean isAvailableCell(int x, int y) {
        return board[x][y] == Signs.SIGN_EMPTY;
    }
    void changeCellState(int x, int y, char sign) {
        board[x][y] = sign;
    }

    boolean isValidCell(int x, int y) {
        return (x >= 0 && x < board.length) && (y >= 0 && y < board.length);
    }

    boolean isWinningMove(int x, int y, char sign) {
        return isCompletedHorizontal(x, y, sign) || isCompletedVertical(x, y, sign) || isCompletedDiagonal(x, y, sign);
    }

    private boolean isCompletedHorizontal(int x, int y, char sign) {
        int seqLength = 1;
        int dx = x - 1;

        while(dx >= 0 && seqLength < winningSequenceLength) {
            if (board[dx][y] == sign) {
                seqLength++;
                dx--;
            } else {
                break;
            }
        }

        dx = x + 1;

        while(dx < board.length && seqLength < winningSequenceLength) {
            if (board[dx][y] == sign) {
                seqLength++;
                dx++;
            } else {
                break;
            }
        }

        return seqLength == winningSequenceLength;
    }

    private boolean isCompletedVertical(int x, int y, char sign) {
        int seqLength = 1;
        int dy = y - 1;

        while(dy >= 0 && seqLength < winningSequenceLength) {
            if (board[x][dy] == sign) {
                seqLength++;
                dy--;
            } else {
                break;
            }
        }

        dy = y + 1;

        while(dy < board.length && seqLength < winningSequenceLength) {
            if (board[x][dy] == sign) {
                seqLength++;
                dy++;
            } else {
                break;
            }
        }

        return seqLength == winningSequenceLength;
    }
    private boolean isCompletedDiagonal(int x, int y, char sign) {
        int seqLength = 1;
        int dy = y - 1;
        int dx = x - 1;

        while((dy >= 0 && dx >= 0) && seqLength < winningSequenceLength) {
            if (board[dx][dy] == sign) {
                seqLength++;
                dy--;
                dx--;
            } else {
                break;
            }
        }

        dy = y + 1;
        dx = x + 1;

        while((dy < board.length && dx < board.length) && seqLength < winningSequenceLength) {
            if (board[x][dy] == sign) {
                seqLength++;
                dy++;
                dx++;
            } else {
                break;
            }
        }

        return seqLength == winningSequenceLength;
    }


}
