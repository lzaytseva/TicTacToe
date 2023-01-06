public class Computer extends Player {
    MinimaxAlfaBeta minimaxAlfaBeta;

    @Override
    String getClassName() {
        return "Computer";
    }

    @Override
    void makeMove(Board board) {
        int[] move;

        System.out.println("Ход компьютера");
        move = minimaxAlfaBeta.getBestMove(board);

        if (move[0] == -1 || move[1] == -1) {
            System.out.println("Компьютер сломався (");
        } else {
            board.changeCellState(move[0], move[1], sign);
        }

        if (board.isWinningMove(move[0], move[1], sign)) {
            isWinner = true;
            addScore();
        }
    }

    void initializeMiniMax() {
        minimaxAlfaBeta = new MinimaxAlfaBeta(sign);
    }
}
