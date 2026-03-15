package net.mcreator.commonsenseneo.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class RawNetheriteItem extends Item {
	public RawNetheriteItem() {
		super(new Item.Properties().fireResistant().rarity(Rarity.EPIC));
	}
}