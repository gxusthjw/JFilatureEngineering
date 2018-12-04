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

import org.hipparchus.analysis.integration.RombergIntegrator;
import org.hipparchus.analysis.integration.SimpsonIntegrator;
import org.hipparchus.analysis.integration.IterativeLegendreGaussIntegrator;
import org.hipparchus.analysis.integration.TrapezoidIntegrator;
import org.hipparchus.analysis.integration.MidPointIntegrator;

public interface IUnivariateIntegrableFunction extends IUnivariateFunction {
    default double integrateRomberg() {
        return new RombergIntegrator().integrate(Integer.MAX_VALUE, this, lowerX(), upperX());
    }

    default double integrateRomberg(double upperX) {
        return new RombergIntegrator().integrate(Integer.MAX_VALUE, this, lowerX(), upperX);
    }

    default double integrateRomberg(double lowerX, double upperX) {
        return new RombergIntegrator().integrate(Integer.MAX_VALUE, this, lowerX, upperX);
    }

    default double integrateRomberg(int maxEval, double lowerX, double upperX) {
        return new RombergIntegrator().integrate(maxEval, this, lowerX, upperX);
    }

    default double integrateSimpson() {
        return new SimpsonIntegrator().integrate(Integer.MAX_VALUE, this, lowerX(), upperX());
    }

    default double integrateSimpson(double upperX) {
        return new SimpsonIntegrator().integrate(Integer.MAX_VALUE, this, lowerX(), upperX);
    }

    default double integrateSimpson(double lowerX, double upperX) {
        return new SimpsonIntegrator().integrate(Integer.MAX_VALUE, this, lowerX, upperX);
    }

    default double integrateSimpson(int maxEval, double lowerX, double upperX) {
        return new SimpsonIntegrator().integrate(maxEval, this, lowerX, upperX);
    }

    default double integrateTrapezoid() {
        return new TrapezoidIntegrator().integrate(Integer.MAX_VALUE, this, lowerX(), upperX());
    }

    default double integrateTrapezoid(double upperX) {
        return new TrapezoidIntegrator().integrate(Integer.MAX_VALUE, this, lowerX(), upperX);
    }

    default double integrateTrapezoid(double lowerX, double upperX) {
        return new TrapezoidIntegrator().integrate(Integer.MAX_VALUE, this, lowerX, upperX);
    }

    default double integrateTrapezoid(int maxEval, double lowerX, double upperX) {
        return new TrapezoidIntegrator().integrate(maxEval, this, lowerX, upperX);
    }

    default double integrateMidPoint() {
        return new MidPointIntegrator().integrate(Integer.MAX_VALUE, this, lowerX(), upperX());
    }

    default double integrateMidPoint(double upperX) {
        return new MidPointIntegrator().integrate(Integer.MAX_VALUE, this, lowerX(), upperX);
    }

    default double integrateMidPoint(double lowerX, double upperX) {
        return new MidPointIntegrator().integrate(Integer.MAX_VALUE, this, lowerX, upperX);
    }

    default double integrateMidPoint(int maxEval, double lowerX, double upperX) {
        return new MidPointIntegrator().integrate(maxEval, this, lowerX, upperX);
    }

    default double integrateIterativeLegendreGauss() {
        return new IterativeLegendreGaussIntegrator(IterativeLegendreGaussN,
                IterativeLegendreGaussIntegrator.DEFAULT_RELATIVE_ACCURACY, IterativeLegendreGaussIntegrator.DEFAULT_ABSOLUTE_ACCURACY).
                integrate(Integer.MAX_VALUE, this, lowerX(), upperX());
    }

    default double integrateIterativeLegendreGauss(double upperX) {
        return new IterativeLegendreGaussIntegrator(IterativeLegendreGaussN,
                IterativeLegendreGaussIntegrator.DEFAULT_RELATIVE_ACCURACY, IterativeLegendreGaussIntegrator.DEFAULT_ABSOLUTE_ACCURACY).
                integrate(Integer.MAX_VALUE, this, lowerX(), upperX);
    }

    default double integrateIterativeLegendreGauss(double lowerX, double upperX) {
        return new IterativeLegendreGaussIntegrator(IterativeLegendreGaussN,
                IterativeLegendreGaussIntegrator.DEFAULT_RELATIVE_ACCURACY, IterativeLegendreGaussIntegrator.DEFAULT_ABSOLUTE_ACCURACY).
                integrate(Integer.MAX_VALUE, this, lowerX, upperX);
    }

    default double integrateIterativeLegendreGauss(int maxEval, double lowerX, double upperX) {
        return new IterativeLegendreGaussIntegrator(IterativeLegendreGaussN,
                IterativeLegendreGaussIntegrator.DEFAULT_RELATIVE_ACCURACY, IterativeLegendreGaussIntegrator.DEFAULT_ABSOLUTE_ACCURACY).
                integrate(maxEval, this, lowerX, upperX);
    }

    default double integrateIterativeLegendreGauss(int n, int maxEval, double lowerX, double upperX) {
        return new IterativeLegendreGaussIntegrator(n,
                IterativeLegendreGaussIntegrator.DEFAULT_RELATIVE_ACCURACY,
                IterativeLegendreGaussIntegrator.DEFAULT_ABSOLUTE_ACCURACY).
                integrate(maxEval, this, lowerX, upperX);
    }

    int IterativeLegendreGaussN = 20;
}
