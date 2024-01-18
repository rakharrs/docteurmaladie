package com.etu1999.finals5.math;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Code by Bijendar Prasad | IIITD'23
 */

/**
 * This is a necessary condition for solving the problem:
 * the numbers on the right parts of the constraint system must be non-negative.
 */

/**
 * Since, Simplex method for LPPs with ≥ and = constraints needs a modified approach.
 * So We use Big-M method, but Big M method raises difficulty when the problem is solved on a digital computer.
 * Convert minimization into maximization problem.
 */

@NoArgsConstructor @Setter @Getter
public class BigM {

     int M = 999999;
     List<Integer> C_j = new ArrayList<>();
     List<Float> Z_j = new ArrayList<>();

     List<Integer> C_B = new ArrayList<>();
     List<String> B = new ArrayList<>();
     List<Float> X_B = new ArrayList<>();

     List<String> variables = new ArrayList<>();

     List<String> artificial_variables = new ArrayList<>();

     List<Float> difference = new ArrayList<>();


     float[][] table;
     String[] row_variables;        // all variables in a row
     String[] basic_variables;       // contain basic variables

     boolean problemType = false;      // Problem Type

     boolean canPrint = true;

     public BigM(int type, int[] objectives_arr, List<List<Float>> constraints, List<Float> rhs){
         int n = objectives_arr.length;
         int m = constraints.size();
         problemType = type == 1;
         for (int i = 0; i < n; i++) {
             objectives_arr[i] = problemType ? objectives_arr[i] : - objectives_arr[i];
             String str = "x" + (i+1);
             variables.add(str);
         }

         for (int i=0; i<objectives_arr.length; i++) {
             C_j.add(objectives_arr[i]);
         }

         int point = n;
         int var = 1;
         int art = 1;

         for (int i = 0; i < m; i++) {
            float b_val = rhs.get(i);
             X_B.add((float) Math.abs(b_val));
             for (int j=0; j<point; j++) {
                 constraints.get(i).add((float) 0);
             }

             constraints.get(i).set(point++, (float)-1.0);
             C_j.add(0);
             constraints.get(i).set(point++, (float)1.0);
             C_j.add(-M);

             String s = "s" + (var++);
             variables.add(s);

             String a = "a" + (art++);
             variables.add(a);
         }

         table = new float[m][constraints.get(constraints.size()-1).size()];
         for (int i=0; i<constraints.size(); i++) {
             for (int j=0; j<constraints.get(i).size(); j++) {
                 table[i][j] = constraints.get(i).get(j);
             }
         }


         System.out.println(Arrays.deepToString(table));

         for (int i=0; i<table.length; i++) {
             for (int j=n; j<C_j.size(); j++) {
                 if(table[i][j] == 1.0) {
                     B.add(variables.get(j));
                     C_B.add(C_j.get(j));
                 }
             }
         }

         for (int i=0; i<C_j.size(); i++) {
             if(C_j.get(i) == -M) {
                 artificial_variables.add(variables.get(i));
             }
         }


     }
    public  void initialize() {

        Scanner input = new Scanner(System.in);

        System.out.println("Choose Problem Type:\n" +"\t\t 1) Maximization Problem \n" + "\t\t 2) Minimization Problem");
        System.out.print("Enter chosen type: ");

        int type = input.nextInt();

        /**
         String prob_type = Input.next();
         int type = 0;

         try {
         type = Integer.parseInt(prob_type);
         } catch (NumberFormatException ex) {
         System.out.println("Wrong input!!!");
         input.close();
         }
         */

        while (type > 2 || type <= 0) {
            System.out.println("Invalid Input");
            System.out.print("Enter chosen type again: ");
            type = input.nextInt();
        }

        problemType = type == 1;        // Assign max/min type...

        System.out.print("Enter No. of variables: ");
        int n = input.nextInt();

        System.out.print("Enter No. of constraints: ");
        int m = input.nextInt();

        System.out.println("Enter coefficients of Objective Function:");
        int[] objective_arr = new int[n];

        for (int i=0; i<n; i++) {
            System.out.print("Enter the value of " + "x" + (i+1) + ": ");
            objective_arr[i] = problemType ? input.nextInt() : - input.nextInt();

            String str = "x" + (i+1);
            variables.add(str);

        }

        for (int i=0; i<objective_arr.length; i++) {
            C_j.add(objective_arr[i]);
        }

        List<List<Float>> list = new ArrayList<>();
        int point = n;
        int var = 1;
        int art = 1;

        for (int j=0; j<m; j++) {
            System.out.println("Enter LHS coefficients of constraints(" + (j+1) + ") : ");

            List<Float> l = new ArrayList<>();

            for (int i=0; i<n; i++) {
                System.out.print("Enter the value of " + "x" + (i+1) + ": ");
                float num = input.nextFloat();
                l.add(num);

            }

            System.out.println("Choose Inequality option: \n" + "\t\t 1) ≤ \n" + "\t\t 2) ≥ \n" + "\t\t 3) = ");
            System.out.print("Enter chosen option: ");
            int choice = input.nextInt();

            while (choice > 3 || choice <= 0) {
                System.out.println("Invalid Input");
                System.out.print("Enter chosen option: ");
                choice = input.nextInt();
            }

            System.out.print("Enter RHS coefficient of constraints(" + (j+1) + ") : ");
            int b_val = input.nextInt();
            X_B.add((float) Math.abs(b_val));

            for (int i=0; i<point; i++) {
                l.add((float) 0);
            }

            if(b_val < 0) {    // negative value...

                for (int i=0; i<l.size(); i++) {
                    l.set(i, -l.get(i));
                }

                if(choice == 1) {
                    // slack variable added
                    l.set(point++, (float)-1.0);
                    C_j.add(0);

                    l.set(point++, (float)1.0);
                    C_j.add(-M);

                    String s = "s" + (var++);
                    variables.add(s);

                    String a = "a" + (art++);
                    variables.add(a);

                } else if(choice == 2) {
                    // surplus variable added
                    l.set(point++, (float)1.0);
                    C_j.add(0);

                    String s = "s" + (var++);
                    variables.add(s);

                } else {
                    l.set(point++, (float)1.0);
                    C_j.add(-M);

                    String a = "a" + (art++);
                    variables.add(a);
                }

            } else {

                if(choice == 1) {
                    // slack variable added
                    l.set(point++, (float)1.0);
                    C_j.add(0);

                    String s = "s" + (var++);
                    variables.add(s);

                } else if(choice == 2) {
                    // surplus variable added
                    l.set(point++, (float)-1.0);
                    C_j.add(0);
                    l.set(point++, (float)1.0);
                    C_j.add(-M);

                    String s = "s" + (var++);
                    variables.add(s);

                    String a = "a" + (art++);
                    variables.add(a);

                } else {
                    l.set(point++, (float)1.0);
                    C_j.add(-M);

                    String a = "a" + (art++);
                    variables.add(a);
                }
            }

            list.add(l);

        }

        System.out.println();
//        System.out.println(C_j);

//        for (int i=0; i<list.size(); i++) {
//            System.out.println(list.get(i));
//        }

        table = new float[m][list.get(list.size()-1).size()];
        for (int i=0; i<list.size(); i++) {
            for (int j=0; j<list.get(i).size(); j++) {
                table[i][j] = list.get(i).get(j);
            }
        }


        System.out.println(Arrays.deepToString(table));

        for (int i=0; i<table.length; i++) {
            for (int j=n; j<C_j.size(); j++) {
                if(table[i][j] == 1.0) {
                    B.add(variables.get(j));
                    C_B.add(C_j.get(j));
                }
            }
        }

//        System.out.println(variables);
//        System.out.println(C_B);
//        System.out.println(B);
//        System.out.println(X_B);

        for (int i=0; i<C_j.size(); i++) {
            if(C_j.get(i) == -M) {
                artificial_variables.add(variables.get(i));
            }
        }

//        System.out.println(artificial_variables);


//        fill_variables(n, m);
//
//        System.out.println(Arrays.toString(row_variables));
//        System.out.println(Arrays.toString(basic_variables));


//
        System.out.println();
//
        optimize_table();

        System.out.println();

        System.out.println("Final table: " + Arrays.deepToString(table));

        System.out.println();

        if(canPrint) {
            print_solution();
        }

    }


