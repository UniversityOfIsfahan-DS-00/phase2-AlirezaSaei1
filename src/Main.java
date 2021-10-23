import java.io.BufferedReader;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static int rows;
    static int columns;
    static Matrix matrix;

    static Boolean exit = false;
    static Boolean read = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (!exit) {
            while (true) {
                System.out.println("Please Enter Your Path (Format: M(rows,columns).csv): \n(Type \"0\" To Quit Program)");
                String x = sc.nextLine();
                if (x.equals("0")) {
                    exit = true;
                    break;
                }
                if (ReadMenu(x)) {
                    System.out.println("Reading Successful!");
                    read = true;
                    break;
                }
            }
            while (read) {
                System.out.println("Main Menu");
                System.out.println("0: Insert\n1: Delete\n2: Search\n3: Update\n4: Print\n5: Back");
                System.out.println("Your Input: ");
                int input = sc.nextInt();
                if (input == 0) {
                    System.out.println("Enter Row, Column, Value :");
                    int row = sc.nextInt(), column = sc.nextInt(), value = sc.nextInt();
                    if(value == 0){
                        System.out.println("Do You Want To Insert 0 To Sparse Matrix?!");
                    }else {
                        try {
                            if (!matrix.exist(row, column)) {
                                matrix.insert(row, column, value);
                                System.out.println("Value Successfully Inserted!");
                            } else {
                                System.out.println("Location Already Has A Value!");
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else if (input == 1) {
                    System.out.println("Enter Row, Column:");
                    int row = sc.nextInt(), column = sc.nextInt();
                    if(matrix.exist(row, column)) {
                        matrix.delete(row, column);
                    }else{
                        System.out.println("There's No Value To Be Deleted!");
                    }
                } else if (input == 2) {
                    System.out.println("Enter Value To Look For:");
                    int x = sc.nextInt();
                    if(x == 0){
                        System.out.println("Bro! This Matrix Is Full Of 0s!!!");
                    }else {
                        matrix.search(x);
                    }
                } else if (input == 3) {

                } else if (input == 4) {
                    matrix.print2D();
                } else if (input == 5) {
                    read = false;
                    sc.nextLine();
                    break;
                } else {
                    System.out.println("Please Enter A Valid Number");
                }
            }
        }
    }

    private static Boolean ReadMenu(String input) {
        try {
            ArrayList<Integer> size = new ArrayList<>();
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(input);
            while (m.find()) {
                size.add(Integer.parseInt(m.group()));
            }
            rows = size.get(size.size() - 2);
            columns = size.get(size.size() - 1);

            matrix = new Matrix(rows, columns);

            try (BufferedReader br = new BufferedReader(new FileReader(input))) {
                String line;
                int counter = 0;
                while ((line = br.readLine()) != null) {
                    String[] lineItems = line.split(",");
                    for (int i = 0; i < columns; i++) {
                        if (!lineItems[i].equals("0")) {
                            matrix.add(new Node(counter, i, Integer.parseInt(lineItems[i])));
                        }
                    }
                    counter++;
                }
            } catch (Exception e) {
                System.out.println("There Was An Error Reading File, please Try Again!");
                return false;
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error Occurred! Please Double-Check Your Path!");
            return false;
        }
    }
}

