import java.util.ArrayList;

public class Level {
    public int width;
    public int height;
    public int levelNumber;
    public ArrayList<Goal> allMyGoals = new ArrayList<Goal>();
    public int completedGoalCount = 0;
    public ArrayList<Square> mazeGrid = new ArrayList<Square>();
    public Eyeball eyeball;

    public Level(int newHeight, int newWidth, int newLevelNumber) {
        this.width = newWidth;
        this.height = newHeight;
        this.levelNumber = newLevelNumber;
    }

    public void assignEyeballColorAndShape(int row, int column) {
        for (Square square: mazeGrid) {
            if (square.row == row && square.column == column) {
                this.eyeball.shape = square.shape;
                this.eyeball.color = square.color;
            }
        }
    }

    public void goalComplete() {
        this.completedGoalCount ++;
    }

    public boolean canMoveUpAndInRange(int targetRow, int targetColumn) {
        if (this.eyeball.direction == Direction.UP || this.eyeball.direction == Direction.LEFT
                || this.eyeball.direction == Direction.RIGHT) {
            if (targetRow < this.eyeball.row
                    && targetRow >= 0 && targetColumn == this.eyeball.column) {
                return true;
            }
        }

        return false;
    }

    public boolean canMoveDownAndInRange(int targetRow, int targetColumn) {
        if (this.eyeball.direction == Direction.DOWN && targetRow > this.eyeball.row
                && targetRow <= this.height && targetColumn == this.eyeball.column) {
            return true;
        }
        return false;
    }

    public boolean canMoveRightAndInRange(int targetRow, int targetColumn) {
        if (this.eyeball.direction == Direction.RIGHT && targetColumn > this.eyeball.column
                && targetColumn <= this.height && targetRow == this.eyeball.row) {
            return true;
        }
        return false;
    }

    public boolean canMoveLeftAndInRange(int targetRow, int targetColumn) {
        if (this.eyeball.direction == Direction.LEFT && targetColumn < this.eyeball.column
                && targetColumn >= 0 && targetRow == this.eyeball.row) {
            return true;
        }
        return false;
    }

    public boolean validShapeOrColor(int row, int column) {
        for (Square square: mazeGrid) {
            if (square.row == row && square.column == column) {
                if (square.shape == eyeball.shape || square.color == eyeball.color) {
                    return true;
                }
            }
        }
        return false;
    }

}
