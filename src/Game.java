import java.util.ArrayList;

public class Game implements ILevelHolder, IEyeballHolder, IGoalHolder, ISquareHolder {

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
    public void addSquare(Square square, int row, int column) {
    }
    @Override
    public Color getColorAt(int row, int column) {
        return Color.BLUE;
    }

    @Override
    public Shape getShapeAt(int row, int column) {
        return Shape.BLANK;
    }

    /*

    IEyeballHolder

    */

    @Override
    public void addEyeball(int row, int column, Direction direction) {

    }

    @Override
    public int getEyeballRow() {
        return 0;
    }

    @Override
    public int getEyeballColumn() {
        return 0;
    }

    @Override
    public Direction getEyeballDirection() {
        return null;
    }

    /*

    IGoalHolder

    */

    @Override
    public void addGoal(int row, int column) {
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
        return 0;
    }
}
