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

import java.util.Objects;

/**
 * The class {@code QuadraticVertex} is used for representing the concrete class of {@code QuadraticVertex}.
 */
public class QuadraticVertex implements IQuadraticVertex {

    /**
     * Serialized version number
     */
    private static final long serialVersionUID = -396577724818520813L;

    public final double quadraticVertexA;
    public final double quadraticVertexB;
    public final double quadraticVertexC;

    public QuadraticVertex(final double quadraticVertexA,
                           final double quadraticVertexB,
                           final double quadraticVertexC) {
        if (quadraticVertexA == 0) {
            throw new IllegalArgumentException(String.format(
                    "Expected the parameter {@code quadraticVertexA != 0},but got {@code quadraticVertexA = %.1f}",
                    quadraticVertexA));
        }
        this.quadraticVertexA = quadraticVertexA;
        this.quadraticVertexB = quadraticVertexB;
        this.quadraticVertexC = quadraticVertexC;
    }


    @Override
    public double getQuadraticVertexA() {
        return quadraticVertexA;
    }


    @Override
    public double getQuadraticVertexB() {
        return quadraticVertexB;
    }


    @Override
    public double getQuadraticVertexC() {
        return quadraticVertexC;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        QuadraticVertex that = (QuadraticVertex) obj;
        return Double.compare(that.getQuadraticVertexA(), getQuadraticVertexA()) == 0 &&
                Double.compare(that.getQuadraticVertexB(), getQuadraticVertexB()) == 0 &&
                Double.compare(that.getQuadraticVertexC(), getQuadraticVertexC()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuadraticVertexA(), getQuadraticVertexB(), getQuadraticVertexC());
    }

    @Override
    public String toString() {
        return "QuadraticVertex{" +
                "quadraticVertexA=" + quadraticVertexA +
                ", quadraticVertexB=" + quadraticVertexB +
                ", quadraticVertexC=" + quadraticVertexC +
                '}';
    }
}