    public  void optimize_table() {

        int iter = 1;

        while (ifminExists() && iter < 4) {

            int index = min_index();

            float min_ratio = Float.MAX_VALUE;
            int min_index = 0;

            boolean state = false;

            for (int j=0; j<table.length; j++) {

                if (table[j][index] > 0) {           // must be > 0
                    state = true;
                    float ratio = X_B.get(j) / table[j][index];

                    if(ratio < min_ratio) {
                        min_ratio = ratio;
                        min_index = j;
                    }
                }
            }

//            System.out.println("Statehdhddh:" + state);

            if(!state) {
                System.out.println("******* This system has unbounded solution *******");
                canPrint = false;
                break;

            } else {

                System.out.println("************ Iteration - " + iter + " ************");
                System.out.println("Incoming Variable is: " + variables.get(index));
                System.out.println("Outgoing Variable is: " + B.get(min_index));
                System.out.println();

                B.set(min_index, variables.get(index));    // swap basic variables...
                C_B.set(min_index, C_j.get(index));

//                System.out.println("Cb:" + C_B);
//                System.out.println("B:" + B);

                row_operation(index, min_index);       // row operation in table...

//                System.out.println("X_b: " + X_B);

                iter++;

            }

            Z_j = new ArrayList<>();
            difference = new ArrayList<>();

        }
    }


