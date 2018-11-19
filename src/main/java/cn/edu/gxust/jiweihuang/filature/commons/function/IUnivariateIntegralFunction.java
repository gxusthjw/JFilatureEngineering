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

/**
 * The interface {@code IUnivariateIntegralFunction} is used for
 * representing the analytic integral function.</p>
 *
 * <p>Create date:2018-11-20.</p>
 *
 * @author JiweiHuang
 * @version 1.0.0_build-20181120
 * @see IUnivariateFunction
 */
public interface IUnivariateIntegralFunction {
    /**
     * <p>The method {@code integrate(double x)} is used to
     * get the integral value of integral function
     * at independent Variable {@code x}.</p>
     * <p>Create date:2018-11-20.</p>
     *
     * @param x independent Variable
     * @return the integral value of integral function at independent Variable {@code x}.
     * @author JiweiHuang
     * @since 1.0.0_build-20181120
     */
    double integrate(double x);

    /**
     * <p>The method {@code integrate(double x0, double x1)} is used to
     * get the definite integration in ({@code x0},{@code x1}).
     * <p>Create date:2018-11-20.</p>
     *
     * @param x0 the lower limit of integrating range.
     * @param x1 the upper limit of integrating range.
     * @return the definite integral value at integrating range [{@code x0},{@code x1}].
     * @author JiweiHuang
     * @since 1.0.0_build-20181120
     */
    default double integrate(double x0, double x1) {
        return integrate(x1) - integrate(x0);
    }

    /**
     * <p>The method {@code getIntegralConstants()} is used to
     * get the integral constants.</p>
     * <p>If the integral constants need to modify,
     * the implement class should override method
     * {@code setIntegralConstants(double integralConstants)}.</p>
     * <p>Create date:2018-11-20.</p>
     *
     * @return the integral constants.
     * @author JiweiHuang
     * @since 1.0.0_build-20181120
     */
    default double getIntegralConstants() {
        return .0;
    }

    /**
     * <p>The method {@code setIntegralConstants(double integralConstants)} is
     * used to set the integral constants.</p>
     * <p>Create date:2018-11-20.</p>
     *
     * @param integralConstants the integral constants.
     * @since 1.0.0_build-20181120
     */
    default void setIntegralConstants(double integralConstants) {
    }

    /**
     * <p>The method {@code iformula()}is used to
     * get the string form of analytic integral function.</p>
     * <p>Create date:2018-11-20.</p>
     *
     * @return the string form of analytic integral function.
     * @since 1.0.0_build-20181120
     */
    String iformula();

}
