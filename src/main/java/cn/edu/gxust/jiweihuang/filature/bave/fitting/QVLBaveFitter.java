/*
 * Copyright (c) 2018-2019, Jiwei Huang. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.edu.gxust.jiweihuang.filature.bave.fitting;


import cn.edu.gxust.jiweihuang.filature.bave.qvlmodel.QVLBave;
import org.hipparchus.fitting.AbstractCurveFitter;
import org.hipparchus.fitting.WeightedObservedPoint;
import org.hipparchus.linear.DiagonalMatrix;
import org.hipparchus.optim.nonlinear.vector.leastsquares.LeastSquaresBuilder;
import org.hipparchus.optim.nonlinear.vector.leastsquares.LeastSquaresProblem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class QVLBaveFitter extends AbstractCurveFitter {
    private static final QVLBave.Parametric FUNCTION = new QVLBave.Parametric();

    private final double[] initialGuess;

    private final int maxIter;

    private QVLBaveFitter(double[] initialGuess,
                          int maxIter) {
        this.initialGuess = initialGuess;
        this.maxIter = maxIter;
    }

    public static QVLBaveFitter create() {
        return new QVLBaveFitter(null, Integer.MAX_VALUE);
    }

    public QVLBaveFitter withStartPoint(double[] newStart) {
        return new QVLBaveFitter(newStart.clone(), maxIter);
    }

    public QVLBaveFitter withMaxIterations(int newMaxIter) {
        return new QVLBaveFitter(initialGuess, newMaxIter);
    }

    @Override
    protected LeastSquaresProblem getProblem(Collection<WeightedObservedPoint> observations) {
        // Prepare least-squares problem.
        final int len = observations.size();
        final double[] target = new double[len];
        final double[] weights = new double[len];
        int i = 0;
        for (WeightedObservedPoint obs : observations) {
            target[i] = obs.getY();
            weights[i] = obs.getWeight();
            ++i;
        }
        final TheoreticalValuesFunction model
                = new TheoreticalValuesFunction(FUNCTION,
                observations);

        final double[] startPoint = initialGuess != null ?
                initialGuess :
                // Compute estimation.
                new QVLBaveFitter.ParameterGuesser(observations).guess();

        // Return a new optimizer set up to fit a Gaussian curve to the
        // observed points.
        return new LeastSquaresBuilder().
                maxEvaluations(Integer.MAX_VALUE).
                maxIterations(maxIter).
                start(startPoint).
                target(target).
                weight(new DiagonalMatrix(weights)).
                model(model.getModelFunction(), model.getModelFunctionJacobian()).
                build();
    }

    public QVLBave getQVLFunctionBave(Collection<WeightedObservedPoint> observations) {
        double[] p = this.fit(observations);
        return new QVLBave(p[0], p[1], p[2], p[3]);
    }

    public static class ParameterGuesser {

        private final double baveLength;
        private final double initialSize;
        private final double initialTerminalSizeRatio;
        private final double maxSizePosBaveLengthRatio;

        public ParameterGuesser(Collection<WeightedObservedPoint> observations) {
            List<WeightedObservedPoint> wopSorted = sortObservations(observations);
            this.baveLength = guessBaveLength(wopSorted);
            this.initialSize = guessInitialSize(wopSorted);
            double terminalSize = guessTerminalSize(wopSorted);
            double maxSizePos = guessMaxSizePos(wopSorted);
            this.initialTerminalSizeRatio = terminalSize / initialSize;
            this.maxSizePosBaveLengthRatio = maxSizePos / baveLength;
        }

        public double[] guess() {
            return new double[]{baveLength, initialSize, initialTerminalSizeRatio, maxSizePosBaveLengthRatio};
        }

        /**
         * Sort the observations with respect to the abscissa.
         *
         * @param unsorted Input observations.
         * @return the input observations, sorted.
         */
        public static List<WeightedObservedPoint> sortObservations(Collection<WeightedObservedPoint> unsorted) {
            final List<WeightedObservedPoint> observations = new ArrayList<WeightedObservedPoint>(unsorted);

            // Since the samples are almost always already sorted, this
            // method is implemented as an insertion sort that reorders the
            // elements in place. Insertion sort is very efficient in this case.
            WeightedObservedPoint curr = observations.get(0);
            final int len = observations.size();
            for (int j = 1; j < len; j++) {
                WeightedObservedPoint prec = curr;
                curr = observations.get(j);
                if (curr.getX() < prec.getX()) {
                    // the current element should be inserted closer to the beginning
                    int i = j - 1;
                    WeightedObservedPoint mI = observations.get(i);
                    while ((i >= 0) && (curr.getX() < mI.getX())) {
                        observations.set(i + 1, mI);
                        if (i-- != 0) {
                            mI = observations.get(i);
                        }
                    }
                    observations.set(i + 1, curr);
                    curr = observations.get(j);
                }
            }

            return observations;
        }

        public static double guessBaveLength(List<WeightedObservedPoint> observations) {
            int len = observations.size();
            double length = observations.get(len - 1).getX() + (observations.get(len - 2).getX() - observations.get(len - 1).getX()) / 2.0;
            return length;
        }

        public static double guessInitialSize(List<WeightedObservedPoint> observations) {
            WeightedObservedPoint wop1 = observations.get(0);
            WeightedObservedPoint wop2 = observations.get(1);
            int len = observations.size();
            double length = observations.get(0).getX() - (observations.get(1).getX() - observations.get(0).getX()) / 2.0;
            double alpha = Math.abs(wop2.getY() - wop1.getY()) / Math.abs(wop2.getX() - wop1.getX());
            return wop1.getY() - length * alpha;
        }

        public static double guessTerminalSize(List<WeightedObservedPoint> observations) {
            int len = observations.size();
            WeightedObservedPoint wop1 = observations.get(len - 2);
            WeightedObservedPoint wop2 = observations.get(len - 1);
            double length = observations.get(len - 1).getX() + (observations.get(len - 2).getX() - observations.get(len - 1).getX()) * 0.5;
            double alpha = Math.abs(wop1.getY() - wop2.getY()) / Math.abs(wop1.getX() - wop2.getX());
            return wop2.getY() - length * alpha;
        }

        public static double guessMaxSizePos(List<WeightedObservedPoint> observations) {
            double max = observations.get(0).getY();
            double max_pos = observations.get(0).getX();
            for (WeightedObservedPoint wop : observations) {
                if (wop.getY() > max) {
                    max = wop.getY();
                    max_pos = wop.getX();
                }
            }
            return max_pos;
        }
    }
}
