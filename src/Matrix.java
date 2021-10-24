import java.io.FileWriter;

public class Matrix {
    Node[] rowMatrix;
    Node[] columnMatrix;

    public Matrix(int row, int col) {
        rowMatrix = new Node[row];
        columnMatrix = new Node[col];
    }

    public void printCompressed() {
        System.out.println("[row] [col] [value]");
        for (Node n : rowMatrix) {
            while (n != null) {
                System.out.printf("%5s %5s %5s%n", n.rowIndex, n.columnIndex, n.data);
                n = n.nextInRow;
            }
        }
    }

    public void print2D() {
        for (Node n : rowMatrix) {
            int last = 0;
            if (n == null) {
                for (int i = 0; i < columnMatrix.length; i++) {
                    System.out.printf("%4s", 0);
                }
            }
            while (n != null) {
                for (int i = last; i < n.columnIndex; i++) {
                    System.out.printf("%4s", 0);
                }
                last = n.columnIndex + 1;
                System.out.printf("%4s", n.data);
                n = n.nextInRow;
                if (last < columnMatrix.length && n == null) {
                    while (last < columnMatrix.length) {
                        System.out.printf("%4s", 0);
                        last++;
                    }
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

    public void save_file(String path) {
        StringBuilder[] lines = new StringBuilder[rowMatrix.length];
        for (int i = 0; i < lines.length; i++) {
            lines[i] = new StringBuilder("");
        }
        try {
            int counter = 0;
            for (Node n : rowMatrix) {
                int last = 0;
                if (n == null) {
                    for (int i = 0; i < columnMatrix.length; i++) {
                        lines[counter].append(0 + ",");
                    }
                }

                while (n != null) {
                    for (int i = last; i < n.columnIndex; i++) {
                        lines[counter].append(0 + ",");
                    }
                    last = n.columnIndex + 1;
                    lines[counter].append(n.data).append(",");
                    n = n.nextInRow;
                    if (last < columnMatrix.length && n == null) {
                        while (last < columnMatrix.length) {
                            lines[counter].append(0 + ",");
                            last++;
                        }
                    }
                }
                counter++;
            }
        } catch (Exception ignored) {
        }
        try {
            FileWriter writer = new FileWriter(path);
            for (StringBuilder line : lines) {
                line.deleteCharAt(line.length() - 1);
                writer.write(line + "\n");
            }
            System.out.println("Data Successfully Saved!");
            writer.flush();
            writer.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
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
