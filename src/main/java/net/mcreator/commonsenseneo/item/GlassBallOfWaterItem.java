package net.mcreator.commonsenseneo.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

import net.mcreator.commonsenseneo.init.CommonSenseModItems;

public class GlassBallOfWaterItem extends Item {
	public GlassBallOfWaterItem() {
		super(new Item.Properties().stacksTo(16));
	}

	@Override
	public boolean hasCraftingRemainingItem(ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack getCraftingRemainingItem(ItemStack itemstack) {
		return new ItemStack(CommonSenseModItems.GLASS_BALL.get());
	}
}