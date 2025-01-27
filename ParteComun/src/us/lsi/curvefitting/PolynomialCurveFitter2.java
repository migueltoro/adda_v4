package us.lsi.curvefitting;

/*
 *  Clase adaptada de org.apache.commons.math3.fitting.PolynomialCurveFitter;
 *  
002 * Licensed to the Apache Software Foundation (ASF) under one or more
003 * contributor license agreements.  See the NOTICE file distributed with
004 * this work for additional information regarding copyright ownership.
005 * The ASF licenses this file to You under the Apache License, Version 2.0
006 * (the "License"); you may not use this file except in compliance with
007 * the License.  You may obtain a copy of the License at
008 *
009 *      http://www.apache.org/licenses/LICENSE-2.0
010 *
011 * Unless required by applicable law or agreed to in writing, software
012 * distributed under the License is distributed on an "AS IS" BASIS,
013 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
014 * See the License for the specific language governing permissions and
015 * limitations under the License.
016 */
import java.util.Collection;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.fitting.AbstractCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoint;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;
import org.apache.commons.math3.linear.DiagonalMatrix;

/**
028 * Fits points to a {@link
029 * org.apache.commons.math3.analysis.polynomials.PolynomialFunction.Parametric polynomial}
030 * function.
031 * <br/>
032 * The size of the {@link #withStartPoint(double[]) initial guess} array defines the
033 * degree of the polynomial to be fitted.
034 * They must be sorted in increasing order of the polynomial's degree.
035 * The optimal values of the coefficients will be returned in the same order.
036 *
037 * @since 3.3
038 */
public class PolynomialCurveFitter2 extends AbstractCurveFitter {
    /** Parametric function to be fitted. */
    private static final PolynomialFunction.Parametric FUNCTION = new PolynomialFunction.Parametric();
    /** Initial guess. */
    private final double[] initialGuess;
    /** Maximum number of iterations of the optimization algorithm. */
    private final int maxIter;

    /**
048     * Contructor used by the factory methods.
049     *
050     * @param initialGuess Initial guess.
051     * @param maxIter Maximum number of iterations of the optimization algorithm.
052     * @throws MathInternalError if {@code initialGuess} is {@code null}.
053     */
    protected PolynomialCurveFitter2(double[] initialGuess,
                                  int maxIter) {
        this.initialGuess = initialGuess;
        this.maxIter = maxIter;
    }

    /**
061     * Creates a default curve fitter.
062     * Zero will be used as initial guess for the coefficients, and the maximum
063     * number of iterations of the optimization algorithm is set to
064     * {@link Integer#MAX_VALUE}.
065     *
066     * @param degree Degree of the polynomial to be fitted.
067     * @return a curve fitter.
068     *
069     * @see #withStartPoint(double[])
070     * @see #withMaxIterations(int)
071     */
    public static PolynomialCurveFitter2 create(int degree) {
        return new PolynomialCurveFitter2(new double[degree + 1], Integer.MAX_VALUE);
    }

    /**
077     * Configure the start point (initial guess).
078     * @param newStart new start point (initial guess)
079     * @return a new instance.
080     */
    public PolynomialCurveFitter2 withStartPoint(double[] newStart) {
        return new PolynomialCurveFitter2(newStart.clone(),
                                         maxIter);
    }

    /**
087     * Configure the maximum number of iterations.
088     * @param newMaxIter maximum number of iterations
089     * @return a new instance.
090     */
    public PolynomialCurveFitter2 withMaxIterations(int newMaxIter) {
        return new PolynomialCurveFitter2(initialGuess,
                                         newMaxIter);
    }

    /** {@inheritDoc} */
    @Override
    public LeastSquaresProblem getProblem(Collection<WeightedObservedPoint> observations) {
        // Prepare least-squares problem.
        final int len = observations.size();
        final double[] target  = new double[len];
        final double[] weights = new double[len];

        int i = 0;
        for (WeightedObservedPoint obs : observations) {
            target[i]  = obs.getY();
            weights[i] = obs.getWeight();
            ++i;
        }

        final AbstractCurveFitter.TheoreticalValuesFunction model =
                new AbstractCurveFitter.TheoreticalValuesFunction(FUNCTION, observations);

        if (initialGuess == null) {
            throw new MathInternalError();
        }

       // Return a new least squares problem set up to fit a polynomial curve to the
        // observed points.
        return new LeastSquaresBuilder().
                maxEvaluations(Integer.MAX_VALUE).
                maxIterations(maxIter).
                start(initialGuess).
                target(target).
                weight(new DiagonalMatrix(weights)).
                model(model.getModelFunction(), model.getModelFunctionJacobian()).
                build();

    }

}
