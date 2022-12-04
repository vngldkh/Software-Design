import java.util.ArrayList;
import java.util.Stack;

/**
 * Класс игры.
 */
public class Game {
    /**
     * Игровое поле.
     */
    private Board board;
    /**
     * Массив игроков.
     * Хотя их всего два, в массиве обрабатывать удобнее.
     */
    private Player[] players;
    /**
     * Стек хранит в себе поля с предыдщуих ходов.
     */
    private Stack<Board> previousTurns;

    /**
     * Конструктор игры.
     * @param player1 - объект первого игрока.
     * @param player2 - объект второго игрока.
     */
    private Game(Player player1, Player player2) {
        board = new Board();
        players = new Player[]{player1, player2};
        previousTurns = new Stack<>();
    }

    /**
     * Метод реализует непосредственно игровой процесс.
     */
    public void play() {
        ArrayList<CellInfo> possibleTurns = board.possibleTurns(players[0].mark);
        int turn = 0;
        // Игра идёт, пока у активного игрока есть возможные ходы.
        while (!possibleTurns.isEmpty()) {
            Console.turn(turn + 1, board);
            int option;
            do {
                // Активный игрок выбирает дальнешее действие.
                // Компьютер всегда делает ход.
                option = players[turn % 2].takeTurn();
                switch (option) {
                    // Ход.
                    case 1 -> {
                        boolean flag;
                        CellInfo chosenCell;
                        do {
                            // Игрок выбирает клетку.
                            chosenCell = players[turn % 2].choose(possibleTurns, board);
                            // У игрока спрашивают, уверен ли он.
                            // Компьютер всегда уверен.
                            flag = players[turn % 2].isSure();
                        } while (!flag);
                        // Сохраняем поле.
                        previousTurns.push(board.clone());
                        // Обновляем его.
                        board.reverse(chosenCell);
                        Console.display(board);
                    }
                    // Отмена хода.
                    case 2 -> {
                        if (previousTurns.isEmpty()) {
                            Console.emptyStack();
                        } else {
                            previousTurns.pop();
                            board = previousTurns.pop();
                            Console.display(board);
                            possibleTurns = board.possibleTurns(players[turn % 2].mark);
                        }
                    }
                    // Активный игрок сдался.
                    case 3 -> {
                        // Игра заканчивается в пользу противоположного игрока.
                        endGame((turn + 1) % 2);
                        return;
                    }
                }
            } while (option != 1);
            ++turn;
            possibleTurns = board.possibleTurns(players[turn % 2].mark);
        }
        // Подсчитывается кол-во фишек каждого цвета, определяется победитель.
        endGame(result());
    }

    /**
     * Подсчитывается кол-во фишек каждого цвета, определяется победитель.
     * @return Номер победителя (0 - 1 игрок, 1 - 2 игрок, 2 - ничья).
     */
    private int result() {
        int counter1 = 0, counter2 = 0;
        for (int[] row : board.getField()) {
            for (int cell : row) {
                if (cell == -1) {
                    ++counter1;
                } else {
                    ++counter2;
                }
            }
        }
        if (counter1 == counter2) {
            return 2;
        }
        if (counter1 > counter2) {
            return 0;
        }
        return 1;
    }

    /**
     * Инициализируется игровой процесс:
     * определяется режим игры, создаётся соответствующий объект класса.
     * @return Объект класса игры.
     */
    public static Game Init() {
        int option = Console.gameMode();
        return switch (option) {
            case 1 -> new Game(new Human(-1), new EasyComputer(1));
            case 2 -> new Game(new Human(-1), new ProfessionalComputer(1));
            case 3 -> new Game(new Human(-1), new Human(1));
            default -> null;
        };
    }

    /**
     * Сообщается результат игры.
     * @param outcome - Номер победителя
     */
    private static void endGame(int outcome) {
        Console.result(outcome);
    }
}
