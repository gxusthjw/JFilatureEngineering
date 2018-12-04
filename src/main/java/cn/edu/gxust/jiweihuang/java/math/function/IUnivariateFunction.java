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

import cn.edu.gxust.jiweihuang.java.math.IMathBase;
import org.hipparchus.analysis.UnivariateFunction;

import java.io.Serializable;

/**
 * <p>The interface {@code IUnivariateFunction} is used for
 * representing univariate function.</p>
 *
 * <p>it inherits from {@code UnivariateFunction}
 * which in {@code org.hipparchus.analysis} package of
 * {@code hipparchus} library.</p>
 *
 * <p>The library {@code hipparchus} is a math library,
 * it has many feature,include the differential (derivative) with any order,
 * numerical integration and interpolation,etc.</p>
 *
 * <p>Create date:2018-11-20.</p>
 *
 * @author JiweiHuang
 * @version 1.0.0_build-20181120
 * @see UnivariateFunction
 * @see IMathBase
 * @see Serializable
 */
public interface IUnivariateFunction extends UnivariateFunction,
        IMathBase, Serializable {
    /**
     * <p>The method {@code lowerX()} is used to get
     * the lower limit of independent variable range. </p>
     *
     * <p>The implement class (or subinterface) of this interface
     * should override this method for changing the the lower limit
     * of independent variable range.</p>
     *
     * <p>
     * The default value of this method:{@code Double.MIN_VALUE},
     * why not be {@code Double.NEGATIVE_INFINITY}, because it
     * will lead to some errors for numerical integral calculation.
     * </p>
     *
     * <p>Create date:2018-11-20.</p>
     *
     * @return the lower limit of independent variable range.
     * @author JiweiHuang
     * @since 1.0.0_build-20181120
     */
    default double lowerX() {
        return Double.MIN_VALUE;
    }

    /**
     * <p>The method {@code upperX()} is used to get
     * the upper limit of independent variable range. </p>
     *
     * <p>The implement class (or subinterface) of this interface
     * should override this method for changing the upper limit of
     * independent variable range.</p>
     *
     * <p>The default value of this method:{@code Double.MAX_VALUE}
     * why not be {@code Double.NEGATIVE_INFINITY}, because it
     * will lead to some errors for numerical integral calculation.</p>
     *
     * <p>Create date:2018-11-20.</p>
     *
     * @return the upper limit of independent variable range.
     * @author JiweiHuang
     * @since 1.0.0_build-20181120
     */
    default double upperX() {
        return Double.MAX_VALUE;
    }


    /**
     * <p>The method {checkX(double x)} is used to
     * check {@code x} whether which be in
     * the range of independent variable.</p>
     *
     * <p>Create date:2018-11-20.</p>
     *
     * @param x the x-value,independent variable.
     * @return {@code boolean} for whether {@code x} be in
     * the range of independent variable.
     * @author JiweiHuang
     * @since 1.0.0_build-20181120
     */
    default boolean checkX(double x) {
        return (x >= lowerX() && x <= upperX());
    }

    /**
     * <p>The method {@code formula()} is used to get
     * the string form of analytical expression of univariate function,
     * for instance, {@code f(x)=a*x^2+c}.</p>
     *
     * <p>The implement class (or subinterface) of this interface
     * should override this method for getting suitable string form.</p>
     *
     * <p>Create date:2018-11-20.</p>
     *
     * @return the string form of analytical expression
     * of univariate function.
     * @author JiweiHuang
     * @since 1.0.0_build-20181120
     */
    String formula();

}
