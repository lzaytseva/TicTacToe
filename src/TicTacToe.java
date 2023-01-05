import java.util.Scanner;

public class TicTacToe {
    private static final int FIRST_ROUND = 1;
    final static int MAX_SEQUENCE = 5;
    static final int MIN_BOARD_SIZE = 3;
    static final int MAX_BOARD_SIZE = 7;
    private final Scanner scanner;
    private Board board;
    private final Player human;
    private final Player computer;
    private int round;

    public TicTacToe() {
        this.scanner = new Scanner(System.in);
        this.human = new Human();
        this.computer = new Computer();
        this.round = FIRST_ROUND;
    }

    void run() {
        do {
            prepareGame();
            play();
            printScore();

            System.out.println("Желаете сыграть еще разок? Введите \"нет\" для завершения игры или любоой символ для продолжения.");
            newRound();
        } while (!"нет".equalsIgnoreCase(scanner.nextLine()));
    }

    private void prepareGame() {
        printGreeting();
        createBoard();
        printBoard();
        setSign();
        setUpMiniMax();
    }

    private void setUpMiniMax() {
        ((Computer)computer).initializeMiniMax();
    }

    private void printGreeting() {
        if (round == FIRST_ROUND) {
            System.out.println("Привет! Это игра крестики-нолики");
        } else {
            System.out.printf("Раунд %d. Поехали :)%n", round);
        }
    }

    private void createBoard() {
        board = new Board(getBoardSizeFromPlayer());
    }

    private int getBoardSizeFromPlayer() {
        int boardSize;

        System.out.println("Введите размер поля от 3 до 7");
        while (true) {
            String input = scanner.nextLine();
            if (input.matches("\\d+")) {
                boardSize = Integer.parseInt(input);
                if (boardSize < MIN_BOARD_SIZE) {
                    System.out.println("Слишком маленькое поле, будет не интересно :(");
                } else if (boardSize > MAX_BOARD_SIZE) {
                    System.out.println("ИИ пока еще не очень умный, будет скучно, давайте выберем поле поменьше.");
                } else if (boardSize > MAX_SEQUENCE) {
                    System.out.println("Победит тот, кто быстрее составит ряд из 5.");
                    break;
                }
            } else {
            System.out.println("Некорректный ввод. Попробуйте снова.");
           }
        }

        return boardSize;
    }

    private void printBoard() {
        System.out.println(board);
    }

    private void setSign() {
        String sign;

        System.out.println("Выберете ваш символ. Введите \"X\" либо \"O\".");

        while (true) {
            sign = scanner.nextLine();
            if (sign.equals("0") || sign.equalsIgnoreCase("o")) {
                human.setSign(Signs.SIGN_O);
                computer.setSign(Signs.SIGN_X);
                break;
            } else if (sign.equalsIgnoreCase("x")) {
                 human.setSign(Signs.SIGN_X);
                 computer.setSign(Signs.SIGN_O);
                 break;
            } else {
                System.out.println("Неизвестный символ. Попробуйте еще раз.");
            }
        }
    }

    private void play() {
        while (true) {
            human.makeMove(board);
            printBoard();

            if (human.isWinner()) {
                System.out.println("Вы выиграли");
                break;
            } else if (board.isFull()) {
                System.out.println("Ничья");
                break;
            }

            computer.makeMove(board);
            printBoard();

            if (computer.isWinner()) {
                System.out.println("Компьютер выиграл :(");
                break;
            } else if (board.isFull()) {
                System.out.println("Ничья.");
                break;
            }
        }
    }

    private void printScore() {
        System.out.printf("Человек - компьютер %d:%d.\n", human.getScore(), computer.getScore());
    }

    private void newRound() {
        human.resetWinnerStatus();
        computer.resetWinnerStatus();
        round++;
    }

}

