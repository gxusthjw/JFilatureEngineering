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
import cn.edu.gxust.jiweihuang.java.math.function.IUnivariateIntegralFunction;
import org.hipparchus.analysis.ParametricUnivariateFunction;
import org.hipparchus.analysis.differentiation.DSCompiler;
import org.hipparchus.analysis.differentiation.DerivativeStructure;
import org.hipparchus.exception.MathIllegalArgumentException;
import org.hipparchus.exception.NullArgumentException;
import org.hipparchus.util.MathUtils;

/**
 * <p> The interface {@code ILogistic} is used for
 * representing the Logistic Function.</p>
 * <p>The logistic function also call sigmoid function</p>
 * <p> formula:l(x)=m/(1+exp(-k(x-x0)))</p>
 * <p> where, m —— the max value of Logistic function, {@code logisticM}.</p>
 * <p>        k —— the steepness of Logistic function, {@code logisticK}.</p>
 * <p>       x0 —— the x-value of the midpoint value of Logistic function,{@code logisticX0}. </p>
 * <p> Reference:https://en.wikipedia.org/wiki/Logistic_function</p>
 * <p> Create Date:2018-11-20 </p>
 *
 * @author JiweiHuang
 * @version 1.0.0_build-20181120
 * @see IUnivariateDifferentiableFunction
 * @see IUnivariateDerivativeFunction
 * @see IUnivariateIntegralFunction
 */
