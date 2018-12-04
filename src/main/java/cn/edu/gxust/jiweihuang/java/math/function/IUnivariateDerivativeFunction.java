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

package cn.edu.gxust.jiweihuang.java.math.function;

/**
 * <p>The interface {@code IUnivariateDerivativeFunction} is used for
 * representing analytic univariate derived function.</p>
 *
 * <p>Create Date:2018-11-20</p>
 *
 * @author JiweiHuang
 * @version 1.0.0_build-20181120
 * @see IUnivariateFunction
 * @see IUnivariateDifferentiableFunction
 */
public interface IUnivariateDerivativeFunction extends IUnivariateFunction {
    /**
     * <p>The method {@code derivative(double x)} is used to
     * get the derivative value of derived function
     * at independent Variable {@code x}.</p>
     *
     * <p>it should be equal to the result of {@code differential(double x)}
     * which in {@code IUnivariateDifferentiableFunction} interface.</p>
     *
     * <p>Create Date:2018-11-20</p>
     *
     * @param x independent variable
     * @return the derivative value of derived function
     * at independent variable {@code x}.
     * @author JiweiHuang
     * @since 1.0.0_build-20181120
     */
    double derivative(double x);

    /**
     * <p>The method {@code dformula()} is used to get
     * the string form of analytic univariate derived function.</p>
     *
     * <p>Create Date:2018-11-20</p>
     *
     * @return the string form of analytic univariate derived function.
     * @author JiweiHuang
     * @since 1.0.0_build-20181120
     */
    String dformula();
}