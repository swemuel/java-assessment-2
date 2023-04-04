public class Eyeball {
    int row;
    int column;
    Shape shape;
    Color color;
    Direction direction;

    public Eyeball(int newRow, int newColumn, Direction newDirection) {
        this.row = newRow;
        this.column = newColumn;
        this.direction = newDirection;
    }
}
