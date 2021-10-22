import java.io.BufferedReader;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static int rows;
    static int columns;

    static Boolean exit = false;
    static Boolean read = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (!exit) {
            while (true) {
                System.out.println("Please Enter Your Path (Format: M(rows, columns).csv): \n(Type \"Exit\" To Quit Program)");
                String x = sc.nextLine();
                if (x.equals("Exit")) {
                    exit = true;
                    break;
                }
                if (ReadMenu(x)) {
                    System.out.println("Reading Successful!");
                    read = true;
                    break;
                }
            }
            while(read){
                System.out.println("Main Menu");
                System.out.println("0: Insert\n1: Delete\n2: Search\n3: Update\n4: Print\n5: Back");
                System.out.println("Your Input: ");
                int input = sc.nextInt();
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

            System.out.println("rows:" + rows + "   columns:" + columns);

            int[][] matrix = new int[rows][columns];
            try (BufferedReader br = new BufferedReader(new FileReader(input))) {
                String line;
                int counter = 0;
                while ((line = br.readLine()) != null) {
                    String[] lineItems = line.split(",");
                    for (int i = 0; i < columns; i++) {
                        matrix[counter][i] = Integer.parseInt(lineItems[i]);

                        //prints while reading from file
                        System.out.print(lineItems[i] + ",");
                    }
                    System.out.println();
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

