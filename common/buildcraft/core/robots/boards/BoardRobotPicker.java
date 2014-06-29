/**
 * Copyright (c) 2011-2014, SpaceToad and the BuildCraft Team
 * http://www.mod-buildcraft.com
 *
 * BuildCraft is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */
package buildcraft.core.robots.boards;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import buildcraft.api.boards.IBoardParameter;
import buildcraft.api.boards.IBoardParameterStack;
import buildcraft.api.boards.RedstoneBoardNBT;
import buildcraft.api.boards.RedstoneBoardRegistry;
import buildcraft.api.boards.RedstoneBoardRobot;
import buildcraft.api.boards.RedstoneBoardRobotNBT;
import buildcraft.api.robots.AIRobot;
import buildcraft.api.robots.EntityRobotBase;
import buildcraft.core.inventory.filters.ArrayStackFilter;
import buildcraft.core.inventory.filters.IStackFilter;
import buildcraft.core.robots.AIRobotFetchItem;
import buildcraft.core.robots.AIRobotGotoSleep;
import buildcraft.core.robots.AIRobotGotoStationToUnload;
import buildcraft.core.robots.AIRobotSleep;
import buildcraft.core.robots.AIRobotUnload;
import buildcraft.core.robots.DockingStation;

public class BoardRobotPicker extends RedstoneBoardRobot {

	// TODO: Clean this when world unloaded
	public static Set<Integer> targettedItems = new HashSet<Integer>();

	private NBTTagCompound data;
	private RedstoneBoardNBT<?> board;
	private IBoardParameter[] params;
	private int range;
	private IStackFilter stackFilter;

	public BoardRobotPicker(EntityRobotBase iRobot) {
		super(iRobot);
	}

	public BoardRobotPicker(EntityRobotBase robot, NBTTagCompound nbt) {
		super(robot);
		data = nbt;

		board = RedstoneBoardRegistry.instance.getRedstoneBoard(nbt);
		params = board.getParameters(nbt);

		range = nbt.getInteger("range");

		ItemStack[] stacks = new ItemStack[params.length];

		for (int i = 0; i < stacks.length; ++i) {
			IBoardParameterStack pStak = (IBoardParameterStack) params[i];
			stacks[i] = pStak.getStack();
		}

		if (stacks.length > 0) {
			stackFilter = new ArrayStackFilter(stacks);
		} else {
			stackFilter = null;
		}
	}

	@Override
	public void update() {
		startDelegateAI(new AIRobotFetchItem(robot, range, stackFilter, robot.getAreaToWork()));
	}

	@Override
	public void delegateAIEnded(AIRobot ai) {
		if (ai instanceof AIRobotFetchItem) {
			AIRobotFetchItem fetching = (AIRobotFetchItem) ai;

			if (!fetching.noItemPicked && fetching.target != null) {
				// if we could get an item, let's try to get another one
				startDelegateAI(new AIRobotFetchItem(robot, range, stackFilter, robot.getAreaToWork()));
			} else if (robot.containsItems()) {
				startDelegateAI(new AIRobotGotoStationToUnload(robot, null));
			} else {
				startDelegateAI(new AIRobotGotoSleep(robot));
			}
		} else if (ai instanceof AIRobotGotoStationToUnload) {
			DockingStation station = (DockingStation) robot.getDockingStation();

			if (station != null) {
				startDelegateAI(new AIRobotUnload(robot));
			} else {
				startDelegateAI(new AIRobotSleep(robot));
			}
		}
	}

	@Override
	public RedstoneBoardRobotNBT getNBTHandler() {
		return BoardRobotPickerNBT.instance;
	}
}
