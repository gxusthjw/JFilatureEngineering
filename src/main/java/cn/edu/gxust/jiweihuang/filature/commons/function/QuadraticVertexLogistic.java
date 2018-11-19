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
 * The class {@code QuadraticVertexLogistic} is used for representing the concrete class of logistic function.
 */
public class QuadraticVertexLogistic implements IQuadraticVertexLogistic {

    private static final long serialVersionUID = 2759509174823334160L;

    private IQuadraticVertex quadraticVertex;
    private ILogistic logistic;
    private double quadraticVertexLogisticD;

    public QuadraticVertexLogistic(double quadraticVertexA, double quadraticVertexB, double quadraticVertexC,
                                   double logisticM, double logisticK, double logisticX0,
                                   double quadraticVertexLogisticD) {
        this.quadraticVertex = new QuadraticVertex(quadraticVertexA, quadraticVertexB, quadraticVertexC);
        this.logistic = new Logistic(logisticM, logisticK, logisticX0);
        this.quadraticVertexLogisticD = quadraticVertexLogisticD;
    }

    @Override
    public IQuadraticVertex getQuadraticVertex() {
        return quadraticVertex;
    }

    @Override
    public ILogistic getLogistic() {
        return logistic;
    }

    @Override
    public double getQuadraticVertexLogisticD() {
        return quadraticVertexLogisticD;
    }

}
