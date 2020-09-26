package WalkSat;

import java.util.Scanner;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Welcome to my WalkSat!");
            System.out.println("Please input the name of your desired text file, eg. sample.txt ");
            String file_name = scanner.nextLine();
            Sat sat = new Sat(file_name); // calls non-default constructor
            double start_time = System.nanoTime(); // time recorded at start
            sat.calculate(); // Calculates solution if any and stores inside solution
            double end_time = System.nanoTime(); // time recorded at end
            System.out.print("\nSolution: ");
            for (int i=0; i< sat.getY()-1; i++)
                System.out.print(sat.getSolution().get(i) + ", ");
            System.out.println(sat.getSolution().get(sat.getY()-1) + "\n");
            double elapsed_time = ((end_time - start_time)/1000000000); // convert into seconds
            System.out.print("Time taken to calculate solution: ");
            System.out.println(elapsed_time);
        }
    }
}



