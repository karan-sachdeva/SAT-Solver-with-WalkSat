package WalkSat;

import java.util.*;
import java.util.stream.*;
import java.io.*;
import java.nio.file.Paths;
import static java.nio.file.Files.lines;
import static java.lang.System.exit;

public class Sat {

    // member variables
    private final int k; // number of literals
    private int x; // number of clauses
    private int y; // number of symbols
    static int count = 0; // counter variable
    private final List<ArrayList<Integer>> literals = new ArrayList<>(); // list to store literals
    private final ArrayList<Integer> solution = new ArrayList<>(); // list to store the solution

    //getters for efficient programming
    public int getK() {
        return this.k;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public List<ArrayList<Integer>> getLiterals() {
        return this.literals;
    }

    public ArrayList<Integer> getSolution() {
        return this.solution;
    }

    // Non-default constructor used to initialize values of member variables like x,y,k.
    public Sat(String file_name) throws IOException {
        List<String> clause_list; // list to store clauses
        Stream<String> stream = lines(Paths.get("src/" + file_name)); //reads the file
        clause_list = stream.collect(Collectors.toList()); // stores lines of the files in the list
        for(int a=0, i=0; a<clause_list.size(); a++, i++) {
            String[] refined_clause_list = clause_list.get(a).split(" "); // splits lines of clause and stores it as different characters in refined_clause_list
            if (i > 0) {
                ArrayList<Integer> temp = new ArrayList<>();
                for (String s : refined_clause_list) {
                    temp.add(Integer.parseInt(s)); // adds every string in refined_clause_list in temp as an integer
                }
                temp.remove(temp.size() - 1); // removes the zeroes because we don't need them
                this.literals.add(temp); // adds arraylist temp to list literals
            }
            else {
                x = Integer.parseInt(refined_clause_list[2]); // assigns x the number of clauses
                y = Integer.parseInt(refined_clause_list[3]); // assigns y the number of variables
                solveCNF();
            }
        }
        k = this.literals.get(0).size(); // assigns the number of literals to k
    }

    //helper function for constructor
    private void solveCNF() {
        for(int i=0; i <=this.getY()-1; i++) {
            Random new_rand= new Random(); // creates a new random object using the Random class
            boolean rand_bool = new_rand.nextBoolean(); // gives us a random boolean
            if(!rand_bool)
                this.getSolution().add(-1*(i+1));
            else
                this.getSolution().add(i + 1);
        }
    }

    public void calculate() {
        count = count + 1;
        if(count > 6000) {  // If trials exceed 6000, stop the program.
            System.out.println("Sorry, no solution could be found! Please try again");
            exit(0);
        }
        int c = 0;
        for(int i = 0; i <= this.getX()-1; i++) {
            if(!checkClause(i))
                break;
            c += 1;
        }
        if(c != this.getX()) {
            solveCNF();calculate();
        }
    }

    // helper function for calculate function
    private boolean checkClause(int index) {
        ArrayList<Integer> clause = this.getLiterals().get(index); // clause is a set of literals stored in 'literals' as an arraylist
        for(int a=0; a <=this.getY()-1; a++) {
            for(int b = 0; b <=this.getK()-1; b++) {
                if ((this.getSolution().get(a)).equals(clause.get(b))) // checks equality
                    return true;
            } }
        return false;
    }

}
