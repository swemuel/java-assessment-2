import java.util.ArrayList;

public class Game implements ILevelHolder, IEyeballHolder, IGoalHolder, ISquareHolder, IMoving {

    private int levelCount = 0;
    private Level currentLevel;
    public ArrayList<Level> allMyLevels = new ArrayList<Level>();


    /*

    ILevelHolder

    */

    @Override
    public void addLevel(int height, int width) {
        Level newLevel = new Level(height, width, this.levelCount);
        this.allMyLevels.add(newLevel);
        this.levelCount += 1;
        this.setLevel(this.levelCount - 1);
    }

    @Override
    public int getLevelWidth() {
        return currentLevel.width;
    }

    @Override
    public int getLevelHeight() {
        return currentLevel.height;
    }

    @Override
    public int getLevelCount() {
        return this.levelCount;
    }

    @Override
    public void setLevel(int levelNumber) throws IllegalArgumentException {
        if (levelNumber > levelCount - 1) {
            throw new IllegalArgumentException("Level count exceeds maximum value.");
        }
        else {
            this.currentLevel = this.allMyLevels.get(levelNumber);
        }

    }

    /*

    ISquareHolder

    */

    @Override
    public void addSquare(Square square, int row, int column) throws IllegalArgumentException {
        if (row > this.currentLevel.height || row < 0 || column > this.currentLevel.width || column < 0){
            throw new IllegalArgumentException("Square outside of level range.");
        }
        square.row = row;
        square.column = column;
        this.currentLevel.mazeGrid.add(square);
    }
    @Override
    public Color getColorAt(int row, int column) {
        ArrayList<Square> squares = this.currentLevel.mazeGrid;
        for (Square square: squares) {
            if(row == square.getRow() && column == square.getColumn()){
                return square.color;
            }
        }
        return null;
    }

    @Override
    public Shape getShapeAt(int row, int column) {
        ArrayList<Square> squares = this.currentLevel.mazeGrid;
        for (Square square: squares) {
            if(row == square.getRow() && column == square.getColumn()){
                return square.shape;
            }
        }
        return null;
    }

    /*

    IEyeballHolder

    */

    @Override
    public void addEyeball(int row, int column, Direction direction) throws IllegalArgumentException {
        if (row > this.currentLevel.height || row < 0 || column > this.currentLevel.width || column < 0){
            throw new IllegalArgumentException("Eyeball outside of level range.");
        }
        Eyeball newEyeball = new Eyeball(row, column, direction);
        this.currentLevel.eyeball = newEyeball;
        this.currentLevel.assignEyeballColorAndShape(row, column);
    }

    @Override
    public int getEyeballRow() {
        return this.currentLevel.eyeball.row;
    }

    @Override
    public int getEyeballColumn() {
        return this.currentLevel.eyeball.column;
    }

    @Override
    public Direction getEyeballDirection() {
        return this.currentLevel.eyeball.direction;
    }

    /*

    IGoalHolder

    */

    @Override
    public void addGoal(int row, int column) throws IllegalArgumentException {
        if (row > this.currentLevel.height || row < 0 || column > this.currentLevel.width || column < 0) {
            throw new IllegalArgumentException("Goal square outside of range.");
        }
        Goal newGoal = new Goal(row, column);
        this.currentLevel.allMyGoals.add(newGoal);
    }

    @Override
    public int getGoalCount() {
        return this.currentLevel.allMyGoals.size();
    }

    @Override
    public boolean hasGoalAt(int targetRow, int targetColumn) {
        ArrayList<Goal> allMyGoals = this.currentLevel.allMyGoals;
        for (Goal goal: allMyGoals) {
            if(targetRow == goal.getRow() && targetColumn == goal.getColumn()){
                return true;
            }
        }
        return false;
    }

    @Override
    public int getCompletedGoalCount() {
        return this.currentLevel.completedGoalCount;
    }

    /*

    IMoving

    */

    @Override
    public boolean canMoveTo(int destinationRow, int destinationColumn) {
        if (isDirectionOK(destinationRow, destinationColumn) &&
                this.currentLevel.validShapeOrColor(destinationRow, destinationColumn)) {
            return true;
        }
        return false;
    }

    @Override
    public Message MessageIfMovingTo(int destinationRow, int destinationColumn) {
        if (!this.currentLevel.validShapeOrColor(destinationRow, destinationColumn)) {
            return Message.DIFFERENT_SHAPE_OR_COLOR;
        }
        return this.checkDirectionMessage(destinationRow, destinationColumn);
    }

    @Override
    public boolean isDirectionOK(int destinationRow, int destinationColumn) {
        if (this.currentLevel.legalMove(destinationRow, destinationColumn) == Message.OK) {
            return true;
        }
        return false;
    }

    @Override
    public Message checkDirectionMessage(int destinationRow, int destinationColumn) {
        return this.currentLevel.legalMove(destinationRow, destinationColumn);
    }

    @Override
    public boolean hasBlankFreePathTo(int destinationRow, int destinationColumn) {
        return false;
    }

    @Override
    public Message checkMessageForBlankOnPathTo(int destinationRow, int destinationColumn) {
        return null;
    }

    @Override
    public void moveTo(int destinationRow, int destinationColumn) {
        if (canMoveTo(destinationRow,destinationColumn)) {
            this.currentLevel.changeEyeballDirection(destinationRow, destinationColumn);
            this.currentLevel.moveEyeball(destinationRow, destinationColumn);
        }
    }
}
