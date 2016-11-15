/* Copyright (c) 2016 AlexIIL and the BuildCraft team
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/. */
package buildcraft.core;

import buildcraft.api.statements.StatementManager;
import buildcraft.api.tiles.IControllable.Mode;

import buildcraft.core.statements.*;

public class BCCoreStatements {
    public static final TriggerTrue TRIGGER_TRUE = new TriggerTrue();

    public static final TriggerMachine TRIGGER_MACHINE_ACTIVE = new TriggerMachine(true);
    public static final TriggerMachine TRIGGER_MACHINE_INACTIVE = new TriggerMachine(false);
    public static final TriggerMachine[] TRIGGER_MACHINE = { TRIGGER_MACHINE_ACTIVE, TRIGGER_MACHINE_INACTIVE };

    public static final TriggerRedstoneInput TRIGGER_REDSTONE_ACTIVE = new TriggerRedstoneInput(true);
    public static final TriggerRedstoneInput TRIGGER_REDSTONE_INACTIVE = new TriggerRedstoneInput(false);
    public static final TriggerRedstoneInput[] TRIGGER_REDSTONE = { TRIGGER_REDSTONE_ACTIVE, TRIGGER_REDSTONE_INACTIVE };

    public static final ActionRedstoneOutput ACTION_REDSTONE = new ActionRedstoneOutput();

    public static final ActionMachineControl ACTION_MACHINE_CONTROL_OFF = new ActionMachineControl(Mode.Off);
    public static final ActionMachineControl ACTION_MACHINE_CONTROL_ON = new ActionMachineControl(Mode.On);
    public static final ActionMachineControl ACTION_MACHINE_CONTROL_LOOP = new ActionMachineControl(Mode.Loop);
    public static final ActionMachineControl[] ACTION_MACHINE_CONTROL = {//
        ACTION_MACHINE_CONTROL_OFF, ACTION_MACHINE_CONTROL_ON, ACTION_MACHINE_CONTROL_LOOP //
    };

    public static final TriggerPower TRIGGER_POWER_HIGH = new TriggerPower(true);
    public static final TriggerPower TRIGGER_POWER_LOW = new TriggerPower(false);
    public static final TriggerPower[] TRIGGER_POWER = { TRIGGER_POWER_LOW, TRIGGER_POWER_HIGH };

    public static final TriggerInventory TRIGGER_INVENTORY_EMPTY = new TriggerInventory(TriggerInventory.State.Empty);
    public static final TriggerInventory TRIGGER_INVENTORY_CONTAINS = new TriggerInventory(TriggerInventory.State.Contains);
    public static final TriggerInventory TRIGGER_INVENTORY_SPACE = new TriggerInventory(TriggerInventory.State.Space);
    public static final TriggerInventory TRIGGER_INVENTORY_FULL = new TriggerInventory(TriggerInventory.State.Full);
    public static final TriggerInventory[] TRIGGER_INVENTORY = {//
        TRIGGER_INVENTORY_EMPTY, TRIGGER_INVENTORY_SPACE, TRIGGER_INVENTORY_CONTAINS, TRIGGER_INVENTORY_FULL //
    };

    public static final TriggerFluidContainer TRIGGER_FLUID_EMPTY = new TriggerFluidContainer(TriggerFluidContainer.State.Empty);
    public static final TriggerFluidContainer TRIGGER_FLUID_CONTAINS = new TriggerFluidContainer(TriggerFluidContainer.State.Contains);
    public static final TriggerFluidContainer TRIGGER_FLUID_SPACE = new TriggerFluidContainer(TriggerFluidContainer.State.Space);
    public static final TriggerFluidContainer TRIGGER_FLUID_FULL = new TriggerFluidContainer(TriggerFluidContainer.State.Full);
    public static final TriggerFluidContainer[] TRIGGER_FLUID = {//
        TRIGGER_FLUID_EMPTY, TRIGGER_FLUID_SPACE, TRIGGER_FLUID_CONTAINS, TRIGGER_FLUID_FULL //
    };

    public static final TriggerInventoryLevel TRIGGER_INVENTORY_BELOW_25 = new TriggerInventoryLevel(TriggerInventoryLevel.TriggerType.BELOW25);
    public static final TriggerInventoryLevel TRIGGER_INVENTORY_BELOW_50 = new TriggerInventoryLevel(TriggerInventoryLevel.TriggerType.BELOW50);
    public static final TriggerInventoryLevel TRIGGER_INVENTORY_BELOW_75 = new TriggerInventoryLevel(TriggerInventoryLevel.TriggerType.BELOW75);
    public static final TriggerInventoryLevel[] TRIGGER_INVENTORY_LEVEL = {//
        TRIGGER_INVENTORY_BELOW_25, TRIGGER_INVENTORY_BELOW_50, TRIGGER_INVENTORY_BELOW_75 //
    };

    public static final TriggerFluidContainerLevel TRIGGER_FLUID_BELOW_25 = new TriggerFluidContainerLevel(TriggerFluidContainerLevel.TriggerType.BELOW25);
    public static final TriggerFluidContainerLevel TRIGGER_FLUID_BELOW_50 = new TriggerFluidContainerLevel(TriggerFluidContainerLevel.TriggerType.BELOW50);
    public static final TriggerFluidContainerLevel TRIGGER_FLUID_BELOW_75 = new TriggerFluidContainerLevel(TriggerFluidContainerLevel.TriggerType.BELOW75);
    public static final TriggerFluidContainerLevel[] TRIGGER_FLUID_LEVEL = {//
        TRIGGER_FLUID_BELOW_25, TRIGGER_FLUID_BELOW_50, TRIGGER_FLUID_BELOW_75 //
    };

    public static final BCStatement[] TRIGGER_INVENTORY_ALL;
    public static final BCStatement[] TRIGGER_FLUID_ALL;

    static {
        TRIGGER_INVENTORY_ALL = new BCStatement[7];
        System.arraycopy(TRIGGER_INVENTORY, 0, TRIGGER_INVENTORY_ALL, 0, 4);
        System.arraycopy(TRIGGER_INVENTORY_LEVEL, 0, TRIGGER_INVENTORY_ALL, 4, 3);

        TRIGGER_FLUID_ALL = new BCStatement[7];
        System.arraycopy(TRIGGER_FLUID, 0, TRIGGER_FLUID_ALL, 0, 4);
        System.arraycopy(TRIGGER_FLUID_LEVEL, 0, TRIGGER_FLUID_ALL, 4, 3);
    }

    public static void preInit() {
        StatementManager.registerTriggerProvider(CoreTriggerProvider.INSTANCE);
        StatementManager.registerActionProvider(CoreActionProvider.INSTANCE);
    }
}
