public class Node {
    Node nextInRow;
    Node nextInColumn;
    int rowIndex, columnIndex;
    int data;

    public Node(int rowIndex, int columnIndex, int data) {
        this.data = data;
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }
}
