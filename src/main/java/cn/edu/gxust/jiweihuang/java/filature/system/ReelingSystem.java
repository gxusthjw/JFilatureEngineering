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
package cn.edu.gxust.jiweihuang.java.filature.system;

import cn.edu.gxust.jiweihuang.java.filature.system.entity.CocoonSupplierManager;
import cn.edu.gxust.jiweihuang.java.filature.system.entity.ReelingThreadsManager;


public class ReelingSystem {

    private final ReelingSystemParameters parameters;

    private final ReelingThreadsManager reelingThreadsManager;
    private final CocoonSupplierManager cocoonSupplierManager;
    private final ReelingTimer reelingTimer;

    public ReelingSystem(final ReelingSystemParameters parameters) {
        this.parameters = parameters;
        this.reelingThreadsManager = new ReelingThreadsManager(this);
        this.cocoonSupplierManager = new CocoonSupplierManager(this);
        this.reelingTimer = new ReelingTimer(this);
    }

    public void init() {

    }

    public void start() {
    }

    public int getCocoonSupplierNum() {
        return 30;
    }

    public int getAddCocoonsNum() {
        return 60;
    }

    public static void main(String[] args) {
        ReelingSystemParameters reelingSystemParameters = new ReelingSystemParameters();
        ReelingSystem reelingSystem = new ReelingSystem(reelingSystemParameters);
        reelingSystem.start();
    }
}
