import java.util.Scanner;

public class TicTacToe {
    private static final int FIRST_ROUND = 1;
    private static final int MIN_BOARD_SIZE = 3;
    private static final int MAX_BOARD_SIZE = 15;
    final static int MAX_SEQUENCE = 5;
    private final Scanner scanner;
    private final Player human;
    private final Player computer;
    private Board board;
    private int round;
    private boolean gameOver;

    public TicTacToe() {
        this.scanner = new Scanner(System.in);
        this.human = new Human();
        this.computer = new Computer();
        this.gameOver = false;
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

        while (true) {
            System.out.printf("Введите размер поля от %d до %d\n", MIN_BOARD_SIZE, MAX_BOARD_SIZE);
            String input = scanner.nextLine();

            if (input.matches("\\d+")) {
                boardSize = Integer.parseInt(input);
                if (boardSize < MIN_BOARD_SIZE) {
                    System.out.println("Слишком маленькое поле, будет не интересно :(");
                } else if (boardSize > MAX_BOARD_SIZE) {
                    System.out.println("Ну и куда вам так много?");
                } else if (boardSize > MIN_BOARD_SIZE) {
                    if (boardSize > MAX_SEQUENCE) {
                        System.out.printf("Победит тот, кто быстрее составит ряд из %d.\n", MAX_SEQUENCE);
                    }
                    System.out.println("Warning! ИИ пока еще не очень умный и может вести себя не адекватно.");
                    break;
                } else {
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
            if (sign.equals("0") || sign.equalsIgnoreCase("o") || sign.equalsIgnoreCase("о")) {
                human.setSign(Signs.O);
                computer.setSign(Signs.X);
                break;
            } else if (sign.equalsIgnoreCase("x") || sign.equalsIgnoreCase("х")) {
                 human.setSign(Signs.X);
                 computer.setSign(Signs.O);
                 break;
            } else {
                System.out.println("Неизвестный символ. Попробуйте еще раз.");
            }
        }
    }

    private void setUpMiniMax() {
        ((Computer)computer).initializeMiniMax();
    }

    private void play() {
        boolean humanTurn = human.getSign() == Signs.X;

        System.out.println("Крестики ходят первыми :)");

        while (!gameOver) {
            if (humanTurn) {
                move(human);
            } else {
                move(computer);
            }
            humanTurn = !humanTurn;
        }
    }

    private void move(Player player) {
        player.makeMove(board);
        printBoard();
        checkWin(player);
    }

    private void checkWin(Player player) {
        if (player.isWinner()) {
            if (player instanceof Human) {
                System.out.println("Вы выиграли");
            } else {
                System.out.println("Компьютер выиграл :(");
            }
            gameOver = true;
        } else if (board.isFull()) {
            System.out.println("Ничья");
            gameOver = true;
        }
    }

    private void printScore() {
        System.out.printf("Человек - компьютер %d:%d.\n", human.getScore(), computer.getScore());
    }

    private void newRound() {
        human.resetWinnerStatus();
        computer.resetWinnerStatus();
        gameOver = false;
        round++;
    }
}

