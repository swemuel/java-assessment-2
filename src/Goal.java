public class Goal {
    int row;
    int column;

    public Goal(int newRow, int newColumn) {
        this.row = newRow;
        this.column = newColumn;
    }

    public int getRow(){
        return this.row;
    }

    public int getColumn(){
        return this.column;
    }
}
