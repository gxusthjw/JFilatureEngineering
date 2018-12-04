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
package cn.edu.gxust.jiweihuang.java.math;

/**
 * <p>The base interface of {@code math} package.it provide some constants
 * for numerical calculation. every class (or interface) should implements
 * (or inherits ) this interface. </p>
 *
 * <p>Create date:2018-11-20.</p>
 *
 * @author JiweiHuang
 * @version 1.0.0_build-20181120
 */
public interface IMathBase {
    /**
     * the constant represented numerical precision for
     * numerical calculation.
     */
    double NUMERICAL_PRECISION = 1.0E-15;
}
