/*
 * Copyright (c) 2018-2019, Jiwei Huang. All Rights Reserved.
 *
 * This file is part of ReelingSystem.
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

package cn.edu.gxust.jiweihuang.filature.system.entity;

import cn.edu.gxust.jiweihuang.filature.cocoon.Cocoon;

import java.util.*;

/**
 * <p>The class {CocoonSupplier} is used for
 * representing cocoon supplier in reeling system.</p>
 *
 * <p>Create date:2018-11-15.</p>
 *
 * @author JiweiHuang
 * @version 1.0.0_build-20181115
 * @since 1.0.0_build-20181115
 */
public class CocoonSupplier {

    public final int DEFAULT_CAPACITY = 60;

    public final int MAX_CAPACITY = 150;

    public final int MIN_CAPACITY = 0;

    private final List<Cocoon> cocoons = new ArrayList<>(DEFAULT_CAPACITY);

    private final int cocoonSupplierID;

    private final CocoonSupplierManager cocoonSupplierManager;

    public CocoonSupplier(final CocoonSupplierManager cocoonSupplierManager) {
        this.cocoonSupplierManager = cocoonSupplierManager;
        this.cocoonSupplierID = this.cocoonSupplierManager.createCocoonSupplierID();
        this.add(this.cocoonSupplierManager.createCocoons());
    }

    /**
     * <p>The method {@code supply()} is used to supply a cocoon for
     * reeling thread.</p>
     *
     * @return cocoon that was supplied.
     * @since 1.0.0_build-20181115
     */
    public Cocoon supply() {
        int index = new Random().nextInt(getCocoonNum());
        Cocoon cocoon = this.cocoons.get(index);
        this.cocoons.remove(index);
        return cocoon;
    }


    /**
     * The method {@code add(Cocoon... cocoons)} is used for
     * adding cocoon to cocoon supplier.
     *
     * @param cocoons cocoon that be add to cocoon supplier.
     * @since 1.0.0_build-20181115
     */
    public void add(Cocoon... cocoons) {
        this.cocoons.addAll(Arrays.asList(cocoons));
    }

    /**
     * The method {@code add(Collection<? extends Cocoon> cocoons)} is used for
     * adding cocoon to cocoon supplier.
     *
     * @param cocoons cocoon that be add to cocoon supplier.
     * @since 1.0.0_build-20181115
     */
    public void add(Collection<? extends Cocoon> cocoons) {
        this.cocoons.addAll(cocoons);
    }

    /**
     * @return the number of cocoon in this cocoon supplier.
     */
    public int getCocoonNum() {
        return this.cocoons.size();
    }
}
