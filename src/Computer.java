import java.util.Random;

public class Computer extends Player {

    Minimax minimax;
    @Override
    void makeMove(Board board) {
        Random rand = new Random();
        int[] move = new int[2];

        System.out.println("Теперь ход компьютера. Подождите, он немного подумает..");

        move = minimax.getBestMove(board);
        if (move[0] == -1 || move[1] == -1) {
            System.out.println("Компьютер сломався (");
        } else {
            board.changeCellState(move[0], move[1], sign);
        }

        /*

        do {
            x = rand.nextInt(board.getBoardSize());
            y = rand.nextInt(board.getBoardSize());
        } while (!board.isAvailableCell(x, y));

        board.changeCellState(x, y, sign);

        if (board.isWinningMove(x, y, sign)) {
            winner = true;
            addScore();
        }
        */
    }

    void initializeMiniMax() {
        minimax = new Minimax(sign);
    }
}
