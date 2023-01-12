abstract class Player {
    protected int score;
    protected char sign;
    protected boolean isWinner;

    public Player() {
        this.score = 0;
        this.isWinner = false;
    }

    public char getSign() {
        return sign;
    }

    void setSign(char sign) {
        this.sign = sign;
    }

    boolean isWinner() {
        return isWinner;
    }

    int getScore() {
        return score;
    }

    void addScore() {
        score++;
    }

    void resetWinnerStatus() {
        isWinner = false;
    }

    abstract void makeMove(Board board);
}
