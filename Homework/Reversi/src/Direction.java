/**
 * Класс для направления.
 */
public class Direction {
    /**
     * Текущая координата.
     */
    private Coords current;
    /**
     * Обозначение направления.
     * up           - 0
     * down         - 1
     * right        - 2
     * left         - 3
     * up-right     - 4
     * up-left      - 5
     * down-right   - 6
     * down-left    - 7
     */
    private int direction;

    public Direction(Coords current, int direction) {
        this.current = current;
        this.direction = direction;
    }

    /**
     * Ходим в нужном направлении.
     * @return true - если полученные координаты валидны, false - в другом случае.
     */
    public boolean produce() {
        return switch (direction) {
            case 0 -> current.decRow();
            case 1 -> current.incRow();
            case 2 -> current.incColumn();
            case 3 -> current.decColumn();
            case 4 -> current.decRow() && current.incColumn();
            case 5 -> current.decRow() && current.decColumn();
            case 6 -> current.incRow() && current.incColumn();
            case 7 -> current.incRow() && current.decColumn();
            default -> false;
        };
    }

    public int getRow() {
        return current.getRow();
    }

    public int getColumn() {
        return current.getColumn();
    }

    public boolean edge() {
        return current.edge();
    }
}
