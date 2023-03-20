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

    public boolean canFaceUp() {
        if (this.direction == Direction.DOWN) {
            return false;
        }
        return true;
    }

    public boolean canFaceDown() {
        if (this.direction == Direction.UP) {
            return false;
        }
        return true;
    }

    public boolean canFaceRight() {
        if (this.direction == Direction.LEFT) {
            return false;
        }
        return true;
    }

    public boolean canFaceLeft() {
        if (this.direction == Direction.RIGHT) {
            return false;
        }
        return true;
    }
}
