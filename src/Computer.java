public class Computer extends Player {

    Minimax minimax;
    @Override
    void makeMove(Board board) {
        int[] move;

        System.out.println("Теперь ход компьютера. Подождите, он немного подумает..");

        move = minimax.getBestMove(board);
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
        minimax = new Minimax(sign);
    }
}
