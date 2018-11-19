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

package cn.edu.gxust.jiweihuang.filature.system.entity;

import cn.edu.gxust.jiweihuang.filature.bave.IBave;
import cn.edu.gxust.jiweihuang.filature.cocoon.Cocoon;
import cn.edu.gxust.jiweihuang.filature.system.ReelingSystem;

import java.util.ArrayList;
import java.util.List;

public class CocoonSupplierManager {

    private static int idCounter = 0;

    private final ReelingSystem reelingSys;

    private final List<CocoonSupplier> cocoonSuppliers = new ArrayList<>();

    public CocoonSupplierManager(ReelingSystem reelingSys) {
        this.reelingSys = reelingSys;
        for (int i = 0; i < this.reelingSys.getCocoonSupplierNum(); i++) {
            this.cocoonSuppliers.add(createCocoonSupplier());
        }
    }

    public CocoonSupplier createCocoonSupplier() {
        return new CocoonSupplier(this);
    }

    public int createCocoonSupplierID() {
        return idCounter++;
    }

    public List<Cocoon> createCocoons() {
        List<Cocoon> cocoons = new ArrayList<>(this.reelingSys.getAddCocoonsNum());
        for (int i = 0; i < this.reelingSys.getAddCocoonsNum(); i++) {
            cocoons.add(new Cocoon() {
                @Override
                public IBave getBave() {
                    return null;
                }
            });
        }
        return cocoons;
    }

}
