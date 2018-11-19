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
 * <p> The interface {@code IQuadratic} is used for representing Quadratic Function.</p>
 * <p> Reference:https://en.wikipedia.org/wiki/Quadratic_function </p>
 * <p> Create Date:2018-11-20 </p>
 *
 * @author JiweiHuang
 * @version 1.0.0_build-20181120
 * @see IUnivariateDifferentiableFunction
 * @see IUnivariateDerivativeFunction
 * @see IUnivariateIntegralFunction
 */
public interface IQuadratic extends IUnivariateDifferentiableFunction,
        IUnivariateDerivativeFunction, IUnivariateIntegralFunction {
    /**
     * <p> Create Date:2018-11-20 </p>
     *
     * @return the vertex of quadratic function
     * @author JiweiHuang
     * @since 1.0.0_build-20181120
     */
    double[] vertex();

    /**
     * <p> Create Date:2018-11-20 </p>
     *
     * @return the intersection of the quadratic function with the X-axis.
     * @author JiweiHuang
     * @since 1.0.0_build-20181120
     */
    double[] xIntersection();

    /**
     * <p> Create Date:2018-11-20 </p>
     *
     * @return the intersection of the quadratic function with the Y-axis.
     * @author JiweiHuang
     * @since 1.0.0_build-20181120
     */
    default double yIntersection() {
        return value(0);
    }

    /**
     * <p> Create Date:2018-11-20 </p>
     *
     * @return the intersection number of the quadratic function with the X-axis.
     * @author JiweiHuang
     * @since 1.0.0_build-20181120
     */
    default int xIntersectionNum() {
        return xIntersection().length;
    }

    /**
     * <p> Create Date:2018-11-20 </p>
     *
     * @return whether is invert of quadratic function.
     * @author JiweiHuang
     * @since 1.0.0_build-20181120
     */
    boolean isInvert();
}
