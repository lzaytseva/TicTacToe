import java.util.Random;

public class Computer extends Player {
    @Override
    void makeMove(Board board) {
        Random rand = new Random();
        int x, y;

        System.out.println("Теперь ход компьютера. Подождите, он немного подумает..");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        do {
            x = rand.nextInt(board.getBoardSize());
            y = rand.nextInt(board.getBoardSize());
        } while (!board.isAvailableCell(x, y));

        board.changeCellState(x, y, sign);

        if (board.isWinningMove(x, y, sign)) {
            winner = true;
            addScore();
        }
    }
}
