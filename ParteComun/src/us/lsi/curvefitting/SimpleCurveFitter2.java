

	/*
	 * Clase adaptada de org.apache.commons.math3.fitting.SimpleCurveFitter;
	 * 
	 * 002 * Licensed to the Apache Software Foundation (ASF) under one or more 003
	 * * contributor license agreements. See the NOTICE file distributed with 004 *
	 * this work for additional information regarding copyright ownership. 005 * The
	 * ASF licenses this file to You under the Apache License, Version 2.0 006 *
	 * (the "License"); you may not use this file except in compliance with 007 *
	 * the License. You may obtain a copy of the License at 008 * 009 *
	 * http://www.apache.org/licenses/LICENSE-2.0 010 * 011 * Unless required by
	 * applicable law or agreed to in writing, software 012 * distributed under the
	 * License is distributed on an "AS IS" BASIS, 013 * WITHOUT WARRANTIES OR
	 * CONDITIONS OF ANY KIND, either express or implied. 014 * See the License for
	 * the specific language governing permissions and 015 * limitations under the
	 * License. 016
	 */

package us.lsi.curvefitting;

import java.util.Collection;
import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.fitting.AbstractCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoint;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;
import org.apache.commons.math3.linear.DiagonalMatrix;

public class SimpleCurveFitter2 extends AbstractCurveFitter {
		/** Function to fit. */
		private final ParametricUnivariateFunction function;
		/** Initial guess for the parameters. */
		private final double[] initialGuess;
		/** Maximum number of iterations of the optimization algorithm. */
		private final int maxIter;

	/**
	040     * Contructor used by the factory methods.
	041     *
	042     * @param function Function to fit.
	043     * @param initialGuess Initial guess. Cannot be {@code null}. Its length must
	044     * be consistent with the number of parameters of the {@code function} to fit.
	045     * @param maxIter Maximum number of iterations of the optimization algorithm.
	046     */
	protected SimpleCurveFitter2(ParametricUnivariateFunction function,
			double[] initialGuess,
			int maxIter) {
				this.function = function;
				this.initialGuess = initialGuess;
				this.maxIter = maxIter;
	}

		/**
		 * 056 * Creates a curve fitter. 057 * The maximum number of iterations of the
		 * optimization algorithm is set 058 * to {@link Integer#MAX_VALUE}. 059 * 060
		 * * @param f Function to fit. 061 * @param start Initial guess for the
		 * parameters. Cannot be {@code null}. 062 * Its length must be consistent with
		 * the number of parameters of the 063 * function to fit. 064 * @return a curve
		 * fitter. 065 * 066 * @see #withStartPoint(double[]) 067 * @see
		 * #withMaxIterations(int) 068
		 */
		public static SimpleCurveFitter2 create(ParametricUnivariateFunction f, double[] start) {
			return new SimpleCurveFitter2(f, start, Integer.MAX_VALUE);
		}

		/**
		 * 075 * Configure the start point (initial guess). 076 * @param newStart new
		 * start point (initial guess) 077 * @return a new instance. 078
		 */
		public SimpleCurveFitter2 withStartPoint(double[] newStart) {
			return new SimpleCurveFitter2(function, newStart.clone(), maxIter);
		}

		/**
		 * 086 * Configure the maximum number of iterations. 087 * @param newMaxIter
		 * maximum number of iterations 088 * @return a new instance. 089
		 */
		public SimpleCurveFitter2 withMaxIterations(int newMaxIter) {
			return new SimpleCurveFitter2(function, initialGuess, newMaxIter);
		}

		/** {@inheritDoc} */
		@Override
		public LeastSquaresProblem getProblem(Collection<WeightedObservedPoint> observations) {
			// Prepare least-squares problem.
			final int len = observations.size();
			final double[] target = new double[len];
			final double[] weights = new double[len];

			int count = 0;
			for (WeightedObservedPoint obs : observations) {
				target[count] = obs.getY();
				weights[count] = obs.getWeight();
				++count;
			}

			final AbstractCurveFitter.TheoreticalValuesFunction model = new AbstractCurveFitter.TheoreticalValuesFunction(
					function, observations);

			// Create an optimizer for fitting the curve to the observed points.
			return new LeastSquaresBuilder().maxEvaluations(Integer.MAX_VALUE).maxIterations(maxIter)
					.start(initialGuess).target(target).weight(new DiagonalMatrix(weights))
					.model(model.getModelFunction(), model.getModelFunctionJacobian()).build();
		}
	}
