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

    public void moveEyeball(int row, int column) {
        this.eyeball.row = row;
        this.eyeball.column = column;
    }

    public void changeEyeballDirection(int row, int column) {
        this.eyeball.direction = this.destinationDirection(row, column);
    }

    public void assignEyeballColorAndShape(int row, int column) {
        for (Square square: mazeGrid) {
            if (square.row == row && square.column == column) {
                this.eyeball.shape = square.shape;
                this.eyeball.color = square.color;
            }
        }
    }

    public Direction destinationDirection(int destinationRow, int destinationColumn) {

        if (this.eyeball.row == destinationRow) {
            if (this.eyeball.column < destinationColumn) {
                return Direction.RIGHT;
            } else if (this.eyeball.column > destinationColumn) {
                return Direction.LEFT;
            }
        }
        if(this.eyeball.column == destinationColumn) {
            if (this.eyeball.row < destinationRow) {
                return Direction.DOWN;
            } else if (this.eyeball.row > destinationRow) {
                return Direction.UP;
            }
        }
        return null;
    }

    public void goalComplete() {
        this.completedGoalCount ++;
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

    public Message legalMove(int destinationRow, int destinationColumn) {
        Direction destDirection = this.destinationDirection(destinationRow, destinationColumn);
        switch (destDirection) {
            case UP:
                if (this.eyeball.direction == Direction.DOWN) {
                    return Message.BACKWARDS_MOVE;
                }
                break;
            case RIGHT:
                if (this.eyeball.direction == Direction.LEFT) {
                    return Message.BACKWARDS_MOVE;
                }
                break;
            case DOWN:
                if (this.eyeball.direction == Direction.UP) {
                    return Message.BACKWARDS_MOVE;
                }
                break;
            case LEFT:
                if (this.eyeball.direction == Direction.RIGHT) {
                    return Message.BACKWARDS_MOVE;
                }
                break;
        }
        return Message.OK;
    }
}