    public  void row_operation(int index, int min_index) {

        float num = table[min_index][index];

        X_B.set(min_index, X_B.get(min_index)/num);

        for (int i=0; i<C_j.size(); i++) {
            table[min_index][i] = table[min_index][i] / num;
        }

//        System.out.println(Arrays.deepToString(table));

        for (int i=0; i<table.length; i++) {

            if (i != min_index) {
                float cal = -table[i][index];

                for (int j=0; j<C_j.size(); j++) {
                    table[i][j] = cal*table[min_index][j] + table[i][j];
                }

//                System.out.println("cal: " + cal);
//                System.out.println("min_index: " + X_B.get(min_index));
//                System.out.println("min: " + X_B.get(index));
//                System.out.println("i: " + X_B.get(i));

                X_B.set(i, cal*X_B.get(min_index) + X_B.get(i));

            }
        }

//        System.out.println(Arrays.deepToString(table));


    }

    public float solution() throws Exception{
         if(!canPrint)
             throw new Exception("No solution");
        float total = 0;
        for (int i=0; i<X_B.size(); i++) {
            System.out.println(C_B);
            System.out.println(X_B);
            total += C_B.get(i)*X_B.get(i);
        }
        if(problemType)
            return total;
        return -total;
    }


    public  void print_solution() {

        boolean yes = true;

        for (int i=0; i<artificial_variables.size(); i++) {
            for (int j=0; j<B.size(); j++) {
                if(artificial_variables.get(i).equals(B.get(j)) && X_B.get(j)>0) {
                    yes = false;
                    break;
                }

            }
        }


        if (!yes) {
            System.out.println("*************** No Feasible Solution: *********************");

        } else {

            System.out.println("*************** Basic Feasible Solution: *********************");

            for (int i=0; i<variables.size(); i++) {
                boolean state = false;

                for (int j=0; j<B.size(); j++) {
                    if(variables.get(i).equals(B.get(j))) {
                        System.out.println("The value of " + variables.get(i) + " is: " + X_B.get(j));
                        state = true;
                        break;
                    }

                }

                if(!state) {
                    System.out.println("The value of " + variables.get(i) + " is: " + 0);
                }
            }

            float total = 0;
            for (int i=0; i<X_B.size(); i++) {
                total += C_B.get(i)*X_B.get(i);
            }

            if (problemType) {
                System.out.println("The value of Z_max is: " + total);
            } else {
                System.out.println("The value of Z_min is: " + -total);
            }

        }

    }
    public List<Float> getSolutions() throws Exception{
         if(!canPrint)
             throw new Exception("No solution");
        List<Float> val = new ArrayList<>();
        for (int i = 0; i < variables.size(); i++) {
            boolean state = false;

            for (int j=0; j<B.size(); j++) {
                if(variables.get(i).equals(B.get(j))) {
                    System.out.println("The value of " + variables.get(i) + " is: " + X_B.get(j));
                    if(variables.get(i).startsWith("x"))
                        val.add(X_B.get(j));
                    state = true;
                    break;
                }

            }

            if(!state) {
                System.out.println("The value of " + variables.get(i) + " is: " + 0);
                if(variables.get(i).startsWith("x"))
                    val.add((float)0);
            }
        }
        return val;
    }


