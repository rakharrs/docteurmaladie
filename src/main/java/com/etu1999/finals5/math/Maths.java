package com.etu1999.finals5.math;

import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Maths {
    public static void main(String[] args) {
        double[] coeffs = {100, 1000};
        double constant = 0;
        LinearObjectiveFunction objectiveFunction = new LinearObjectiveFunction(coeffs, constant);
        List<LinearConstraint> linearConstraints = new ArrayList<>();
        linearConstraints.add(new LinearConstraint(new double[]{2, 0}, Relationship.GEQ, 4));
        linearConstraints.add(new LinearConstraint(new double[]{0.5, 1}, Relationship.GEQ, 2));

        SimplexSolver solver = new SimplexSolver();
        PointValuePair solution = solver.optimize(objectiveFunction, new LinearConstraintSet(linearConstraints), GoalType.MINIMIZE, new NonNegativeConstraint(true));
        System.out.println(Arrays.toString(solution.getPoint()));
    }
}