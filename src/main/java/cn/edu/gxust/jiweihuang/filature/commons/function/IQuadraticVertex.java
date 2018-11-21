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

package cn.edu.gxust.jiweihuang.filature.commons.function;

import org.hipparchus.analysis.ParametricUnivariateFunction;
import org.hipparchus.analysis.differentiation.DSCompiler;
import org.hipparchus.analysis.differentiation.DerivativeStructure;
import org.hipparchus.exception.MathIllegalArgumentException;
import org.hipparchus.exception.NullArgumentException;
import org.hipparchus.util.MathUtils;

/**
 * The interface {@code IQuadraticVertex} is used for representing
 * the vertex form of quadratic function.
 * formula：q(x)=a*(x-b)^2+c
 * <p> Create Date:2018-11-20 </p>
 *
 * @author JiweiHuang
 * @version 1.0.0_build-20181120
 * @see IUnivariateDifferentiableFunction
 * @see IUnivariateDerivativeFunction
 * @see IUnivariateIntegralFunction
 */
public interface IQuadraticVertex extends IQuadratic {
    /**
     * The parameter {@code a} of quadratic function
     * which must be not equals to zero.
     * <p> Create Date:2018-11-20 </p>
     *
     * @return the parameter {@code a} of quadratic function.
     * @author JiweiHuang
     * @since 1.0.0_build-20181120
     */
    double getQuadraticVertexA();

    /**
     * The parameter {@code b} of quadratic function.
     * <p> Create Date:2018-11-20 </p>
     *
     * @return the parameter {@code b} of quadratic function.
     * @author JiweiHuang
     * @since 1.0.0_build-20181120
     */
    double getQuadraticVertexB();

    /**
     * The parameter {@code c} of quadratic function.
     * <p> Create Date:2018-11-20 </p>
     *
     * @return the parameter {@code c} of quadratic function.
     * @author JiweiHuang
     * @since 1.0.0_build-20181120
     */
    double getQuadraticVertexC();

    /**
     * <p> The method {} is used to check the parameter {@code a},
     * which must be is not equals to zero,otherwise will be throw a exception
     * {@code RuntimeException}. </p>
     *
     * @author JiweiHuang
     * @since 1.0.0_build-20181120
     */
    default void checkA() throws RuntimeException {
        if (getQuadraticVertexA() == 0) {
            throw new RuntimeException(String.format(
                    "Expected {getQuadraticVertexA() != 0},but got {getQuadraticVertexA() == %.1f}", getQuadraticVertexA()));
        }
    }
    //The default implementation of method in {IQuadratic} interface.
    //================================

    /**
     * The method {vertex()} is used to get the vertex of quadratic function.
     * <p> Create Date:2018-11-20 </p>
     *
     * @return the vertex of quadratic function
     * @author JiweiHuang
     * @since 1.0.0_build-20181120
     */
    @Override
    default double[] vertex() {
        return new double[]{getQuadraticVertexB(), getQuadraticVertexC()};
    }

    /**
     * The method is used to get
     * the intersection points of the quadratic function with the X-axis.
     * <p> Create Date:2018-11-20 </p>
     *
     * @return the intersection points of the quadratic function with the X-axis.
     * @author JiweiHuang
     * @since 1.0.0_build-20181120
     */
    @Override
    default double[] xIntersection() {
        checkA();
        double tem = -getQuadraticVertexC() / getQuadraticVertexA();
        if (tem > 0) {
            return new double[]{getQuadraticVertexB() - Math.sqrt(tem), getQuadraticVertexB() + Math.sqrt(tem)};
        } else if (tem == 0) {
            return new double[]{getQuadraticVertexB()};
        } else {
            return new double[]{};
        }
    }

    /**
     * The method {@code isInvert()} is used to estimate whether is invert of quadratic function.
     * <p> Create Date:2018-11-20 </p>
     *
     * @return whether is invert of quadratic function.
     * @author JiweiHuang
     * @since 1.0.0_build-20181120
     */
    @Override
    default boolean isInvert() {
        checkA();
        return getQuadraticVertexA() > 0 ? false : true;
    }

    //The default implementation of method in {IUnivariateIntegralFunction} interface.
    //================================

    /**
     * The method {@code integrate(double x)} is used to get the integral value of integral function at independent variable {@code x}.
     * <p> Create Date:2018-11-20 </p>
     *
     * @param x independent variable
     * @return the integral value of integral function
     * at independent variable {@code x}.
     * @author JiweiHuang
     * @since 1.0.0_build-20181120
     */
    @Override
    default double integrate(double x) {
        return getQuadraticVertexA() * Math.pow(getQuadraticVertexB(), 2.0) * x +
                getQuadraticVertexC() * x - getQuadraticVertexA() * getQuadraticVertexB() * Math.pow(x, 2.0) +
                getQuadraticVertexA() * Math.pow(x, 3) / 3.0 + getIntegralConstants();
    }

