import java.util.ArrayList;

public class Level {
    public int width;
    public int height;
    public int levelNumber;
    public ArrayList<Goal> allMyGoals = new ArrayList<>();
    public int completedGoalCount = 0;
    public ArrayList<Square> mazeGrid = new ArrayList<>();
    public Eyeball eyeball;

    public boolean goalJustComplete = false;

    public Level(int newHeight, int newWidth, int newLevelNumber) {
        this.width = newWidth;
        this.height = newHeight;
        this.levelNumber = newLevelNumber;
    }

    public void moveEyeball(int row, int column) {
        this.eyeball.row = row;
        this.eyeball.column = column;
        this.checkGoalComplete(row, column);
        assignEyeballColorAndShape(row, column);
    }

    private void checkGoalComplete(int row, int column) {
        for (Goal goal : this.allMyGoals) {
            if (goal.row == row && goal.column == column) {
                this.goalComplete();
                this.allMyGoals.remove(goal);
                break;
            }
        }
    }

    public void goalComplete() {
        this.completedGoalCount ++;
        this.goalJustComplete = true;
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
            case null:
                return Message.MOVING_DIAGONALLY;
        }
        return Message.OK;
    }
}
