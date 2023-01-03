import java.util.Scanner;

public class Human extends Player{
    private final Scanner scanner = new Scanner(System.in);
    @Override
    void makeMove(Board board) {
        int x, y;

        System.out.printf("Ваш ход. Введите координаты ячейки через пробел от 0 до %d\n", board.getBoardSize() - 1);

        while (true) {
            String input = scanner.nextLine();
            if (isValidInput(input)) {
                x = Integer.parseInt(input.substring(0, 1));
                y = Integer.parseInt(input.substring(2));
                if (board.isValidCell(x, y)) {
                    if (board.isAvailableCell(x, y)) {
                        board.changeCellState(x, y, sign);
                        break;
                    } else {
                        System.out.println("Эта ячейка уже занята.");
                    }
                } else {
                    System.out.println("Неверные координаты. Попробуйте еще раз.");
                }
            } else {
                System.out.println("Неверный ввод. Попробуйте еще раз");
            }
        }

        if (board.isWinningMove(x, y, sign)) {
            winner = true;
            addScore();
        }

    }

    private boolean isValidInput(String input) {
        return input.matches("\\d \\d");
    }
}
