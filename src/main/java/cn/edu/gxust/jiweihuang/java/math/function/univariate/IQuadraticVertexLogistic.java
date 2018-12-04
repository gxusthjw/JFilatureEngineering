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

package cn.edu.gxust.jiweihuang.java.math.function.univariate;

import cn.edu.gxust.jiweihuang.java.math.function.IUnivariateDerivativeFunction;
import cn.edu.gxust.jiweihuang.java.math.function.IUnivariateDifferentiableFunction;
import org.hipparchus.analysis.ParametricUnivariateFunction;
import org.hipparchus.analysis.differentiation.DSCompiler;
import org.hipparchus.analysis.differentiation.DerivativeStructure;
import org.hipparchus.exception.MathIllegalArgumentException;
import org.hipparchus.exception.NullArgumentException;
import org.hipparchus.util.MathUtils;

/**
 * The interface {@code IQuadraticVertexLogistic} is used for representing the composite function of logistic and quadratic function.
 */
public interface IQuadraticVertexLogistic extends IUnivariateDifferentiableFunction,
        IUnivariateDerivativeFunction {

    /**
     * @return the object of QuadraticVertex
     */
    IQuadraticVertex getQuadraticVertex();

    /**
     * @return the object of Logistic
     */
    ILogistic getLogistic();

    /**
     * @return the parameter {@code d} of QuadraticVertexLogistic function.
     */
    double getQuadraticVertexLogisticD();

    /**
     * @return the parameter {@code a} of quadratic function.
     * @since 1.0.0_build-20181120
     */
    default double getQuadraticVertexA() {
        return getQuadraticVertex().getQuadraticVertexA();
    }

    /**
     * @return the parameter {@code b} of quadratic function.
     * @since 1.0.0_build-20181120
     */
    default double getQuadraticVertexB() {
        return getQuadraticVertex().getQuadraticVertexB();
    }

    /**
     * @return the parameter {@code c} of quadratic function.
     * @since 1.0.0_build-20181120
     */
    default double getQuadraticVertexC() {
        return getQuadraticVertex().getQuadraticVertexC();
    }

    /**
     * @return the parameter {@code m} of Logistic function.
     * @since 1.0.0_build-20181120
     */
    default double getLogisticM() {
        return getLogistic().getLogisticM();
    }

    /**
     * @return the parameter {@code k} of Logistic function.
     * @since 1.0.0_build-20181120
     */
    default double getLogisticK() {
        return getLogistic().getLogisticK();
    }

    /**
     * @return the parameter {@code x0} of Logistic function.
     * @since 1.0.0_build-20181120
     */
    default double getLogisticX0() {
        return getLogistic().getLogisticX0();
    }

    //The default implementation of method in {IUnivariateDerivativeFunction} interface.
    //================================

    /**
     * The method {@code derivative(double x)} is used to get the integral value of integral function at independent variable {@code x}.
     *
     * @param x independent variable
     * @return the integral value of integral function at independent variable {@code x}.
     * @since 1.0.0_build-20181120
     */
    @Override
    default double derivative(double x) {
        return getLogistic().value(x) * getQuadraticVertex().derivative(x) + getLogistic().derivative(x) * getQuadraticVertex().value(x);
    }

    /**
     * <p>The method {@code iformula()}is used to get the the analytic expression of integral function.</p>
     *
     * @return the the analytic expression of integral function.
     * @since 1.0.0_build-20181120
     */
    @Override
    default String dformula() {
        return (getLogistic().dformula() + "*" + getQuadraticVertex().formula()) + "+" + (getQuadraticVertex().dformula() + "*" + getLogistic().formula());
    }

    //The default implementation of method in {UnivariateDifferentiableFunction} interface.
    //================================

    /**
     * The method {} is used to get the calculated value of function at independent variable {{@code x}}.
     *
     * @param x independent variable
     * @return the calculated value of function at independent variable {{@code x}}.
     * @since 1.0.0_build-20181120
     */
    @Override
    default double value(double x) {
        return (getLogisticM() * (getQuadraticVertexC() + getQuadraticVertexA() * Math.pow(-getQuadraticVertexB() + x, 2))) / (1 + Math.exp(-getLogisticK() * (-getLogisticX0() + x))) + getQuadraticVertexLogisticD();
    }

    /**
     * @param t {@code DerivativeStructure}
     * @return {@code DerivativeStructure} for derivative value with any order.
     * @see DSCompiler
     * @since 1.0.0_build-20181120
     */
    @Override
    default DerivativeStructure value(DerivativeStructure t) {
        return getLogistic().value(t).multiply(getQuadraticVertex().value(t)).add(getQuadraticVertexLogisticD());
    }

    //接口IUnivariateFunction中方法的缺省实现。
    //================================

    /**
     * @return the analytic expression of univariate function.
     * @since 1.0.0_build-20181120
     */
    @Override
    default String formula() {
        return String.format("(%.5f*(%.5f + %.5f*(-%.5f + x)^2))/(1 + E^(-%.5f*(-%.5f + x)))+%.5f", getLogisticM(),
                getQuadraticVertexC(), getQuadraticVertexA(), getQuadraticVertexB(),
                getLogisticK(), getLogisticX0(), getQuadraticVertexLogisticD());
    }

    /**
     * The inner class {@code Parametric} is used for representing the parameterized form of the composite function of logistic and quadratic function..
     * it make the function to take advantage of {@code org.hipparchus.fitting.AbstractCurveFitter} for curve fitting.
     *
     * @since 1.0.0_build-20181120
     */
    class Parametric implements ParametricUnivariateFunction {

        @Override
        public double value(double x, double... parameters) {
            validateParameters(parameters);
            double a = parameters[0];
            double b = parameters[1];
            double c = parameters[2];
            double m = parameters[3];
            double k = parameters[4];
            double x0 = parameters[5];
            double d = parameters[6];
            return IQuadraticVertexLogistic.value(x, a, b, c, m, k, x0, d);
        }

        @Override
        public double[] gradient(double x, double... parameters) {
            validateParameters(parameters);
            double a = parameters[0];
            double b = parameters[1];
            double c = parameters[2];
            double m = parameters[3];
            double k = parameters[4];
            double x0 = parameters[5];
            double d = parameters[6];
            double[] make_array = new double[7];
            make_array[0] = m * Math.pow(x - b, 2) * ILogistic.logisticDenominatorItem(x, k, x0);
            make_array[1] = 2 * a * m * (x - b) * ILogistic.logisticDenominatorItem(x, k, x0);
            make_array[2] = m * ILogistic.logisticDenominatorItem(x, k, x0);
            make_array[3] = IQuadraticVertex.value(x, a, b, c) * ILogistic.logisticDenominatorItem(x, k, x0);
            make_array[4] = ILogistic.logisticExpItem(x, k, x0) * m * (x - x0) * IQuadraticVertex.value(x, a, b, c) * Math.pow(ILogistic.logisticDenominatorItem(x, k, x0), 2);
            make_array[5] = ILogistic.logisticExpItem(x, k, x0) * m * k * IQuadraticVertex.value(x, a, b, c) * Math.pow(ILogistic.logisticDenominatorItem(x, k, x0), 2);
            make_array[6] = 1;
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
            MathUtils.checkDimension(param.length, 7);
        }
    }

    static double value(double x, double a, double b, double c,
                        double m, double k, double x0, double d) {
        return IQuadraticVertex.value(x, a, b, c) * ILogistic.value(x, m, k, x0) + d;
    }
}