    /**
     * <p>The method {@code iformula()}is used to get
     * the analytic expression of integral function.</p>
     * <p> Create Date:2018-11-20 </p>
     *
     * @return the the analytic expression of integral function.
     * @author JiweiHuang
     * @since 1.0.0_build-20181120
     */
    @Override
    default String iformula() {
        return String.format("iq(x)=%f*%f^2*x + %f*x - %f*%f*x^2 + (%f*x^3)/3",
                getQuadraticVertexA(), getQuadraticVertexB(), getQuadraticVertexC(),
                getQuadraticVertexA(), getQuadraticVertexB(), getQuadraticVertexA());
    }

    //The default implementation of method in {IUnivariateDerivativeFunction} interface.
    //================================

    /**
     * <p>The method {@code derivative(double x)} is used to
     * get the derivative value of derived function
     * at independent Variable {@code x}.
     * it should be equal to the result of
     * {@code differential(double x)} which in
     * {@code IUnivariateDifferentiableFunction} interface.</p>
     * <p> Create Date:2018-11-20 </p>
     *
     * @param x independent variable
     * @return the derivative value of derived function at independent Variable {@code x}.
     * @author JiweiHuang
     * @since 1.0.0_build-20181120
     */
    @Override
    default double derivative(double x) {
        return 2 * getQuadraticVertexA() * (x - getQuadraticVertexB());
    }

    /**
     * <p>The method {@code dformula()} is used to get the analytic expression of univariate derived function.</p>
     * <p> Create Date:2018-11-20 </p>
     *
     * @return the analytic expression of univariate derived function.
     * @author JiweiHuang
     * @since 1.0.0_build-20181120
     */
    @Override
    default String dformula() {
        return String.format("dq(x)=2*%f*(x-%f)", getQuadraticVertexA(),
                getQuadraticVertexB());
    }


    //The default implementation of method in {UnivariateDifferentiableFunction} interface.
    //================================

    /**
     * The method {@code value(double x)} is used to
     * get the calculated value of function at independent variable {{@code x}}.
     * <p> Create Date:2018-11-20 </p>
     *
     * @param x independent variable
     * @return the calculated value of function at independent variable {{@code x}}.
     * @author JiweiHuang
     * @since 1.0.0_build-20181120
     */
    @Override
    default double value(double x) {
        return getQuadraticVertexA() * Math.pow(x - getQuadraticVertexB(), 2.0) + getQuadraticVertexC();
    }

    /**
     * <p> Create Date:2018-11-20 </p>
     *
     * @param t {@code DerivativeStructure}
     * @return {@code DerivativeStructure} for derivative value with any order.
     * @author JiweiHuang
     * @see DSCompiler
     * @since 1.0.0_build-20181120
     */
    @Override
    default DerivativeStructure value(DerivativeStructure t) {
        return t.subtract(getQuadraticVertexB()).pow(2).multiply(getQuadraticVertexA()).add(getQuadraticVertexC());
    }

    //The default implementation of method in {IUnivariateFunction} interface.
    //================================

    /**
     * <p> Create Date:2018-11-20 </p>
     *
     * @return the String form of quadratic function.
     * @author JiweiHuang
     */
    @Override
    default String formula() {
        return String.format("q(x)=%f*(x-%f)^2+%f", getQuadraticVertexA(),
                getQuadraticVertexB(), getQuadraticVertexC());
    }

    /**
     * The inner class {@code Parametric} is used for representing
     * the parameterized form of vertex quadratic function.
     * it make the function to take advantage
     * of {@code org.hipparchus.fitting.AbstractCurveFitter} for curve fitting.
     * <p> Create Date:2018-11-20 </p>
     *
     * @since 1.0.0_build-20181120
     */
    class Parametric implements ParametricUnivariateFunction {

        @Override
        public double value(double x, double... parameters) {
            validateParameters(parameters);
            return IQuadraticVertex.value(x, parameters[0], parameters[1], parameters[2]);
        }

        @Override
        public double[] gradient(double x, double... parameters) {
            validateParameters(parameters);
            double[] make_array = new double[3];
            double a = parameters[0];
            double b = parameters[1];
            double c = parameters[2];
            make_array[0] = Math.pow(x - b, 2);
            make_array[1] = -2 * a * (x - b);
            make_array[2] = 1;
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
            MathUtils.checkDimension(param.length, 3);
            checkA(param[0]);
        }

        private void checkA(double a) throws RuntimeException {
            if (a == 0) {
                throw new RuntimeException(String.format(
                        "Expected {param[0] != 0},but got {param[0] == 0}"));
            }
        }
    }

    static double value(double x, double a, double b, double c) {
        return a * Math.pow(x - b, 2.0) + c;
    }

    static double integrate(double x, double a, double b, double c) {
        return a * Math.pow(b, 2.0) * x +
                c * x - a * b * Math.pow(x, 2.0) +
                a * Math.pow(x, 3.0) / 3.0;
    }

    static double derivative(double x, double a, double b, double c) {
        return 2 * a * (x - b);
    }
}
