import java.util.ArrayList;

/**
 * Наследник класса игрок для живого игрока.
 */
public class Human extends Player {

    public Human(int mark) {
        super(mark);
    }

    /**
     * Игрок выбирает клетку через консоль.
     * @param turns - возможные ходы.
     * @param board - игровое поле.
     * @return Выбранная клетка.
     */
    @Override
    public CellInfo choose(ArrayList<CellInfo> turns, Board board) {
        Console.displayPossibleTurns(board, turns);
        return turns.get(Console.inputNumber(turns.size()) - 1);
    }

    /**
     * Игрок выбирает следующее действие.
     * @return Код выбранного действия.
     */
    @Override
    public int takeTurn() {
        return Console.nextAction();
    }

    /**
     * Узнаём у игрока, уверен ли он в своём ходе.
     * @return true - верен, false - иначе.
     */
    @Override
    public boolean isSure() {
        return Console.sure();
    }
}
