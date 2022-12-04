import java.util.ArrayList;
import java.util.Scanner;

/**
 * Класс консоли.
 * Содержит только статические методы.
 * Реализует взаимодействие пользователя с игрой через консоль.
 */
public class Console {
    /**
     * Отображает игровое поле в консоли.
     * @param board - игровое поле.
     */
    public static void display(Board board) {
        // Создаём полосу-разделитель нужного размера.
        StringBuilder line = new StringBuilder();
        line.append('\t');
        line.append("--------".repeat(Board.size));
        System.out.print("\t");
        // Подписываем номера столбцов.
        for (int i = 0; i < Board.size; ++i) {
            System.out.print("\t" + (i + 1) + "\t");
        }
        System.out.println();
        // Проходимся по всем строкам поля.
        for (int i = 0; i < Board.size; ++i) {
            System.out.println(line);
            StringBuilder sb = new StringBuilder();
            sb.append(i + 1);
            sb.append("  ");
            // Проходимся по всем клеткам в строке.
            for (int cell : board.getField()[i]) {
                sb.append("|\t");
                sb.append(switch (cell) {
                    // Второй игрок.
                    case 1 -> "○";
                    // Первый игрок.
                    case -1 -> "●";
                    // Пустая клетка.
                    case 0 -> " ";
                    // Возможный ход.
                    default -> "?";
                });
                sb.append('\t');
            }
            sb.append("|");
            System.out.println(sb);
        }
        System.out.println(line + "\n");
    }

    /**
     * Отображает возможные ходы.
     * @param board - игровое поле.
     * @param turns - список возможных ходов.
     */
    public static void displayPossibleTurns(Board board, ArrayList<CellInfo> turns) {
        // Клонируем поле.
        Board newBoard = board.clone();
        // Обозначаем возможные ходы на поле.
        for (CellInfo turn : turns) {
            newBoard.getField()[turn.coords.getRow()][turn.coords.getColumn()] = '?';
        }
        // Отображаем отредактированное поле.
        display(newBoard);
        // Выводим список возможных ходов.
        System.out.println("List of possible turns:");
        for (int i = 0; i < turns.size(); ++i) {
            System.out.println((i + 1) + ".\t(" + (turns.get(i).coords.getRow() + 1) + "; " +
                               (turns.get(i).coords.getColumn() + 1) + ")");
        }
        System.out.println();
    }

    /**
     * Ввод числа в диапазоне от 1 до max.
     * @param max Максимальное число диапазона.
     * @return Введённое число.
     */
    public static int inputNumber(int max) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of the preferred option (from 1 to " + max + ")");
        int no = 0;
        String input;
        boolean flag;
        do {
            System.out.print(">> ");
            input = scanner.next();
            try {
                no = Integer.parseInt(input);
                flag = no < 1 || no > max;
                if (flag) {
                    System.out.println("Number must be between 1 and " + max + "!");
                }
            } catch (NumberFormatException e) {
                System.out.println("You must write a number!");
                flag = true;
            }
        } while (flag);
        System.out.println();
        return no;
    }

    /**
     * Узнаём у пользователя следующее действие.
     * @return Выбранное действие.
     */
    public static int nextAction() {
        System.out.println("Next action?");
        System.out.println("1. Make a move.\n2. Undo the previous turn.\n3. Give up.\n");
        return inputNumber(3);
    }

    /**
     * Сообщаем о начале хода, отображаем поле.
     * @param no - номер хода.
     * @param board - текущее состояние доски.
     */
    public static void turn(int no, Board board) {
        System.out.println("Turn " + no + " has begun. Player " + (((no + 1) % 2) + 1) + " is in action.\n");
        display(board);
    }

    /**
     * Узнаём у пользователя, уверен ли он в своём ходе.
     * @return true - если уверен, false - иначе.
     */
    public static boolean sure() {
        System.out.println("Are you sure?\n1. Yes.\n2. No.");
        return inputNumber(2) == 1;
    }

    /**
     * Сообщение об ошибке, если пользователь пытается отменить ход, а предыдщуего хода нет.
     */
    public static void emptyStack() {
        System.out.println("There are no previous turns!\n");
    }

    /**
     * Начало игры, пользователь выбирает режим игры.
     * @return Код выбранного режима.
     */
    public static int gameMode() {
        System.out.println("Welcome to REVERSI!\n\nSelect game mode:");
        System.out.println("1. Player vs Newbie Computer.\n" +
                           "2. Player vs Professional Computer.\n" +
                           "3. Player vs Player.\n4. Exit.");
        return inputNumber(4);
    }

    /**
     * Выводится результат игры.
     * @param outcome - код результата.
     */
    public static void result(int outcome) {
        System.out.println(switch (outcome) {
            case 0 -> "Player 1 won!";
            case 1 -> "Player 2 won!";
            default -> "Tie!";
        });
    }
}
