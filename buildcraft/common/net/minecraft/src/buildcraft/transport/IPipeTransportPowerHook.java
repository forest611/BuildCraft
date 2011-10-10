/** 
 * BuildCraft is open-source. It is distributed under the terms of the 
 * BuildCraft Open Source License. It grants rights to read, modify, compile
 * or run the code. It does *NOT* grant the right to redistribute this software
 * or its modifications in any form, binary or source, except if expressively
 * granted by the copyright holder.
 */

package net.minecraft.src.buildcraft.transport;

import net.minecraft.src.buildcraft.api.Orientations;

public interface IPipeTransportPowerHook {
	public void receiveEnergy(Orientations from, double val);
	public void requestEnergy(Orientations from, int i);
}
