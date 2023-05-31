public sealed class Square permits BlankSquare, PlayableSquare{
    public int row;
    public int column;
    protected Color color;
    protected Shape shape;

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

}
