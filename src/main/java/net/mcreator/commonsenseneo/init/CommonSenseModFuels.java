/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.commonsenseneo.init;

import net.neoforged.neoforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.ItemStack;

@EventBusSubscriber
public class CommonSenseModFuels {
	@SubscribeEvent
	public static void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		ItemStack itemstack = event.getItemStack();
		if (itemstack.getItem() == CommonSenseModBlocks.COMPRESSED_COAL_BLOCK.get().asItem())
			event.setBurnTime(144000);
		else if (itemstack.getItem() == CommonSenseModBlocks.DOUBLE_COMPRESSED_COAL_BLOCK.get().asItem())
			event.setBurnTime(1296000);
		else if (itemstack.getItem() == CommonSenseModBlocks.INRICHED_COAL_BLOCK.get().asItem())
			event.setBurnTime(11664000);
		else if (itemstack.getItem() == CommonSenseModItems.CHARED_BARK.get())
			event.setBurnTime(1600);
	}
}