public interface ILogistic extends IUnivariateDifferentiableFunction,
        IUnivariateDerivativeFunction, IUnivariateIntegralFunction {
    /**
     * <p> Create Date:2018-11-20 </p>
     *
     * @return the parameter {@code m} of Logistic function.
     * @since 1.0.0_build-20181120
     */
    double getLogisticM();

    /**
     * <p> Create Date:2018-11-20 </p>
     *
     * @return the parameter {@code k} of Logistic function.
     * @since 1.0.0_build-20181120
     */
    double getLogisticK();

    /**
     * <p> Create Date:2018-11-20 </p>
     *
     * @return the parameter {@code x0} of Logistic function.
     * @since 1.0.0_build-20181020
     */
    double getLogisticX0();

    /**
     * The mathod {expItem(double x)} is used to
     * get the calculated value of {@code exp(-k(x-x0))}.
     * <p> Create Date:2018-11-20 </p>
     *
     * @param x independent variable
     * @return the calculated value of {@code exp(-k(x-x0))}.
     * @since 1.0.0_build-20181120
     */
    default double expItem(double x) {
        return Math.exp(-getLogisticK() * (x - getLogisticX0()));
    }

    //The default implementation of method in {IUnivariateIntegralFunction} interface.
    //================================

    /**
     * The method {@code integrate(double x)} is used to
     * get the integral value of integral function
     * at independent variable {@code x}.
     * <p> Create Date:2018-11-20 </p>
     *
     * @param x independent variable
     * @return the integral value of integral function at independent variable {@code x}.
     * @since 1.0.0_build-20181120
     */
    @Override
    default double integrate(double x) {
        return getLogisticM() * (x + (Math.log(1 + expItem(x)) / getLogisticK()));
    }

    /**
     * <p>The method {@code iformula()}is used to get the the analytic expression of integral function.</p>
     * <p> Create Date:2018-11-20 </p>
     *
     * @return the the analytic expression of integral function.
     * @since 1.0.0_build-20181120
     */
    @Override
    default String iformula() {
        return String.format("il(x)=%.5f*(x+log(1+exp(-%.5f*(x-%.5f)))/%.5f)", getLogisticM(),
                getLogisticK(), getLogisticX0(), getLogisticK());
    }

    //The default implementation of method in {IUnivariateDerivativeFunction} interface.
    //================================

    /**
     * <p>The method {@code derivative(double x)} is used to
     * get the derivative value of derived function
     * at independent Variable {@code x}.
     * it should be equal to the result of {@code differential(double x)} which in {@code IUnivariateDifferentiableFunction} interface.</p>
     * <p> Create Date:2018-11-20 </p>
     *
     * @param x independent variable
     * @return the derivative value of derived function at independent Variable {@code x}.
     * @since 1.0.0_build-20181120
     */
    @Override
    default double derivative(double x) {
        return (getLogisticM() * getLogisticK() * expItem(x)) / Math.pow(1 + expItem(x), 2);
    }

    /**
     * <p>The method {@code dformula()} is used to get the analytic expression of univariate derived function.</p>
     * <p> Create Date:2018-11-20 </p>
     *
     * @return the analytic expression of univariate derived function.
     * @since 1.0.0_build-20181120
     */
    @Override
    default String dformula() {
        return String.format("dl(x)=(%.5f*%.5f*exp(-%.5f*(x-%.5f)))/(1+exp(-%.5f*(x-%.5f)))^2", getLogisticM(),
                getLogisticK(), getLogisticK(), getLogisticX0(), getLogisticK(), getLogisticX0());
    }

    //The default implementation of method in {UnivariateDifferentiableFunction} interface.
    //================================

    /**
     * The method {@code value(double x)} is used to get the calculated value of function at independent variable {{@code x}}.
     * <p> Create Date:2018-11-20 </p>
     *
     * @param x independent variable
     * @return the calculated value of function at independent variable {{@code x}}.
     * @since 1.0.0_build-20181120
     */
    @Override
    default double value(double x) {
        return getLogisticM() / (1 + expItem(x));
    }

    /**
     * <p> Create Date:2018-11-20 </p>
     *
     * @param t {@code DerivativeStructure}
     * @return {@code DerivativeStructure} for derivative value with any order.
     * @see DSCompiler
     * @since 1.0.0_build-20181120
     */
    @Override
    default DerivativeStructure value(DerivativeStructure t) {
        return t.subtract(getLogisticX0()).multiply(getLogisticK()).negate().exp().add(1).pow(-1).multiply(getLogisticM());
    }

    //The default implementation of method in {IUnivariateFunction} interface.
    //================================

    /**
     * <p> Create Date:2018-11-20 </p>
     *
     * @return the analytic expression of univariate function.
     * @since 1.0.0_build-20181120
     */
    @Override
    default String formula() {
        return String.format("l(x)=%.5f/(1+exp(-%.5f*(x-%.5f)))", getLogisticM(),
                getLogisticK(), getLogisticX0());
    }

    /**
     * The inner class {@code Parametric} is used for representing the parameterized form of logistic function.
     * it make the function to take advantage of {@code org.hipparchus.fitting.AbstractCurveFitter} for curve fitting.
     * <p> Create Date:2018-11-20 </p>
     *
     * @since 1.0.0_build-20181120
     */
    class Parametric implements ParametricUnivariateFunction {

        @Override
        public double value(double x, double... parameters) {
            validateParameters(parameters);
            double m = parameters[0];
            double k = parameters[1];
            double x0 = parameters[2];
            return ILogistic.value(x, m, k, x0);
        }

        @Override
        public double[] gradient(double x, double... parameters) {
            validateParameters(parameters);
            double[] make_array = new double[3];
            double m = parameters[0];
            double k = parameters[1];
            double x0 = parameters[2];
            make_array[0] = ILogistic.logisticDenominatorItem(x, k, x0);
            make_array[1] = (ILogistic.logisticExpItem(x, k, x0) * m * (x - x0)) * Math.pow(ILogistic.logisticDenominatorItem(x, k, x0), 2);
            make_array[2] = -k * m * ILogistic.logisticExpItem(x, k, x0) * Math.pow(ILogistic.logisticDenominatorItem(x, k, x0), 2);
            return make_array;
        }

        /**
         * Validates parameters to ensure they are appropriate for the evaluation of
         * the {@link #value(double, double[])} and {@link #gradient(double, double[])}
         * methods.
         * <p> Create Date:2018-11-20 </p>
         *
         * @param param Values of m, k and x0.
         * @throws NullArgumentException        if {@code param} is {@code null}.
         * @throws MathIllegalArgumentException if the size of {@code param} is
         *                                      not 3.
         * @since 1.0.0_build-20181120
         */
        private void validateParameters(double[] param)
                throws MathIllegalArgumentException, NullArgumentException {
            MathUtils.checkNotNull(param);
            MathUtils.checkDimension(param.length, 3);
            if (param[0] == 0) {
                throw new IllegalArgumentException("Expected {@code param[0] != 0},but got {@code param[0] == 0}.");
            }
        }
    }

    static double logisticExpItem(double x, double k, double x0) {
        return Math.exp(-k * (x - x0));
    }

    static double logisticDenominatorItem(double x, double k, double x0) {
        return 1 / (1 + logisticExpItem(x, k, x0));
    }

    static double value(double x, double m, double k, double x0) {
        return m * logisticDenominatorItem(x, k, x0);
    }

}
