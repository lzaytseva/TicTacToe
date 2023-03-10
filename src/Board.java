import java.util.Arrays;

public class Board {
    private final int winningSequenceLength;
    char[][] board;

    Board(int size) {
        board = new char[size][size];
        for (char[] row: board) {
            Arrays.fill(row, Signs.EMPTY);
        }
        winningSequenceLength = Math.min(size, TicTacToe.MAX_SEQUENCE);
    }

    int getBoardSize() {
        return board.length;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("    | ");
        for (int i = 0; i < board.length; i++) {
            if (i >= 10) {
                builder.append(i).append("|").append(" ");
            } else {
                builder.append(i).append(" ").append("|").append(" ");
            }
        }
        builder.append("\n");
        builder.append("_ ".repeat(board.length * 2 + 3)).append("\n");
        for (int i = 0; i < board.length; i++) {
            if (i >= 10) {
                builder.append("[").append(i).append("]").append("|").append(" ");
            } else {
                builder.append("[").append(i).append("]").append(" ").append("|").append(" ");
            }
            for (int j = 0; j < board.length; j++) {
                builder.append(board[i][j]).append(" ").append("|").append(" ");
            }
            builder.append("\n");
            if (i != board.length - 1) {
                builder.append("_ ".repeat(board.length * 2 + 3)).append("\n");
            }
        }
        
        return builder.toString();
    }

    boolean isFull() {
        for (char[] row: board) {
            for (char ch: row) {
                if (ch == Signs.EMPTY) {
                    return false;
                }
            }
        }
        
        return true;
    }
    boolean isAvailableCell(int x, int y) {
        return board[x][y] == Signs.EMPTY;
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
    boolean isCompletedHorizontal(int x, int y, char sign) {
        int seqLength = board[x][y] == sign ? 1 : 0;
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

    boolean isCompletedVertical(int x, int y, char sign) {
        int seqLength = board[x][y] == sign ? 1 : 0;
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
    boolean isCompletedDiagonal(int x, int y, char sign) {
        return isCompletedMainDiagonal(x, y, sign) || isCompletedAntidiagonal(x, y, sign);
    }

    boolean isCompletedMainDiagonal(int x, int y, char sign) {
        int seqLength = board[x][y] == sign ? 1 : 0;
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
            if (board[dx][dy] == sign) {
                seqLength++;
                dy++;
                dx++;
            } else {
                break;
            }
        }
        
        return seqLength == winningSequenceLength;
    }

    boolean isCompletedAntidiagonal(int x, int y, char sign) {
        int seqLength = board[x][y] == sign ? 1 : 0;
        int dy = y + 1;
        int dx = x - 1;

        while((dx >= 0 && dy < board.length) && seqLength < winningSequenceLength) {
            if (board[dx][dy] == sign) {
                seqLength++;
                dy++;
                dx--;
            } else {
                break;
            }
        }

        dy = y - 1;
        dx = x + 1;

        while((dx < board.length && dy >= 0) && seqLength < winningSequenceLength) {
            if (board[dx][dy] == sign) {
                seqLength++;
                dy--;
                dx++;
            } else {
                break;
            }
        }
        
        return seqLength == winningSequenceLength;
    }

}
