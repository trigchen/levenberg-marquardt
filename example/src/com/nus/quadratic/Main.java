package com.nus.quadratic;

/**
 * Created by duy on 1/2/15.
 */

import com.nus.LmSumSquaresError;
import com.nus.LmSolver;

public class Main {
  /**
   * Task: Suppose the true model is f(x) = 3 * x^2 - 7 * x
   *
   * Given the set of points generated by f(x) = 3 * x^2 - 7 * x with some
   * noise, use Levenberg-Marquardt algorithm to find the model to fit
   * all the points.
   */
  public static void main(String[] args) {
    double[][] xy = {
      {0.0, 0.1}, {1.0, -3.999}, {1.5, -3.74}, {2.3, -0.24}, {3.0, 6.05},
      {5.0, 41.01}, {-1, 10.08}, {0.5, -2.75}, {-10, 371}, {5.1, 42.33},
      {-0.4, 3.281}, {-2.0, 26.0}, {6.99, 97.65}
    };

    int numData = xy.length;
    double x[] = new double[numData];
    double y[] = new double[numData];
    for (int i = 0; i < numData; ++i) {
      x[i] = xy[i][0];
      y[i] = xy[i][1];
    }

    ModelQuadraticFunc model = new ModelQuadraticFunc(x, y);
    LmSumSquaresError errorFunc = new LmSumSquaresError(model);

    LmSolver lmSolver = new LmSolver(0.0001, 100, 0.00000001, errorFunc);

    double originalAB[] = {100, -1000};
    double ab[] = {100, -1000};
    lmSolver.solve(ab);

    System.out.printf("Result with initial guess (a, b) = (%f, %f):\n",
      originalAB[0], originalAB[1]);
    System.out.println(ab[0]);
    System.out.println(ab[1]);

    originalAB[0] = -3000;
    originalAB[1] = 10;
    ab[0] = -3000;
    ab[1] = 10;
    lmSolver.solve(ab);
    System.out.printf("Result with initial guess (a, b) = (%f, %f):\n",
      originalAB[0], originalAB[1]);
    System.out.println(ab[0]);
    System.out.println(ab[1]);
  }
}
