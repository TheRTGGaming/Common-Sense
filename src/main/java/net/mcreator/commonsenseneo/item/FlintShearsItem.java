package net.mcreator.commonsenseneo.item;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

public class FlintShearsItem extends ShearsItem {
	public FlintShearsItem() {
		super(new Item.Properties().durability(60));
	}

	@Override
	public int getEnchantmentValue() {
		return 2;
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState blockstate) {
		return 1f;
	}
}