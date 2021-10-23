public class Matrix {
    Node[] rowMatrix;
    Node[] columnMatrix;

    public Matrix(int row, int col) {
        rowMatrix = new Node[row];
        columnMatrix = new Node[col];
    }

    public void printDense() {

    }

    public void print2D() {
        //Just To Check Events Work Correctly

        System.out.println("----------------Row Wise-------------------");
        for (int i = 0; i < rowMatrix.length; i++) {
            if (rowMatrix[i] == null) {
                System.out.println("0s line");
            } else {
                Node n = rowMatrix[i];
                while (n != null) {
                    System.out.print(n.data + ",");
                    n = n.nextInRow;
                }
            }
            System.out.println();
        }
        System.out.println("----------------Column Wise-------------------");
        for (int i = 0; i < columnMatrix.length; i++) {
            if (columnMatrix[i] == null) {
                System.out.println("0s col");
            } else {
                Node n = columnMatrix[i];
                while (n != null) {
                    System.out.print(n.data + ",");
                    n = n.nextInColumn;
                }
            }
            System.out.println();
        }
    }

    public void add(Node node) {
        int row = node.rowIndex;
        int col = node.columnIndex;

        if (rowMatrix[row] == null) {
            rowMatrix[row] = node;
        } else {
            Node n1 = rowMatrix[row];
            while (n1.nextInRow != null) {
                n1 = n1.nextInRow;
            }
            n1.nextInRow = node;

        }

        if (columnMatrix[col] == null) {
            columnMatrix[col] = node;

        } else {
            Node n2 = columnMatrix[col];
            while (n2.nextInColumn != null) {
                n2 = n2.nextInColumn;
            }
            n2.nextInColumn = node;

        }
    }

    public void insert(int row, int col, int value) {
        Node node = new Node(row, col, value);
        if (rowMatrix[row] == null) {
            rowMatrix[row] = node;
        } else {
            Node n = rowMatrix[row];
            if (n.columnIndex < col) {
                while (n.nextInRow != null) {
                    if (n.nextInRow.columnIndex > col) {
                        break;
                    } else {
                        n = n.nextInRow;
                    }
                }
                Node temp = n;
                temp = temp.nextInRow;
                n.nextInRow = node;
                node.nextInRow = temp;
            } else {
                rowMatrix[row] = node;
                rowMatrix[row].nextInRow = n;
            }
        }

        if (columnMatrix[col] == null) {
            columnMatrix[col] = node;
        } else {
            Node n = columnMatrix[col];
            if (n.rowIndex < row) {
                while (n.nextInColumn != null) {
                    if (n.nextInColumn.rowIndex > row) {
                        break;
                    } else {
                        n = n.nextInColumn;
                    }
                }
                Node temp = n;
                temp = temp.nextInColumn;
                n.nextInColumn = node;
                node.nextInColumn = temp;
            } else {
                columnMatrix[col] = node;
                columnMatrix[col].nextInColumn = n;
            }
        }
    }

    public void delete(int row, int col) {
        Node r = rowMatrix[row];
        Node c = columnMatrix[col];

        Node temp = null;
        if (r.columnIndex == col) {
            rowMatrix[row] = r.nextInRow;
        } else {
            while (r.nextInRow != null) {
                if (r.nextInRow.columnIndex == col) {
                    temp = r.nextInRow.nextInRow;
                    break;
                }
                r = r.nextInRow;
            }
            r.nextInRow = temp;
        }

        if (c.rowIndex == row) {
            columnMatrix[col] = c.nextInColumn;
        } else {
            while (c.nextInColumn != null) {
                if (c.nextInColumn.rowIndex == row) {
                    temp = c.nextInColumn.nextInColumn;
                    break;
                }
                c = c.nextInColumn;
            }
            c.nextInColumn = temp;
        }
    }

    public void search(int value) {
        int counter = 0;
        for (Node matrix : rowMatrix) {
            if (matrix != null) {
                Node n = matrix;
                while (n != null) {
                    if (n.data == value) {
                        System.out.println("Value: " + value + "  Found in: (" + n.rowIndex + "," + n.columnIndex + ")");
                        counter++;
                    }
                    n = n.nextInRow;
                }
            }
        }
        if (counter == 0) {
            System.out.println("The Value You Entered Wasn't Found!");
        } else {
            System.out.println("The Value You Entered Was Found " + counter + " time(s)!");
        }
    }

    public void update(int row, int col, int value) {
        for (Node matrix : rowMatrix) {
            if (matrix != null) {
                Node n = matrix;
                while (n != null) {
                    if (n.rowIndex == row && n.columnIndex == col) {
                        n.data = value;
                        System.out.println("Value Updated Successfully!");
                        break;
                    }
                    n = n.nextInRow;
                }
            }
        }
    }

    public Boolean exist(int row, int col) {
        if (rowMatrix[row] == null) {
            return false;
        }
        Node n = rowMatrix[row];
        while (n != null) {
            if (n.columnIndex == col) {
                return true;
            }
            n = n.nextInRow;
        }
        return false;
    }

}
