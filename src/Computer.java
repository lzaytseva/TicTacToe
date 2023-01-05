public class Computer extends Player {

    MinimaxAlfaBeta minimaxAlfaBeta;
    @Override
    void makeMove(Board board) {
        int[] move;

        System.out.println("Теперь ход компьютера. Подождите, он немного подумает..");

        move = minimaxAlfaBeta.getBestMove(board);
        if (move[0] == -1 || move[1] == -1) {
            System.out.println("Компьютер сломався (");
        } else {
            board.changeCellState(move[0], move[1], sign);
        }

        if (board.isWinningMove(move[0], move[1], sign)) {
            winner = true;
            addScore();
        }
    }

    void initializeMiniMax() {
        minimaxAlfaBeta = new MinimaxAlfaBeta(sign);
    }
}
