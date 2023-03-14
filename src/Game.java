import java.util.ArrayList;

public class Game {

    private int levelCount = 0;
    private Level currentLevel;
    public ArrayList<Level> allMyLevels = new ArrayList<Level>();

    public void addLevel(int height, int width) {
        Level newLevel = new Level(height, width, this.levelCount);
        this.allMyLevels.add(newLevel);
        this.levelCount += 1;
        this.setLevel(this.levelCount - 1);
    }

    public int getLevelWidth() {
        return currentLevel.width;
    }

    public int getLevelHeight() {
        return currentLevel.height;
    }

    public int getLevelCount() {
        return this.levelCount;
    }

    public void setLevel(int levelNumber) throws IllegalArgumentException {
        if (levelNumber > levelCount - 1) {
            throw new IllegalArgumentException("Level count exceeds maximum value.");
        }
        else {
            this.currentLevel = this.allMyLevels.get(levelNumber);
        }

    }

}
