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

package cn.edu.gxust.jiweihuang.filature.cocoon;


import cn.edu.gxust.jiweihuang.filature.bave.IBave;
import cn.edu.gxust.jiweihuang.filature.filament.ReelableFilament;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>The class {@code Cocoon} is used for
 * representing cocoon in reeling system.</p>
 *
 * <p>Create date:2018-11-15.</p>
 *
 * @author JiweiHuang
 * @version 1.0.0_build-20181115
 * @since 1.0.0_build-20181115
 */
public abstract class Cocoon {

    private static long cocoonIdCounter = 0;

    private final long cocoonID;

    private final List<ReelableFilament> reelableFilaments = new ArrayList<>(1);

    /**
     * The method {@code getID()} is used to get the unique identifier of cocoon in reeling system.
     *
     * @return Identitier of this cocoon.
     * @since 1.0.0_build-20181115
     */
    public Cocoon() {
        this.cocoonID = cocoonIdCounter;
        cocoonIdCounter++;
    }

    public long getCocoonID() {
        return this.cocoonID;
    }

    public abstract IBave getBave();
}
