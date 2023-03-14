public interface IGoalHolder {
    public void addGoal(int row, int column);
    public int getGoalCount();
    public boolean hasGoalAt(int targetRow, int targetColumn);
    public int getCompletedGoalCount();
}
