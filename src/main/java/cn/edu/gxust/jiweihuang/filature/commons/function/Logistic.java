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

import java.util.Objects;


public class Logistic implements ILogistic {

    private static final long serialVersionUID = 8627279483787287846L;

    private double logisticM;
    private double logisticK;
    private double logisticX0;


    public Logistic(double logisticM, double logisticK, double logisticX0) {
        this.logisticM = logisticM;
        this.logisticK = logisticK;
        this.logisticX0 = logisticX0;
    }


    public Logistic(double logisticK, double logisticX0) {
        this(1, logisticK, logisticX0);
    }


    public Logistic(double logisticX0) {
        this(1, 1, logisticX0);
    }


    public Logistic() {
        this(1, 1, 0);
    }


    @Override
    public double getLogisticM() {
        return logisticM;
    }


    @Override
    public double getLogisticK() {
        return logisticK;
    }


    @Override
    public double getLogisticX0() {
        return logisticX0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Logistic logistic = (Logistic) obj;
        return Double.compare(logistic.getLogisticM(), getLogisticM()) == 0 &&
                Double.compare(logistic.getLogisticK(), getLogisticK()) == 0 &&
                Double.compare(logistic.getLogisticX0(), getLogisticX0()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogisticM(), getLogisticK(), getLogisticX0());
    }
}
