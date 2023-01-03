abstract class Player {
    protected int score;
    protected char sign;

    protected boolean winner;

    public Player() {
        this.score = 0;
        this.winner = false;
    }

    void setSign(char sign) {
        this.sign = sign;
    }

    boolean isWinner() {
        return winner;
    }

    int getScore() {
        return score;
    }
    void addScore() {
        score++;
    }

    void resetWinnerStatus() {
        winner = false;
    }
    abstract void makeMove(Board board);

}