    public  void diff() {

        for (int i=0; i<C_j.size(); i++) {
            float val = 0;
            for (int j=0; j<table.length; j++) {
                val += C_B.get(j)*table[j][i];
            }

            Z_j.add(val);
            difference.add(Z_j.get(i) - C_j.get(i));
        }

//        System.out.println(Z_j);
//        System.out.println(difference);

    }


    public  boolean ifminExists() {

        diff();

        boolean state = false;

        for (int i=0; i<difference.size(); i++) {
            if (difference.get(i) < 0) {
                state = true;
                break;
            }
        }

        return state;
    }


    public  int min_index() {

        int index = 0;
        float min = Float.MAX_VALUE;

        for (int i=0; i<difference.size(); i++) {
            if (difference.get(i) < min) {
                index = i;
                min = difference.get(i);
            }
        }

//        System.out.println(min);

        return index;
    }

    public  void fill_variables(int n, int m) {

        basic_variables = new String[m+1];
        basic_variables[0] = "c";

        for (int i=0; i<m; i++) {
            basic_variables[i+1] = "s" + (i+1);
        }

        row_variables = new String[n+m+2];
        row_variables[0] = "z";
        for (int i=0; i<n; i++) {
            row_variables[i+1] = "x" + (i+1);
        }

        for (int i=0; i<m; i++) {
            row_variables[n+i+1] = "s" + (i+1);
        }

        row_variables[n+m+1] = "b";

    }

    public static void main(String[] args) throws Exception{
         int[] objectives_arr = {10000, 5000};
        List<List<Float>> constraints = new ArrayList<>();
        List<Float> floats1 = new ArrayList<>();
        floats1.add((float) 4);
        floats1.add((float) 3);

        List<Float> floats2 = new ArrayList<>();
        floats2.add((float) 2);
        floats2.add((float) 0);

        List<Float> floats3 = new ArrayList<>();
        floats3.add((float) 1);
        floats3.add((float) 0);

        List<Float> floats4 = new ArrayList<>();
        floats4.add((float) 0);
        floats4.add((float) 2);

        constraints.add(floats1);
        constraints.add(floats2);
        constraints.add(floats3);
        constraints.add(floats4);

        List<Float> rhsl = new ArrayList<>();
        rhsl.add((float)5);
        rhsl.add((float)3);
        rhsl.add((float)4);
        rhsl.add((float)5);

        BigM bigM = new BigM(2, objectives_arr, constraints, rhsl);

        BigM f = new BigM();
        bigM.optimize_table();
        System.out.println(bigM.getSolutions());
        System.out.println(bigM.solution());
        //f.initialize();

    }
}
