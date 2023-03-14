public class GoalHolder implements IGoalHolder {
    public void addGoal(int row, int column) {
        System.out.println("Hi");
    }
    public int getGoalCount() {
        return 1;
    }
    public boolean hasGoalAt(int targetRow, int targetColumn) {
        return true;
    }
    public int getCompletedGoalCount() {
        return 1;
    }
}
