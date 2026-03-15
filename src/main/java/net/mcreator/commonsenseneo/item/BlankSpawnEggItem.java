package net.mcreator.commonsenseneo.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class BlankSpawnEggItem extends Item {
	public BlankSpawnEggItem() {
		super(new Item.Properties().fireResistant().rarity(Rarity.EPIC));
	}
}