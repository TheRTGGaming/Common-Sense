package net.mcreator.commonsenseneo.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;

public class CookedFleshItem extends Item {
	public CookedFleshItem() {
		super(new Item.Properties().food((new FoodProperties.Builder()).nutrition(4).saturationModifier(0.9f).build()));
	}
}