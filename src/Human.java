import java.util.Scanner;

public class Human extends Player{
    private final Scanner scanner = new Scanner(System.in);

    @Override
    String getClassName() {
        return "Human";
    }

    @Override
    void makeMove(Board board) {
        int x, y;

        System.out.printf("Ваш ход. Введите ряд и столбец через пробел (значения от 0 до %d)\n", board.getBoardSize() - 1);

        while (true) {
            String input = scanner.nextLine();
            if (isValidInput(input)) {
                String[] coord = input.split(" ");
                x = Integer.parseInt(coord[0]);
                y = Integer.parseInt(coord[1]);
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
            isWinner = true;
            addScore();
        }
    }

    private boolean isValidInput(String input) {
        return input.matches("\\d{1,2} \\d{1,2}");
    }
}
