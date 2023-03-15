import java.util.ArrayList;

public class Level {
    public int width;
    public int height;
    public int levelNumber;
    public ArrayList<Goal> allMyGoals = new ArrayList<Goal>();

    public Level(int newHeight, int newWidth, int newLevelNumber) {
        this.width = newWidth;
        this.height = newHeight;
        this.levelNumber = newLevelNumber;
    }

    public void addGoalToLevel(){
        //this.allMyGoals;
    }
}
