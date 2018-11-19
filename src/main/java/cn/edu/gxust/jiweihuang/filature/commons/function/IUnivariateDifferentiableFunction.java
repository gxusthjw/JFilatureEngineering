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

import org.hipparchus.analysis.differentiation.DSFactory;
import org.hipparchus.analysis.differentiation.DerivativeStructure;
import org.hipparchus.analysis.differentiation.UnivariateDifferentiableFunction;

/**
 * <p>The interface {@code IUnivariateDifferentiableFunction} is used for
 * representing the univariate differentiable function.</p>
 * <p>it inherits from {@code UnivariateDifferentiableFunction}
 * witch in {org.hipparchus.analysis.differentiation} package
 * of {@code hipparchus} library.</p>
 * <p>Create Date:2018-11-20</p>
 *
 * @author JiweiHuang
 * @version 1.0.0_build-20181120
 * @see IUnivariateFunction
 * @see UnivariateDifferentiableFunction
 * @see DSFactory
 * @since 1.0.0_build-20181120
 */
public interface IUnivariateDifferentiableFunction extends UnivariateDifferentiableFunction, IUnivariateFunction {
    /**
     * <p>The method {@code differential(double x, int order)} is used to
     * get differential value of function with any order
     * at independent variable {@code x}.</p>
     * <p>Create Date:2018-11-20</p>
     *
     * @param x     independent variable
     * @param order differential order
     * @return the differential value of function with order at independent variable {@code x}.
     * @author JiweiHuang
     * @since 1.0.0_build-20181120
     */
    default double differential(double x, int order) {
        //Create DSFactory
        //The {@code parameters} of DSFactory is number of independent variable.
        //The {@code order} of DSFactory is differential order of function.
        DSFactory factory = new DSFactory(1, order);
        //Create a independent variable
        //The {@code index} of factory.variable(index, x) is the index of variable.
        DerivativeStructure xds = factory.variable(0, x);
        return value(xds).getPartialDerivative(order);
    }

    /**
     * The method {@code differential(double x)} is used to
     * get the differential value of function with {@code 1 order}
     * at independent variable {@code x}.
     * <p>Create Date:2018-11-20</p>
     *
     * @param x independent variable
     * @return the differential value of function with {@code 1 order} at independent variable {@code x}.
     * @author JiweiHuang
     * @since 1.0.0_build-20181120
     */
    default double differential(double x) {
        return differential(x, 1);
    }
}