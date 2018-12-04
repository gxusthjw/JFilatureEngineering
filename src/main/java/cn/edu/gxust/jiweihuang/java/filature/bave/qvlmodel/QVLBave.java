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

package cn.edu.gxust.jiweihuang.java.filature.bave.qvlmodel;

import cn.edu.gxust.jiweihuang.java.filature.bave.IBave;

import cn.edu.gxust.jiweihuang.java.math.function.univariate.*;
import org.hipparchus.analysis.ParametricUnivariateFunction;
import org.hipparchus.exception.MathIllegalArgumentException;
import org.hipparchus.exception.NullArgumentException;
import org.hipparchus.util.MathUtils;

public class QVLBave implements IBave, IQuadraticVertexLogistic {

    private static final long serialVersionUID = 9121969971048988529L;

    private static long ID_COUNTER = 0;
    private final long baveID;
    private final double baveLength;
    private final double initialSize;
    private final double initialTerminalSizeRatio;
    private final double maxSizePosBaveLengthRatio;

    public QVLBave(double baveLength, double initialSize,
                   double initialTerminalSizeRatio,
                   double maxSizePosBaveLengthRatio) {
        this.baveLength = baveLength;
        this.initialSize = initialSize;
        this.initialTerminalSizeRatio = initialTerminalSizeRatio;
        this.maxSizePosBaveLengthRatio = maxSizePosBaveLengthRatio;
        this.baveID = ID_COUNTER++;
    }

    @Override
    public double getBaveLength() {
        return baveLength;
    }

    @Override
    public double getBaveSize(double pos) {
        return value(pos);
    }

    public double getInitialSize() {
        return initialSize;
    }

    public double getMaxSizePos() {
        return getBaveLength() * getMaxSizePosBaveLengthRatio();
    }

    public double getInitialTerminalSizeRatio() {
        return initialTerminalSizeRatio;
    }

    public double getMaxSizePosBaveLengthRatio() {
        return maxSizePosBaveLengthRatio;
    }

    public long getBaveID() {
        return baveID;
    }

    @Override
    public IQuadraticVertex getQuadraticVertex() {
        return new QuadraticVertex(getQuadraticVertexA(), getQuadraticVertexB(), getQuadraticVertexC());
    }

    @Override
    public ILogistic getLogistic() {
        return new Logistic(getLogisticM(), getLogisticK(), getLogisticX0());
    }

    @Override
    public double getQuadraticVertexLogisticD() {
        return getInitialTerminalSizeRatio() * getInitialSize();
    }

    /**
     * @return 顶点式二次函数的参数a。
     */
    @Override
    public double getQuadraticVertexA() {
        return (getInitialSize() * (1 + Math.exp(getLogisticK() * getMaxSizePos())) * (1 - getInitialTerminalSizeRatio())) / Math.pow(getBaveLength(), 2.0);
    }

    /**
     * @return 顶点式二次函数的参数b。
     */
    @Override
    public double getQuadraticVertexB() {
        return getBaveLength();
    }

    /**
     * @return 顶点式二次函数的参数c。
     */
    @Override
    public double getQuadraticVertexC() {
        return .0;
    }

    /**
     * @return Logistic函数的参数a (与参考中的L对应)
     */
    @Override
    public double getLogisticM() {
        return 1.0;
    }

    /**
     * @return Logistic函数的参数b (与参考中的k对应)
     */
    @Override
    public double getLogisticK() {
        return 4.0 / (getBaveLength() * (1 - getMaxSizePosBaveLengthRatio()));
    }

    /**
     * @return Logistic函数的参数c (与参考中的x0对应)
     */
    @Override
    public double getLogisticX0() {
        return getMaxSizePos();
    }

    /**
     * 处于曲线拟合的目的。
     */
    public static class Parametric implements ParametricUnivariateFunction {

        @Override
        public double value(double x, double... parameters) {
            validateParameters(parameters);
            double L = parameters[0];//baveLength
            double h = parameters[1];//initialSize,headSize
            double alpha = parameters[2];//initialTerminalSizeRatio
            double beta = parameters[3]; //maxSizePosBaveLengthRatio
            double up = h * (1 + Math.exp(4 * (beta / (1 - beta)))) * (1 - alpha) * Math.pow(x - L, 2);
            double down = Math.pow(L, 2) * (1 + Math.exp(-((4 * (x - beta * L)) / (L * (1 - beta)))));
            return up / down + alpha * h;
        }

        @Override
        public double[] gradient(double x, double... parameters) {
            validateParameters(parameters);
            double L = parameters[0];//baveLength
            double h = parameters[1];//initialSize,headSize
            double alpha = parameters[2];//initialTerminalSizeRatio
            double beta = parameters[3]; //maxSizePosBaveLengthRatio
            double[] make_array = new double[4];
            make_array[0] = (2 * Math.exp((4 * (x + 2 * L * beta)) / (L - L * beta)) * (1 + Math.exp(4 * beta / (-1 + beta))) * h * x * (-L + x) * (-1 + alpha) * (-2 * x + L * (1 + Math.exp((4 * (x - L * beta)) / (L - L * beta)) * (-1 + beta) + beta))) / (Math.pow(((Math.exp(-4 * beta / (-1 + beta)) + Math.exp(4 * x / (L - L * beta)))), 2) * Math.pow(L, 4) * (-1 + beta));
            make_array[1] = ((1 + Math.exp(4 * beta / (1 - beta))) * (Math.pow(-L + x, 2)) * (1 - alpha)) / ((1 + Math.exp(-(4 * (x - L * beta)) / (L * (1 - beta)))) * Math.pow(L, 2)) + alpha;
            make_array[2] = h - ((1 + Math.exp(4 * beta / (1 - beta))) * h * Math.pow((-L + x), 2)) / ((1 + Math.exp(-(4 * (x - L * beta)) / (L * (1 - beta)))) * Math.pow(L, 2));
            make_array[3] = -(4 * Math.exp((4 * x + 4 * L * beta) / (L - L * beta)) * h * Math.pow(L - x, 2) *
                    ((-1 + Math.exp(4 * x / (L - L * beta))) * L + x + Math.exp(-4 *
                            beta / (-1 + beta)) * x) * (-1 + alpha)) / (Math.pow(
                    (Math.exp(-4 * beta / (-1 + beta)) + Math.exp(4 * x / (L - L * beta))), 2) * Math.pow(L, 3) * Math.pow(-1 + beta, 2));
            return make_array;
        }

        /**
         * Validates parameters to ensure they are appropriate for the evaluation of
         * the {@link #value(double, double[])} and {@link #gradient(double, double[])}
         * methods.
         *
         * @param param Values of m, k and x0.
         * @throws NullArgumentException        if {@code param} is {@code null}.
         * @throws MathIllegalArgumentException if the size of {@code param} is
         *                                      not 3.
         */
        private void validateParameters(double[] param)
                throws MathIllegalArgumentException, NullArgumentException {
            MathUtils.checkNotNull(param);
            MathUtils.checkDimension(param.length, 4);
        }
    }

    @Override
    public String toString() {
        return "QVLFunctionBave{" +
                "baveLength=" + baveLength +
                ", initialSize=" + initialSize +
                ", initialTerminalSizeRatio=" + initialTerminalSizeRatio +
                ", maxSizePosBaveLengthRatio=" + maxSizePosBaveLengthRatio +
                '}';
    }
}
